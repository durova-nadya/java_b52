package ru.stqa.addressbook.manager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.AvailableSettings;
import ru.stqa.addressbook.manager.hbm.ContactRecord;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;


    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
               .addAnnotatedClass(GroupRecord.class)
               .addAnnotatedClass(ContactRecord.class)
               .setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
               .setProperty(AvailableSettings.JAKARTA_JDBC_USER, "root")
               .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
               .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    static List<ContactData> convertListContact(List<ContactRecord> records) {
        List<ContactData> result = new ArrayList<>();
        for (var record : records) {
            result.add(transform(record));
        }
        return result;
    }

    private static ContactData transform(ContactRecord record) {
        return new ContactData("" + record.id, record.firstname, record.lastname, record.address, record.email);
    }

    private static ContactRecord transform(ContactData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstname(), data.lastname(), data.address(), data.email());
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public List<ContactData> getContactList() {
        return convertListContact(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }


    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(transform(contactData));
            session.getTransaction().commit();
        });

    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertListContact(session.get(GroupRecord.class, group.id()).contacts);
        });

    }
}

package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactsInGroups extends TestBase{

    @Test
    void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName("Гаечка")
                .withLastName("Спасатель")
                .withEmail("gaya@ya.ru");
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Friends", "Best friends", "comment"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContactInGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }


    @Test
    void canAddContactInGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Саша", "Наш", "Санкт-Петербург", "pushkin.as@mail.ru"));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Friends", "Best friends", "comment"));
        }

        var allContacts = app.hbm().getContactList();;
        var rnd = new Random();
        var indexContact = rnd.nextInt(allContacts.size());
        var contact = app.hbm().getContactList().get(indexContact);

        var allGroups = app.hbm().getGroupList();
        var indexGroup = rnd.nextInt(allGroups.size());
        var group = app.hbm().getGroupList().get(indexGroup);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().addContactInGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var expectedList = new ArrayList<>(oldRelated);
        if (!app.contacts().contactInGroup(contact.id(), oldRelated)) {
            expectedList.add(contact.withId(contact.id()));
        }
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newRelated);
    }

    @Test
    void canDeleteContactFromGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Friends", "Best friends", "comment"));
        }
        var group = app.hbm().getGroupList().get(0);
        if (app.hbm().getContactCount() == 0) {
            var contact = new ContactData("", "Саша", "Наш", "Санкт-Петербург", "pushkin.as@mail.ru");
            app.contacts().createContactInGroup(contact, group);
        }

        var allGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var indexGroup = rnd.nextInt(allGroups.size());
        group = app.hbm().getGroupList().get(indexGroup);

        var allContacts = app.hbm().getContactList();
        var indexContact = rnd.nextInt(allContacts.size());
        var contact = app.hbm().getContactList().get(indexContact);

        var allRelated = app.hbm().getContactsInGroup(group);
        if (allRelated.isEmpty()) {
            app.contacts().addContactInGroup(contact, group);
        }

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().removeContactFromGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.remove(contact.withId(contact.id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newRelated);
    }
}

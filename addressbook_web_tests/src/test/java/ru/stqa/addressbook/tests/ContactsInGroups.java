package ru.stqa.addressbook.tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.*;

public class ContactsInGroups extends TestBase{

    @Test
    void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName("Gaya")
                .withLastName("Mouse")
                .withEmail("gaya@ya.ru")
                .withAddress("Orel")
                .withHome("2342342342")
                .withMobile("3242344")
                .withWork("234234");
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
    void canAddContactInGroup2() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Sacha", "Puh", "Spb", "pushkin.as@mail.ru", "1474147", "7452775", "7578575", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Friends", "Best friends", "comment"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().contactPutIntoGroup(group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }


    @Test
    void canAddContactInGroup() {

        boolean isGroup = false;
        List<ContactData> oldRelated = new ArrayList<>();
        var contactCheck = new ContactData();
        var groupCheck = new GroupData();

        Allure.step("Checking precondition", step -> {
                    if (app.hbm().getContactCount() == 0) {
                        app.hbm().createContact(new ContactData("", "Sacha", "Puh", "Spb", "pushkin.as@mail.ru", "1474147", "7452775", "7578575", ""));
                    }
                    if (app.hbm().getGroupCount() == 0) {
                        app.hbm().createGroup(new GroupData("", "Friends", "Best friends", "comment"));
                    }
                });

        var allGroups = app.hbm().getGroupList();
        var allContacts = app.hbm().getContactList();

        for (var group : allGroups) {
            oldRelated = app.hbm().getContactsInGroup(group);
            for (var contact : allContacts) {
                if (!app.contacts().contactInGroup(contact.id(), oldRelated)) {
                    app.contacts().addContactInGroup(contact, group);
                    isGroup = true;
                    groupCheck = group;
                    contactCheck = contact;
                    break;
                }
            }
            if (isGroup) {break;}
        }
        if (! isGroup) {
            groupCheck = app.hbm().getGroupList().get(0);
            oldRelated = app.hbm().getContactsInGroup(groupCheck);
            app.hbm().createContact(new ContactData("", "Sacha", "Bach", "Moscow", "pushkin.as@mail.ru", "7557411", "75275272", "77578585", ""));
            int maxId = (int) app.hbm().getContactCount();
            contactCheck = app.hbm().getContactList().get(maxId - 1);
            app.contacts().addContactInGroup(contactCheck, groupCheck);
        }

        var newRelated = app.hbm().getContactsInGroup(groupCheck);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contactCheck.withId(contactCheck.id()));

        Allure.step("Validating results", step -> {
            Assertions.assertEquals(Set.copyOf(expectedList), Set.copyOf(newRelated));
        });
    }


    @Test
    void canAddContactInNewGroup() {

        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Alex", "Bach", "Moscow", "pushkin.as@mail.ru", "47727", "7527527", "7852527", ""));
        }
        app.hbm().createGroup(new GroupData("", "Work", "Colleagues", "Family"));

        int maxId = (int) app.hbm().getGroupCount();
        var group = app.hbm().getGroupList().get(maxId - 1);

        var contact = app.hbm().getContactList().get(0);
        app.contacts().addContactInGroup(contact, group);

        List<ContactData> expectedList = new ArrayList<>();
        expectedList.add(contact);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(expectedList, newRelated);
    }

    @Test
    void canRemoveContactFromGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Friends", "Best friends", "comment"));
        }
        var group = app.hbm().getGroupList().get(0);
        if (app.hbm().getContactCount() == 0) {
            var contact = new ContactData("", "Alex", "Bah", "Tula", "pushkin.as@mail.ru", "752727", "2727", "7217", "");
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

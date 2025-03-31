package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ContactModificationTests extends TestBase
{
    @Test
    void canModificationContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Саша", "Наш", "Санкт-Петербург", "pushkin.as@mail.ru"));
        }
        var oldContacts = app.hbm().getContactList();;
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName("Александр").withLastName("Пушкин");
        app.contacts().modifyContact(index, testData);

        var newContacts = app.hbm().getContactList();;
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
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

}

package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "Ann", "Marshak", "Moscow", "marshak_ann@ya.ru", "")
            );
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canRemoveAllContactAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "Ann", "Marshak", "Moscow", "marshak_ann@ya.ru", "avatar5.png"));
        }
        app.contacts().removeAllContact();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}

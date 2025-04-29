package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Sacha", "Nash", "Spb", "pushkin.as@mail.ru", "5248", "6752", "454", ""));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.phone2())
                        .filter(s -> s != null && ! "".equals(s))
                        .collect(Collectors.joining("\n"))
                        .replaceAll("[()\\-]", "").replaceAll(" ", "")
                ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Alexandr", "Puchkin", "Spb", "pushkin.as@mail.ru", "88121110055", "+79168841212", "445", ""));
        }
        var contacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());
        var expected = app.contacts().getList().get(index);
        var actual = app.contacts().getInfoContactEditPage(index);
        Assertions.assertEquals(expected, actual);
    }
}

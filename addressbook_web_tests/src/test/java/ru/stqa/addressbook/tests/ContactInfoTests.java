package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Саша", "Наш", "Санкт-Петербург", "pushkin.as@mail.ru", "", "", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var expected =contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.phone2())
                        .filter(s -> s != null && ! "".equals(s))
                        .collect(Collectors.joining("\n"))
                        .replaceAll("[()\\-]", "").replaceAll(" ", "")
                ));
        var phones =app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }
}

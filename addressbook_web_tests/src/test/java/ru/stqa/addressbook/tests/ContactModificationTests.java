package ru.stqa.addressbook.tests;

import io.qameta.allure.Allure;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class ContactModificationTests extends TestBase
{
    @Test
    void canModificationContact() {
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getContactCount() == 0) {
                app.hbm().createContact(new ContactData("", "Sacha", "Onegin", "Spb", "pushkin.as@mail.ru", "11222245", "11222245", "11222245", ""));
            }
        });

        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData()
                .withFirstName("Alexandr")
                .withLastName("Puchkin")
                .withAddress("Moscow")
                .withEmail("asp@mymail.com");

        app.contacts().modifyContact(index, testData);

        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));

        Allure.step("Validating results", step -> {
            Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
        });
    }
}

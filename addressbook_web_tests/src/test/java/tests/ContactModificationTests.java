package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase
{
    @Test
    void canModificationContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "Anna", "Brodskaya", "Susdal", "brodskaya_anna@ya.ru", "avatar5.png")
            );
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName("Анна").withLastName("Бородина").withPhoto("avatar5.png");
        app.contacts().modifyContact(index, testData);

        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()).withPhoto(""));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}

package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("Ann", "Marshak", "Moscow", "marshak_ann@ya.ru")
            );
        }
        app.contacts().removeContact();
    }
}

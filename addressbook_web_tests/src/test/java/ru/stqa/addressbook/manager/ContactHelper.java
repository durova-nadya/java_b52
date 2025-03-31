package ru.stqa.addressbook.manager;

import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        openHomePage();
    }

    public void createContactInGroup(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        openHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }


    public void modifyContact(int index, ContactData modifiedContact) {
        openHomePage();
        initContactModification(index);
        fillContactForm(modifiedContact);
        submitContactModification();
        openHomePage();
    }


    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification(int index) {
        click(By.xpath(String.format("(//img[@alt='Edit'])[%s]", index + 1)));
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContacts();
        openHomePage();
    }

    private void removeSelectedContacts() {
        click(By.xpath("//div[2]/input"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }


    private void openHomePage() {
        click(By.linkText("home"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        //attach(By.name("photo"), contact.photo());
    }

    private void initContactCreation() {
        click(By.linkText("add new"));

    }

    public void removeAllContact() {
        openHomePage();
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var rows = manager.driver.findElements(By.name("entry"));
            for (var row : rows) {
                var lastname = row.findElement(By.cssSelector(":nth-child(2)")).getText();
                var firstname = row.findElement(By.cssSelector(":nth-child(3)")).getText();;
                var address = row.findElement(By.cssSelector(":nth-child(4)")).getText();;
                var email = row.findElement(By.cssSelector(":nth-child(5)")).getText();;
                var checkbox = row.findElement(By.name("selected[]"));
                var id = checkbox.getDomAttribute("value");
                    contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).withAddress(address).withEmail(email));
            }
        return contacts;
    }


    public void addContactInGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        selectGroupFromHomePage(group);
        addContactToGroup();
        openPageContactsInGroup(group);
    }

    private void openPageContactsInGroup(GroupData group) {
        click(By.linkText(String.format("group page \"%s\"", group.name())));
    }

    private void addContactToGroup() {
        click(By.name("add"));
    }

    private void selectGroupFromHomePage(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    public boolean contactInGroup(String id, List<ContactData> contacts) {
        for (var contact : contacts) {
            var s = contact.id();
            if (id.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectFilterToGroup(group);
        selectContact(contact);
        removeContactOfGroup();
        openPageContactsInGroup(group);
    }

    private void removeContactOfGroup() {
        click(By.name("remove"));
    }

    private void selectFilterToGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }
}


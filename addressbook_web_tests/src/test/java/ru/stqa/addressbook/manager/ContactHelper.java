package ru.stqa.addressbook.manager;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    @Step
    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        openHomePage();
    }

    @Step
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


    @Step
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

    @Step
    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContacts();
        openHomePage();
    }

    private void removeSelectedContacts() {
        click(By.xpath("//div[2]/input"));
    }

    @Step
    private void selectContact(ContactData contact) {
//        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
//        WebElement element = wait.until(
//                ExpectedConditions.elementToBeClickable(By.cssSelector(String.format("input[value='%s']", contact.id()))));
//        element.click();
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }


    @Step
    public void openHomePage() {
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
        type(By.name("home"), contact.home());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work());

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

    @Step
    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    @Step
    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var rows = manager.driver.findElements(By.name("entry"));
            for (var row : rows) {
                var lastname = row.findElement(By.cssSelector(":nth-child(2)")).getText();
                var firstname = row.findElement(By.cssSelector(":nth-child(3)")).getText();
                var address = row.findElement(By.cssSelector(":nth-child(4)")).getText();
                var email = row.findElement(By.cssSelector(":nth-child(5)")).getText();
                var phone = row.findElement(By.cssSelector(":nth-child(6)")).getText();
                var checkbox = row.findElement(By.name("selected[]"));
                var id = checkbox.getDomAttribute("value");
                    contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).withAddress(address).withEmail(email).withMobile(phone));
            }
        return contacts;
    }


    @Step
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

    @Step
    public boolean contactInGroup(String id, List<ContactData> contacts) {
        for (var contact : contacts) {
            var s = contact.id();
            if (id.equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Step
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

//    public String getPhones(ContactData contact) {
//        return manager.driver.findElement(By.xpath(
//                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
//    }

    @Step
    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row: rows) {
            var id = row.findElement(By.tagName("input")).getDomAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    @Step
    public Object getInfoContactEditPage(int index) {
        openHomePage();
        initContactModification(index);

        var id = manager.driver.findElement(By.name("id")).getDomAttribute("value");
        var lastname = manager.driver.findElement(By.name("lastname")).getDomAttribute("value");
        var firstname = manager.driver.findElement(By.name("firstname")).getDomAttribute("value");
        var address = manager.driver.findElement(By.name("address")).getText();

        var email1 = manager.driver.findElement(By.name("email")).getDomAttribute("value");
        var email2 = manager.driver.findElement(By.name("email2")).getDomAttribute("value");
        var email3 = manager.driver.findElement(By.name("email3")).getDomAttribute("value");
        var email = Stream.of(email1, email2, email3)
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));

        var phone1 = manager.driver.findElement(By.name("home")).getDomAttribute("value");
        var phone2 = manager.driver.findElement(By.name("mobile")).getDomAttribute("value");
        var phone3 = manager.driver.findElement(By.name("work")).getDomAttribute("value");
        var phone = Stream.of(phone1, phone2, phone3)
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"))
                .replaceAll("[()\\-]", "").replaceAll(" ", "");

        var contact = new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).withAddress(address).withEmail(email).withMobile(phone);
        return contact;
    }
}


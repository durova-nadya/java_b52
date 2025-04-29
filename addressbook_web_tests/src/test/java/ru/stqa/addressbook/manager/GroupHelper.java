package ru.stqa.addressbook.manager;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import ru.stqa.addressbook.model.GroupData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openGroupPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    @Step
    public void createGroup(GroupData group) {
        openGroupPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    @Step
    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupPage();
        selectGroup(group);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupPage();
    }


    @Step
    public void revomeGroup(GroupData group) {
        openGroupPage();
        selectGroup(group);
        removeSelectedGroups();
        returnToGroupPage();
    }

    private void removeSelectedGroups() {
        click(By.name("delete"));
    }


    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }


    private void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    @Step
    private void selectGroup(GroupData group) {
        click(By.cssSelector(String.format("input[value='%s']", group.id())));
    }

    @Step
    public int getCount() {
        openGroupPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    @Step
    public void removeAllGroups() {
        openGroupPage();
        selectAllGroups();
        removeSelectedGroups();
    }

    @Step
    private void selectAllGroups() {
        manager.driver
                .findElements(By.name("selected[]"))
                .forEach(WebElement::click);
    }

    @Step
    public List<GroupData> getList() {
        openGroupPage();
        var groups = new ArrayList<GroupData>();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        return spans.stream()
                .map(span -> {
                    var name = span.getText();
                    var checkbox = span.findElement(By.name("selected[]"));
                    var id = checkbox.getDomAttribute("value");
                    return new GroupData().withId(id).withName(name);
                })
                .collect(Collectors.toList());
    }
}

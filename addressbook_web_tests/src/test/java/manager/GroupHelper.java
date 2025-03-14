package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openGroupPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public boolean isGroupPresent() {
        openGroupPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createGroup(GroupData group) {
        openGroupPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }


    public void modifyGroup(GroupData modifiedGroup) {
        openGroupPage();
        selectGroup();
        initGroupModication();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupPage();
    }


    public void revomeGroup() {
        openGroupPage();
        selectGroup();
        removeSelectedGroup();
        returnToGroupPage();
    }

    private void removeSelectedGroup() {
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

    private void initGroupModication() {
        click(By.name("edit"));
    }

    private void selectGroup() {
        click(By.name("selected[]"));
    }

}

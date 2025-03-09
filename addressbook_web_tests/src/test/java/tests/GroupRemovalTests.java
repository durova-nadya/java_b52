package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        app.openGroupPage();
        if (!app.isGroupPresent()) {
            app.createGroup(new GroupData("Friends", "Best friends", "comment"));
        }
        app.revomeGroup();
    }

}

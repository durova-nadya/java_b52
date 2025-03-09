import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void canCreateGroup() {
        openGroupPage();
        createGroup("Friends", "Best friends", "comment");
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        openGroupPage();
        createGroup("", "", "");
    }
}

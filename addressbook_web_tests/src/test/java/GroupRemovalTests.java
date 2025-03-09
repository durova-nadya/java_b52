import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        openGroupPage();
        if (!isGroupPresent()) {
            createGroup(new GroupData("Friends", "Best friends", "comment"));
        }
        revomeGroup();
    }

}

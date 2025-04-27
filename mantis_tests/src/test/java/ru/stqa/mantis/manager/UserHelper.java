package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserHelper extends HelperBase {
    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createUser(String username, String email) {
        openManagePage();
        openManageUserPage();
        initUserCreation();
        fillUserForm(username, email);
        submitUserCreation();
    }

    private void submitUserCreation() {
        click(By.xpath("//input[@value='Create User']"));
    }

    private void fillUserForm(String username, String email) {
        type(By.name("username"), username);
        type(By.name("realname"), username);
        type(By.name("email"), email);
    }

    private void initUserCreation() {
       click(By.xpath("//a[text()='Create New Account']"));
       //click(By.xpath("//a[@href='manage_user_create_page.php']"));
    }

    private void openManageUserPage() {
        click(By.xpath("//a[text()='Manage Users']"));
        //click(By.xpath("//a[@href='/mantisbt-2.25.8/manage_user_page.php']"));

    }

    private void openManagePage() {
        //click(By.xpath("//span[text()='Manage']"));
        click(By.xpath("//a[@href='/mantisbt-2.25.8/manage_overview_page.php']"));
    }

    public String receivedUrl(String email) {
        var messages = manager.mail().receive(email, "password", Duration.ofSeconds(60));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            return url;
        }
        return null;

    }

    public void activation(String url) {
        openVerificationPage(url);
        fillVerificationPage();
        submitVerification();
    }

    private void submitVerification() {
        click(By.cssSelector("button[type='submit'].btn-success"));
    }

    private void fillVerificationPage() {
        type(By.name("password"), "password");
        type(By.name("password_confirm"), "password");
    }

    private void openVerificationPage(String url) {
        manager.driver().get(url);
    }
}

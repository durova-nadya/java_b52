package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;

import java.util.List;

public class UserRegistrationTests extends TestBase {

    public static List<String> singleRandomUserNameProvider() {
        return List.of(CommonFunctions.randomString(8));
    }

    @ParameterizedTest
    @MethodSource("singleRandomUserNameProvider")
    public void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        app.jamesCli().addUser(email, "password");
        app.users().createUser(username, email);

        var url = app.users().receivedUrl(email);
        if (url != null) {
            app.users().activation(url);
        }

        app.http().login(username, "password");
        Assertions.assertTrue(app.http().isLoggedIn());

    }

    @ParameterizedTest
    @MethodSource("singleRandomUserNameProvider")
    public void canCreateUser(String username) {
        var email = String.format("%s@localhost", username);
        app.jamesApi().addUser(email, "password");
        app.users().createUser(username, email);

        var url = app.users().receivedUrl(email);
        app.users().activation(url);


        app.http().login(username, "password");
        Assertions.assertTrue(app.http().isLoggedIn());

    }

}

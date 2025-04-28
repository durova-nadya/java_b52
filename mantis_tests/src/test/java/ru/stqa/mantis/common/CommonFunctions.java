package ru.stqa.mantis.common;

import ru.stqa.mantis.model.MailMessage;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(i -> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());
        return result;
    }

    public static String extractUrl(String message) {
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(message);
        if (matcher.find()) {
            var url = message.substring(matcher.start(), matcher.end());
            return url;
        }
        return null;
    }
}

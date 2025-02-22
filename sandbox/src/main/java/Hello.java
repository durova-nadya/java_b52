import java.io.File;

public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, world! Привет!");

        var configFile = new File("sandbox/settings.gradle");
        System.out.println(configFile.getAbsolutePath());
        System.out.println(configFile.exists());
    }
}

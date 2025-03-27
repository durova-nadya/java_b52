package common;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class CommonFunctions {
    public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char)('a' + rnd.nextInt(26));
        }
        return result;
    }

    public static String randomFile(String dir) {
        File directory = new File(dir);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Указанный путь не существует или не является директорией: " + dir);
        }
       var fileNames = new File(dir).list();
       var rnd = new Random();
       var index = rnd.nextInt(fileNames.length);
       return Paths.get(dir, fileNames[index]).toString();
    }
}

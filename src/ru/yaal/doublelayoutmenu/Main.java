package ru.yaal.doublelayoutmenu;


import java.io.File;
import java.io.IOException;

/**
 * Запускает приложение.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        File entryDir = new File(System.getProperty("user.home"), ".local/share/applications");
        File commonEntryDir = new File("/usr/share/applications");
        new Application(entryDir, commonEntryDir).execute();
    }
}
package ru.yaal.doublelayoutmenu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

class Helper {
     static String content(String name, String comment) {
        return "[Desktop Entry]\n" +
                "Name=" + name + "\n" +
                "Comment=" + comment + "\n" +
                "Exec=env PULSE_LATENCY_MSEC=60 skype %U\n" +
                "Icon=skype.png\n" +
                "Terminal=false\n" +
                "Type=Application\n" +
                "Encoding=UTF-8\n" +
                "Categories=Network;Application;\n" +
                "MimeType=x-scheme-handler/skype;\n" +
                "X-KDE-Protocols=skype";
    }

    static File createEntryFile(String content) throws IOException {
        File entryFile = File.createTempFile("DoubleLayoutMenu_", ".tmp");
        entryFile.deleteOnExit();

        Files.write(entryFile.toPath(), Arrays.asList(content));
        return entryFile;
    }
}

package ru.yaal.doublelayoutmenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;

class Helper {
    private static final Logger LOG = LoggerFactory.getLogger(Helper.class);

    static String content(String name, String comment) {
        String commentLine = comment != null ? "Comment=" + comment + "\n" : "";
        return "#My desktop entry file\n" +
                "[Header Section]\n" +
                "Name=Header\n" +
                "Comment=Super header\n" +
                "[Desktop Entry]\n" +
                "Name=" + name + "\n" +
                commentLine +
                "Exec=env PULSE_LATENCY_MSEC=60 skype %U\n" +
                "Icon=skype.png\n" +
                "Terminal=false\n" +
                "Type=Application\n" +
                "Encoding=UTF-8\n" +
                "Categories=Network;Application;\n" +
                "MimeType=x-scheme-handler/skype;\n" +
                "X-KDE-Protocols=skype\n" +
                "[Bottom Section]\n" +
                "Name=Footer\n" +
                "Comment=Super footer\n";
    }

    static File createEntryFile(File dir, String content) throws IOException {
        File entryFile = Files.createTempFile(dir.toPath(), "DoubleLayoutMenu_", ".desktop").toFile();
        entryFile.deleteOnExit();
        Files.write(entryFile.toPath(), Collections.singletonList(content));
        return entryFile;
    }

    static File createEntryFile(String content) throws IOException {
        File entryFile = File.createTempFile("DoubleLayoutMenu_", ".desktop");
        LOG.info("Created file: " + entryFile.getAbsolutePath());
        entryFile.deleteOnExit();
        Files.write(entryFile.toPath(), Collections.singletonList(content));
        return entryFile;
    }
}

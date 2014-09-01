package ru.yaal.doublelayoutmenu;

import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.testng.Assert.*;

public class EntryPersistHelperTest {

    @Test
    public void read() throws Exception {
        final String expName = "Skype";
        final String expComment = "Skype Internet Telephony";
        final String expContent = "[Desktop Entry]\n" +
                "Name=" + expName + "\n" +
                "Comment=" + expComment + "\n" +
                "Exec=env PULSE_LATENCY_MSEC=60 skype %U\n" +
                "Icon=skype.png\n" +
                "Terminal=false\n" +
                "Type=Application\n" +
                "Encoding=UTF-8\n" +
                "Categories=Network;Application;\n" +
                "MimeType=x-scheme-handler/skype;\n" +
                "X-KDE-Protocols=skype";

        final File entryFile = File.createTempFile("DoubleLayoutMenu_", ".tmp");
        entryFile.deleteOnExit();
        Path entryPath = entryFile.toPath();

        Files.write(entryPath, Arrays.asList(expContent));

        Entry entry = EntryPersistHelper.read(entryPath.toFile());

        assertEquals(entry.getName(), expName);
        assertEquals(entry.getComment(), expComment);
        assertEquals(entry.getFile(), entryFile);
    }

    @Test
    public void save() throws Exception {

    }
}
package ru.yaal.doublelayoutmenu;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static ru.yaal.doublelayoutmenu.Helper.content;

public class EntryPersistHelperTest {

    private File entryFile;
    private Path entryPath;
    private String name = "Skype";
    private String comment = "Skype Internet Telephony";

    @BeforeMethod
    public void setUp() throws Exception {
        entryFile(content(name, comment));
    }

    private void entryFile(String content) throws IOException {
        entryFile = File.createTempFile("DoubleLayoutMenu_", ".tmp");
        entryFile.deleteOnExit();
        entryPath = entryFile.toPath();

        Files.write(entryPath, Arrays.asList(content));
    }

    @Test
    public void read() throws Exception {
        Entry entry = EntryPersistHelper.read(entryPath.toFile());
        assertEquals(entry.getName(), name);
        assertEquals(entry.getComment(), comment);
        assertEquals(entry.getFile(), entryFile);
    }

    @Test
    public void save() throws Exception {
        //expected
        entryFile(content("Telegram", "Telegram Messenger"));

        Properties expProperties = new Properties();
        expProperties.load(new FileInputStream(entryFile));
        expProperties.setProperty("Name", name);
        expProperties.setProperty("Comment", comment);

        //actual
        Entry entry = EntryPersistHelper.read(entryPath.toFile());
        entry.setName(name);
        entry.setComment(comment);

        EntryPersistHelper.save(entry);

        Properties actProperties = new Properties();
        actProperties.load(new FileInputStream(entry.getFile()));

        //verify
        assertEquals(actProperties, expProperties);
    }
}
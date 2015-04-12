package ru.yaal.doublelayoutmenu;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;
import static org.testng.Assert.assertEquals;
import static ru.yaal.doublelayoutmenu.Helper.content;
import static ru.yaal.doublelayoutmenu.Helper.createEntryFile;

public class EntryPersistHelperTest {

    private final String name = "Skype";
    private final String comment = "Skype Internet Telephony";

    @Test
    public void read() throws Exception {
        File entryFile = createEntryFile(content(name, comment));
        Entry entry = EntryPersistHelper.read(entryFile);
        assertEquals(entry.getName(), name);
        assertEquals(entry.getComment(), comment);
        assertEquals(entry.getFile(), entryFile);
    }

    @Test
    public void readEmptyName() throws Exception {
        File entryFile = createEntryFile(content("", comment));
        Entry entry = EntryPersistHelper.read(entryFile);
        assertThat(entry.getName(), isEmptyString());
        assertEquals(entry.getComment(), comment);
        assertEquals(entry.getFile(), entryFile);
    }

    @Test
    public void readEmptyComment() throws Exception {
        File entryFile = createEntryFile(content(name, ""));
        Entry entry = EntryPersistHelper.read(entryFile);
        assertEquals(entry.getName(), name);
        assertThat(entry.getComment(), isEmptyString());
        assertEquals(entry.getFile(), entryFile);
    }

    @Test
    public void save() throws Exception {
        File entryFile = createEntryFile(content(name, comment));

        Properties expProperties = new Properties();
        expProperties.load(new FileInputStream(entryFile));

        Entry entry = EntryPersistHelper.read(entryFile);
        entry.setName(name);
        entry.setComment(comment);

        EntryPersistHelper.save(entry);

        Properties actProperties = new Properties();
        actProperties.load(new FileInputStream(entry.getFile()));

        assertEquals(actProperties, expProperties);
    }

    /**
     * В исходном файле не было свойства Comment,
     * значит оно должно быть добавлено.
     */
    @Test
    public void saveNoCommentLine() throws Exception {
        File entryFile = createEntryFile(content(name, null));

        Properties expProperties = new Properties();
        expProperties.load(new FileInputStream(entryFile));
        expProperties.setProperty("Comment", comment);

        Entry entry = EntryPersistHelper.read(entryFile);
        entry.setName(name);
        entry.setComment(comment);

        EntryPersistHelper.save(entry);

        Properties actProperties = new Properties();
        actProperties.load(new FileInputStream(entry.getFile()));

        assertEquals(actProperties, expProperties);
    }
}
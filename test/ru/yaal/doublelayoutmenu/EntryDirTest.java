package ru.yaal.doublelayoutmenu;

import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static ru.yaal.doublelayoutmenu.Helper.content;

public class EntryDirTest {

    @Test
    public void getEntries() throws Exception {
        File dir = Files.createTempDirectory("DoubleLayoutMenu_").toFile();
        String name1 = "Firefox";
        String comment1 = "Super Browser";
        File entryFile = Helper.createEntryFile(dir, content(name1, comment1));
        String name2 = "Chrome";
        String comment2 = "Good multimedia";
        File entryFile2 = Helper.createEntryFile(dir, content(name2, comment2));
        dir.deleteOnExit();

        Files.createTempFile(dir.toPath(), "DoubleLayoutMenu", ".tmp");

        List<Entry> entries = EntryDir.getEntries(dir);
        assertEquals(entries.size(), 2);

        final Entry actEntry1 = EntryPersistHelper.read(entryFile);
        final Entry actEntry2 = EntryPersistHelper.read(entryFile2);

        assertThat(actEntry1.getName(), anyOf(equalTo(name1), equalTo(name2)));
        assertThat(actEntry1.getComment(), anyOf(equalTo(comment1), equalTo(comment2)));
        assertThat(actEntry2.getName(), anyOf(equalTo(name1), equalTo(name2)));
        assertThat(actEntry2.getComment(), anyOf(equalTo(comment1), equalTo(comment2)));
    }
}
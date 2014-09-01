package ru.yaal.doublelayoutmenu;

import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ru.yaal.doublelayoutmenu.Helper.content;

public class EntryDirTest {

    @Test
    public void getEntries() throws Exception {
        File dir = Files.createTempDirectory("DoubleLayoutMenu_").toFile();
        File entryFile = Helper.createEntryFile(dir, content("Firefox", "Super Browser"));
        File entryFile2 = Helper.createEntryFile(dir, content("Chrome", "Good multimedia"));
        dir.deleteOnExit();

        Files.createTempFile(dir.toPath(), "DoubleLayoutMenu", ".tmp");

        List<Entry> entries = EntryDir.getEntries(dir);
        assertEquals(entries.size(), 2);

        final Entry entry1 = EntryPersistHelper.read(entryFile);
        final Entry entry2 = EntryPersistHelper.read(entryFile2);
        boolean containsEntry1 = entry1.getName().equals(entries.get(0).getName())
                || entry1.getName().equals(entries.get(1).getName());
        boolean containsEntry2 = entry2.getName().equals(entries.get(0).getName())
                || entry2.getName().equals(entries.get(1).getName());
        assertTrue(containsEntry1);
        assertTrue(containsEntry2);
    }
}
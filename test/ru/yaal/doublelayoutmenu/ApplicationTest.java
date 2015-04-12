package ru.yaal.doublelayoutmenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;

import static org.testng.Assert.assertEquals;

public class ApplicationTest {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationTest.class);

    @Test
    public void testExecute() throws Exception {
        File entryDir = Files.createTempDirectory("DoubleLayoutMenu_").toFile();
        LOG.info("Entry dir: " + entryDir);

        File entry1File = File.createTempFile("Entry_", ".desktop", entryDir);
        Entry entry1 = new Entry("Entry1", "Comment to entry1", entry1File);
        EntryPersistHelper.save(entry1);

        Application app = new Application(entryDir);
        app.execute();

        Entry actEntry = EntryPersistHelper.read(entry1File);
        assertEquals(actEntry.getName(), entry1.getName());
        assertEquals(actEntry.getComment(), entry1.getComment()  + " ~Утекн1~");
    }
}
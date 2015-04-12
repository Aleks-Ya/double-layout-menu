package ru.yaal.doublelayoutmenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ApplicationTest {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationTest.class);

    @Test
    public void testExecute() throws Exception {
        File entryDir = Files.createTempDirectory("DoubleLayoutMenu_").toFile();
        entryDir.deleteOnExit();
        LOG.info("Entry dir: " + entryDir);

        File entry1File = File.createTempFile("Entry_", ".desktop", entryDir);
        entry1File.deleteOnExit();
        String expName = "Entry1";
        String expComment = "Comment to entry1";
        List<String> expLines = Arrays.asList("Name=" + expName, "Comment=" + expComment);
        Files.write(entry1File.toPath(), expLines);

        Application app = new Application(entryDir);
        app.execute();

        Entry actEntry = EntryPersistHelper.read(entry1File);
        assertEquals(actEntry.getName(), expName);
        assertEquals(actEntry.getComment(), expComment + " ~Утекн1~");
    }
}
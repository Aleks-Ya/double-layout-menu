package ru.yaal.doublelayoutmenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 * Сохраняет и читает пункт меню из файла.
 */
class EntryPersistHelper {
    private static final Logger LOG = LoggerFactory.getLogger(EntryPersistHelper.class);
    private static final String NAME_PROPERTY = "Name=";
    private static final String COMMENT_PROPERTY = "Comment=";
    private static final String DESKTOP_ENTRY_GROUP = "[Desktop Entry]";

    /**
     * todo Читать только из секции [Desktop Entry]
     */
    static Entry read(File entryFile) {
        Entry newEntry = new Entry();
        newEntry.setFile(entryFile);
        try {
            List<String> lines = Files.readAllLines(entryFile.toPath());
            boolean inDesktopEntryGroup = false;
            for (String fileLine : lines) {
                String line = fileLine.trim();
                if (inDesktopEntryGroup && line.startsWith("[")) {
                    inDesktopEntryGroup = false;
                }
                if (line.startsWith(DESKTOP_ENTRY_GROUP)) {
                    inDesktopEntryGroup = true;
                }
                if (inDesktopEntryGroup) {
                    if (line.startsWith(NAME_PROPERTY)) {
                        String[] texts = line.split("=");
                        String newText = (texts.length >= 2) ? texts[1] : "";
                        newEntry.setName(newText);
                    }
                    if (line.startsWith(COMMENT_PROPERTY)) {
                        String[] texts = line.split("=");
                        String newText = (texts.length >= 2) ? texts[1] : "";
                        newEntry.setComment(newText);
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Can't read: " + entryFile.getAbsolutePath(), e);
        }
        return newEntry;
    }

    static void save(Entry entry) throws IOException {
        List<String> fileContent = Files.readAllLines(entry.getFile().toPath());
        final String newComment = COMMENT_PROPERTY + entry.getComment();
        boolean inDesktopEntryGroup = false;
        for (int i = 0; i < fileContent.size(); i++) {
            String line = fileContent.get(i).trim();
            if (inDesktopEntryGroup && line.startsWith("[")) {
                fileContent.add(i, newComment);
                break;
            }
            if (line.startsWith(DESKTOP_ENTRY_GROUP)) {
                inDesktopEntryGroup = true;
            }
            if (inDesktopEntryGroup) {
                if (line.startsWith(COMMENT_PROPERTY)) {
                    fileContent.set(i, newComment);
                    break;
                }
            }
        }
        Files.write(entry.getFile().toPath(), fileContent, Charset.defaultCharset());
    }
}
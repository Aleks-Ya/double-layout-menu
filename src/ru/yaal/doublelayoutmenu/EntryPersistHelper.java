package ru.yaal.doublelayoutmenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    /**
     * todo Читать только из секции [Desktop Entry]
     */
    static Entry read(File entryFile) {
        Entry newEntry = null;
        try (BufferedReader br = new BufferedReader(new FileReader(entryFile))) {
            newEntry = new Entry();
            newEntry.setFile(entryFile);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith(NAME_PROPERTY)) {
                    String[] texts = line.split("=");
                    String newText = (texts.length >= 2) ? texts[1] : "";
                    newEntry.setName(newText);
                }
                if (line.trim().startsWith(COMMENT_PROPERTY)) {
                    String[] texts = line.split("=");
                    String newText = (texts.length >= 2) ? texts[1] : "";
                    newEntry.setComment(newText);
                }
            }
        } catch (Exception e) {
            LOG.error("Can't read: " + entryFile.getAbsolutePath(), e);
        }
        return newEntry;
    }

    static void save(Entry entry) throws IOException {
        List<String> fileContent = Files.readAllLines(entry.getFile().toPath());
        boolean commentFound = false;
        String newComment = COMMENT_PROPERTY + entry.getComment();
        for (int i = 0; i < fileContent.size(); i++) {
            String line = fileContent.get(i).trim();
            if (line.startsWith(COMMENT_PROPERTY)) {
                fileContent.set(i, newComment);
                commentFound = true;
                break;
            }
        }
        if (!commentFound) {
            fileContent.add(newComment);
        }
        Files.write(entry.getFile().toPath(), fileContent, Charset.defaultCharset());
    }
}
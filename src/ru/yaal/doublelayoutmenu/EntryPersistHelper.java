package ru.yaal.doublelayoutmenu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Сохраняет и читает пункт меню из файла.
 */
class EntryPersistHelper {
    private static final String NAME_PROPERTY = "Name";
    private static final String COMMENT_PROPERTY = "Comment";
    private static final BackupHelper BACKUP_HELPER = new BackupHelper();

    static Entry read(File entryFile) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(entryFile));
        return new Entry(properties.getProperty(NAME_PROPERTY), properties.getProperty(COMMENT_PROPERTY), entryFile);
    }

    static void save(Entry entry) throws IOException {
        BACKUP_HELPER.backup(entry.getFile());
        Properties properties = new Properties();
        File entryFile = entry.getFile();
        final FileInputStream fis = new FileInputStream(entryFile);
        properties.load(fis);
        properties.setProperty(NAME_PROPERTY, entry.getName());
        properties.setProperty(COMMENT_PROPERTY, entry.getComment());
        properties.store(new FileOutputStream(entryFile), "[Desktop Entry]");
        fis.close();
    }
}
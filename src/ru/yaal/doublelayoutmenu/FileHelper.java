package ru.yaal.doublelayoutmenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Делает резервную копию файла пункта меню.
 */
class FileHelper {
    private static final Logger LOG = LoggerFactory.getLogger(FileHelper.class);

    @Deprecated
    static void backup(File file) throws IOException {
        final String fileName = file.getName();
        String backupFileName = fileName + ".bak";
        File backupFile = new File(file.getParent(), backupFileName);
        if (backupFile.exists()) {
            int counter = 0;
            do {
                counter++;
                backupFile = new File(file.getParent(), backupFileName + counter);
            } while (backupFile.exists());
        }
        Files.copy(file.toPath(), backupFile.toPath());
    }

    static void backupDir(File dir) throws IOException {
        if (!dir.isDirectory()) {
            throw new IOException("Is not a directory: " + dir.getAbsolutePath());
        }
        final String dirName = dir.getName();
        String backupDirName = dirName + ".bak";
        File backupDir = new File(dir.getParent(), backupDirName);
        if (backupDir.exists()) {
            int counter = 0;
            do {
                counter++;
                backupDir = new File(dir.getParent(), backupDirName + counter);
            } while (backupDir.exists());
        }
        Files.copy(dir.toPath(), backupDir.toPath());
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                File backupFile = new File(backupDir, file.getName());
                Files.copy(file.toPath(), backupFile.toPath());
                LOG.info("File was backed up: " + backupFile.getAbsolutePath());
            }
        }
        LOG.info("Backup dir: " + backupDir.getAbsolutePath());
    }
}
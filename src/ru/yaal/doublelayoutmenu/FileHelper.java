package ru.yaal.doublelayoutmenu;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

class FileHelper {
    private static final Logger LOG = LoggerFactory.getLogger(FileHelper.class);

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
        FileUtils.copyDirectory(dir, backupDir);
        LOG.info("Backup dir: " + backupDir.getAbsolutePath());
    }

    static void copyDesktops(File fromDir, File toDir) throws IOException {
        LOG.info("Copy from: " + fromDir);
        LOG.info("Copy to: " + toDir);
        if (!fromDir.exists()) {
            throw new IOException("FromDir doesn't exists: " + fromDir.getAbsolutePath());
        }
        if (!toDir.exists()) {
            throw new IOException("ToDir doesn't exists: " + toDir.getAbsolutePath());
        }
        FileUtils.copyDirectory(fromDir, toDir, EntryDir.DESKTOP_FILE_FILTER);
    }
}
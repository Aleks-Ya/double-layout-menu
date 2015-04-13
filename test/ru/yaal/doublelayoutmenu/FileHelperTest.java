package ru.yaal.doublelayoutmenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FileHelperTest {
    private static final Logger LOG = LoggerFactory.getLogger(FileHelperTest.class);

    @Test
    public void backupDir() throws Exception {
        File dir = Files.createTempDirectory("BackupHelper_").toFile();
        LOG.info("Original dir: " + dir.getAbsolutePath());
        String fileName = "FileForBackup.txt";
        File createdFile = Files.createFile(Paths.get(dir.getAbsolutePath(), fileName)).toFile();
        LOG.info("File for backup: " + createdFile.getAbsolutePath());

        FileHelper.backupDir(dir);

        File backupDir = new File(dir.getParent(), dir.getName() + ".bak");
        assertTrue(backupDir.exists());
        File backupFile = new File(backupDir, fileName);
        LOG.info("Backup file: " + backupFile.getAbsolutePath());
        assertTrue(backupFile.exists());
    }


    @Test
    public void copyDesktops() throws Exception {
        File source = Files.createTempDirectory("CopyDesktops_").toFile();
        LOG.info("Source: " + source.getAbsolutePath());
        String desktopFileName = "Firefox.desktop";
        File desktopFile = Files.createFile(Paths.get(source.getAbsolutePath(), desktopFileName)).toFile();
        LOG.info("Desktop file: " + desktopFile.getAbsolutePath());

        String otherFileName = "about.txt";
        File otherFile = Files.createFile(Paths.get(source.getAbsolutePath(), otherFileName)).toFile();
        LOG.info("Other file: " + otherFile.getAbsolutePath());


        File destDir = Files.createTempDirectory("CopyDesktops_").toFile();
        LOG.info("Destination: " + destDir.getAbsolutePath());

        FileHelper.copyDesktops(source, destDir);

        assertTrue(new File(destDir, desktopFileName).exists());
        assertFalse(new File(destDir, otherFileName).exists());
    }
}
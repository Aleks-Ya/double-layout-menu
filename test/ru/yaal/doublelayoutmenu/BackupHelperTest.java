package ru.yaal.doublelayoutmenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertTrue;

public class BackupHelperTest {
    private static final Logger LOG = LoggerFactory.getLogger(BackupHelperTest.class);

    @Test
    public void backup() throws Exception {
        File origin = File.createTempFile("DoubleLayoutMenu_BackupHelperTest_", ".tmp");

        BackupHelper.backup(origin);
        assertTrue(new File(origin.getAbsolutePath() + ".bak").exists());

        BackupHelper.backup(origin);
        assertTrue(new File(origin.getAbsolutePath() + ".bak1").exists());

        BackupHelper.backup(origin);
        assertTrue(new File(origin.getAbsolutePath() + ".bak2").exists());
    }

    @Test
    public void backupDir() throws Exception {
        File dir = Files.createTempDirectory("BackupHelper_").toFile();
        LOG.info("Original dir: " + dir.getAbsolutePath());
        String fileName = "FileForBackup.txt";
        File createdFile = Files.createFile(Paths.get(dir.getAbsolutePath(), fileName)).toFile();
        LOG.info("File for backup: " + createdFile.getAbsolutePath());

        BackupHelper.backupDir(dir);

        File backupDir = new File(dir.getParent(), dir.getName() + ".bak");
        assertTrue(backupDir.exists());
        File backupFile = new File(backupDir, fileName);
        LOG.info("Backup file: " + backupFile.getAbsolutePath());
        assertTrue(backupFile.exists());
    }
}
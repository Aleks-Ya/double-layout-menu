package ru.yaal.doublelayoutmenu;

import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

public class BackupHelperTest {

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
}
package ru.yaal.doublelayoutmenu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Делает резервную копию файла пункта меню.
 */
class BackupHelper {
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
}
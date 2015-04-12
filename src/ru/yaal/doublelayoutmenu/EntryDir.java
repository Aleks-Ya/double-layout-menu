package ru.yaal.doublelayoutmenu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Читает пункты меню, находящиеся в данной папке.
 */
class EntryDir {
    static List<Entry> getEntries(File entryDir) {
        List<Entry> result = new ArrayList<>();
        File[] entryFiles = entryDir.listFiles(new DesktopFilenameFilter());
        for (File entryFile : entryFiles) {
            result.add(EntryPersistHelper.read(entryFile));
        }
        return result;
    }
}
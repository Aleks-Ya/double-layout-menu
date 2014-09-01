package ru.yaal.doublelayoutmenu;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Читает пункты меню, находящиеся в данной папке.
 */
class EntryDir {
    static List<Entry> getEntries(File entryDir) throws IOException {
        List<Entry> result = new ArrayList<>();
        File[] entryFiles = entryDir.listFiles(new DesktopFilenameFilter());
        for (File entryFile : entryFiles) {
            result.add(EntryPersistHelper.read(entryFile));
        }
        return result;
    }

    private static class DesktopFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".desktop");
        }
    }
}
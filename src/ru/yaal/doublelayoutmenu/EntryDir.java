package ru.yaal.doublelayoutmenu;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Читает пункты меню, находящиеся в данной папке.
 */
class EntryDir {
        public static final IOFileFilter DESKTOP_FILE_FILTER = new SuffixFileFilter(".desktop");

    static List<Entry> getEntries(File entryDir) {
        List<Entry> result = new ArrayList<>();
        Collection<File> entryFiles = FileUtils.listFiles(entryDir, DESKTOP_FILE_FILTER, TrueFileFilter.INSTANCE);

        for (File entryFile : entryFiles) {
            result.add(EntryPersistHelper.read(entryFile));
        }
        return result;
    }
}
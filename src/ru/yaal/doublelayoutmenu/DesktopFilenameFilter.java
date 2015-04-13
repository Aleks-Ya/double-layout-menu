package ru.yaal.doublelayoutmenu;

import java.io.File;
import java.io.FilenameFilter;

class DesktopFilenameFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(".desktop");
    }
}

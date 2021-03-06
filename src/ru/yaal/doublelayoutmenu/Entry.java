package ru.yaal.doublelayoutmenu;

import java.io.File;

/**
 * Пункт меню Ubuntu.
 */
class Entry {
    private String name;
    private String comment;
    private File file;

    public Entry() {
    }

    public String getName() {
        return name != null ? name : "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment != null ? comment : "";
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Entry[" + file.getName() + "]";
    }
}
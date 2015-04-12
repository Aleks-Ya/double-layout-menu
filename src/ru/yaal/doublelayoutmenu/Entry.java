package ru.yaal.doublelayoutmenu;

import java.io.File;

/**
 * Пункт меню Ubuntu.
 */
class Entry {
    private String name;
    private String comment;
    private File file;

    public Entry(String name, String comment, File file) {
        this.name = name;
        this.comment = comment;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
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
package ru.yaal.doublelayoutmenu;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Использует классы приложения для достижения нужного результата.
 */
class Application {
    private static final File ENTRY_DIR = new File("/usr/share/applications");
    private final Statistic statistic = new Statistic();

    void execute() throws IOException {
        if (!ENTRY_DIR.canRead()) {
            throw new IOException("Can't read " + ENTRY_DIR.getAbsolutePath());
        }
        List<Entry> entries = EntryDir.getEntries(ENTRY_DIR);
        for (Entry entry : entries) {
            String comment = entry.getComment();
            String rusName = LayoutConverter.engToRus(entry.getName());
            if (!comment.contains(rusName)) {
                entry.setComment(comment + " " + rusName);
                if (entry.getFile().canWrite()) {
                    EntryPersistHelper.save(entry);
                    statistic.incProcessed(entry);
                } else {
                    System.err.println("Can't write to " + entry.getFile().getAbsolutePath());
                }
            } else {
                statistic.incPassed(entry);
            }
        }

        System.out.println(StatisticFormatter.format(statistic));
    }
}
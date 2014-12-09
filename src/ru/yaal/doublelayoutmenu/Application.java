package ru.yaal.doublelayoutmenu;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Использует классы приложения для достижения нужного результата.
 * todo Cover unit tests
 */
class Application {
    private final File entryDir;
    private final Statistic statistic = new Statistic();

    Application(File entryDir) {
        this.entryDir = entryDir;
    }

    void execute() throws IOException {
        if (!entryDir.canRead()) {
            throw new IOException("Can't read " + entryDir.getAbsolutePath());
        }
        List<Entry> entries = EntryDir.getEntries(entryDir);
        for (Entry entry : entries) {
            String comment = entry.getComment();
            String rusName = StringLayoutConverter.engToRus(entry.getName());
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
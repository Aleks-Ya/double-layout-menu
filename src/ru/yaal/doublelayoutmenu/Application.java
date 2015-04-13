package ru.yaal.doublelayoutmenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Использует классы приложения для достижения нужного результата.
 */
class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    private final File entryDir;
    private final File commonEntryDir;
    private final Statistic statistic = new Statistic();

    Application(File userEntryDir, File commonEntryDir) {
        this.entryDir = userEntryDir;
        this.commonEntryDir = commonEntryDir;
    }

    void execute() throws IOException {
        if (!entryDir.canRead()) {
            throw new IOException("Can't read: " + commonEntryDir.getAbsolutePath());
        }
        if (!entryDir.canWrite()) {
            throw new IOException("Can't write: " + entryDir.getAbsolutePath());
        }
        FileHelper.backupDir(entryDir);
        FileHelper.copyDesktops(commonEntryDir, entryDir);
        List<Entry> entries = EntryDir.getEntries(entryDir);
        for (Entry entry : entries) {
            LOG.info("Process entry: " + entry.getName());
            String comment = entry.getComment();
            String rusName = "~" + LayoutConverter.engToRus(entry.getName()) + "~";
            if (!comment.contains(rusName)) {
                entry.setComment(comment + " " + rusName);
                if (entry.getFile().canWrite()) {
                    EntryPersistHelper.save(entry);
                    statistic.incProcessed(entry);
                } else {
                    LOG.warn("Can't write to " + entry.getFile().getAbsolutePath());
                }
            } else {
                LOG.info("Passed: " + entry);
                statistic.incPassed(entry);
            }
        }
        System.out.println(StatisticFormatter.format(statistic));
    }
}
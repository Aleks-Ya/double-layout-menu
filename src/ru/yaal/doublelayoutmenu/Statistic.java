package ru.yaal.doublelayoutmenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранение статистики работы программы.
 */
class Statistic {
    private final List<Entry> passed = new ArrayList<>();
    private final List<Entry> processed = new ArrayList<>();

    void incPassed(Entry entry) {
        passed.add(entry);
    }

    void incProcessed(Entry entry) {
        processed.add(entry);
    }

    public List<Entry> getPassed() {
        return passed;
    }

    public List<Entry> getProcessed() {
        return processed;
    }
}

package ru.yaal.doublelayoutmenu;

/**
 * Форматирует статистику.
 */
class StatisticFormatter {
    static String format(Statistic statistic) {
        return String.format("Passed: %s\n Processed: %s\n",
                statistic.getPassed(), statistic.getProcessed());
    }
}
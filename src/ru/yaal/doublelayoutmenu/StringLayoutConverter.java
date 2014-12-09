package ru.yaal.doublelayoutmenu;

/**
 * Конвертирует буквы из английской раскладки в русскую.
 */
class StringLayoutConverter {
    private static final CharLayoutConverter converter = new EngToRusCharLayoutConverter();

    static String engToRus(String rus) {
        StringBuilder result = new StringBuilder();
        for (char c : rus.toCharArray()) {
            result.append(converter.convert(c));
        }
        return result.toString();
    }
}
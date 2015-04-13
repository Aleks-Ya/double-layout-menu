package ru.yaal.doublelayoutmenu;

/**
 * Конвертирует буквы из английской раскладки в русскую.
 */
class LayoutConverter {

    static String engToRus(String rus) {
        StringBuilder result = new StringBuilder();
        for (char c : rus.toCharArray()) {
            result.append(convert(c));
        }
        return result.toString();
    }

    private static char convert(char original) {
        switch (original) {
            case 'a' : return 'ф';
            case 'A' : return 'Ф';
            case 'b' : return 'и';
            case 'B' : return 'И';
            case 'c' : return 'с';
            case 'C' : return 'С';
            case 'd' : return 'в';
            case 'D' : return 'В';
            case 'e' : return 'у';
            case 'E' : return 'У';
            case 'f' : return 'а';
            case 'F' : return 'А';
            case 'g' : return 'п';
            case 'G' : return 'П';
            case 'h' : return 'р';
            case 'H' : return 'Р';
            case 'i' : return 'ш';
            case 'I' : return 'Ш';
            case 'j' : return 'о';
            case 'J' : return 'О';
            case 'k' : return 'л';
            case 'K' : return 'Л';
            case 'l' : return 'д';
            case 'L' : return 'Д';
            case 'm' : return 'ь';
            case 'M' : return 'Ь';
            case 'n' : return 'т';
            case 'N' : return 'Т';
            case 'o' : return 'щ';
            case 'O' : return 'Щ';
            case 'p' : return 'з';
            case 'P' : return 'З';
            case 'q' : return 'й';
            case 'Q' : return 'Й';
            case 'r' : return 'к';
            case 'R' : return 'К';
            case 's' : return 'ы';
            case 'S' : return 'Ы';
            case 't' : return 'е';
            case 'T' : return 'Е';
            case 'u' : return 'г';
            case 'U' : return 'Г';
            case 'v' : return 'м';
            case 'V' : return 'М';
            case 'x' : return 'ч';
            case 'X' : return 'Ч';
            case 'y' : return 'н';
            case 'Y' : return 'Н';
            case 'w' : return 'ц';
            case 'W' : return 'Ц';
            case 'z' : return 'я';
            case 'Z' : return 'Я';
            case '[' : return 'х';
            case '{' : return 'Х';
            case ']' : return 'ъ';
            case '}' : return 'Ъ';
            case ';' : return 'ж';
            case ':' : return 'Ж';
            case '\'' : return 'э';
            case '"' : return 'Э';
            case ',' : return 'б';
            case '<' : return 'Б';
            case '.' : return 'ю';
            case '>' : return 'Ю';
            case '/' : return '.';
            case '?' : return ',';
            default: return original;
        }
    }
}
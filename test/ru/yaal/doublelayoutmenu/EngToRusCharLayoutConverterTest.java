package ru.yaal.doublelayoutmenu;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EngToRusCharLayoutConverterTest {
    private final EngToRusCharLayoutConverter converter = new EngToRusCharLayoutConverter();

    @Test
    public void convert() {
        final String srcLowerCase = "qwertyuiop[]asdfghjkl;'zxcvbnm,./1234567890";
        final String srcUpperCase = "QWERTYUIOP{}ASDFGHJKL:\"ZXCVBNM<>?1234567890";
        final String src = srcLowerCase + srcUpperCase;

        final String expLowerCase = "йцукенгшщзхъфывапролджэячсмитьбю.1234567890";
        final String expUpperCase = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ,1234567890";
        final String exp = expLowerCase + expUpperCase;

        assertEquals(srcLowerCase.length(), expLowerCase.length());
        assertEquals(srcUpperCase.length(), expUpperCase.length());
        assertEquals(src.length(), exp.length());

        final StringBuilder act = new StringBuilder();
        for (char c : src.toCharArray()) {
            act.append(converter.convert(c));
        }
        assertEquals(act.toString(), exp);
    }
}
package ru.yaal.doublelayoutmenu;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EngToRusCharLayoutConverterTest {
    private EngToRusCharLayoutConverter converter = new EngToRusCharLayoutConverter();

    @Test
    public void convert() {
        final String srcLowerCase = "qwertyuiop[]asdfghjkl;'zxcvbnm,./";
        final String srcUpperCase = "QWERTYUIOP{}ASDFGHJKL:\"ZXCVBNM<>?";
        final String src = srcLowerCase + srcUpperCase;

        final String expLowerCase = "йцукенгшщзхъфывапролджэячсмитьбю.";
        final String expUpperCase = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ,";
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
package ru.yaal.doublelayoutmenu;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LayoutConverterTest {

    @Test
    public void lowCase() {
        final String source = "qwertyuiop[]asdfghjkl;' zxcvbnm,./";
        final String exp = "йцукенгшщзхъфывапролджэ ячсмитьбю.";
        final String act = LayoutConverter.engToRus(source);
        assertEquals(act, exp);
    }

    @Test
    public void upperCase() {
        final String source = "QWERTYUIOP{}ASDFGHJKL:\" ZXCVBNM<>?";
        final String exp = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭ ЯЧСМИТЬБЮ,";
        final String act = LayoutConverter.engToRus(source);
        assertEquals(act, exp);
    }
}
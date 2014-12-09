package ru.yaal.doublelayoutmenu;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LayoutConverterTest {

    @Test
    public void lowCase() {
        final String source = "qwertyuiop[]asdfghjkl;'zxcvbnm,./";
        final String exp = "йцукенгшщзхъфывапролджэячсмитьбю.";
        final String act = LayoutConverter.engToRus(source);
        assertEquals(act, exp);
    }

    @Test
    public void upperCase() {
        final String source = "QWERTYUIOP[]ASDFGHJKL;'ZXCVBNM,./";
        final String exp = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ..";
        final String act = LayoutConverter.engToRus(source);
        assertEquals(act, exp);
    }
}
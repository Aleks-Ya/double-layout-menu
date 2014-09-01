package ru.yaal.doublelayoutmenu;

class Helper {
     static String content(String name, String comment) {
        return "[Desktop Entry]\n" +
                "Name=" + name + "\n" +
                "Comment=" + comment + "\n" +
                "Exec=env PULSE_LATENCY_MSEC=60 skype %U\n" +
                "Icon=skype.png\n" +
                "Terminal=false\n" +
                "Type=Application\n" +
                "Encoding=UTF-8\n" +
                "Categories=Network;Application;\n" +
                "MimeType=x-scheme-handler/skype;\n" +
                "X-KDE-Protocols=skype";
    }
}

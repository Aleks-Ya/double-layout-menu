double-layout-menu
==================

Поиск программ в главном меню в неправильной раскладке ("Ашкуащч" -> "Firefox").

Требования:
1. Linux (проверял на Ubuntu 14.04 и Linux Mint 17)
2. Gradle 2
3. Java 7

Запуск:
gradle run

Принцип работы:

1. Бэкап папки {user_home}/.local/share/applications  в {user_home}/.local/share/applications.bak
2. Копирование недостающих файлов .desktop из /usr/share/applications  в {user_home}/.local/share/applications 
3. Во всех файлах .desktop дополняет свойство Comment названием программы (свойство Name) в неправильной раскладке.
4. Linux в течение минуты подхватывает изменения
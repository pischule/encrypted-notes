1.Серверная часть программы:

1.1. хранит текстовые файлы,

1.2. генерирует случайный сеансовый ключ по запросу клиента

1.3 отправляет клиенту зашифрованный открытым ключом RSA сеансовый ключ.

1.4 отправляет клиенту зашифрованный сеансовым ключом текстовый файл 

2. Клиентская часть программы:

2.1 Генерирует и отправляет серверу открытый ключ RSA (единожды).

2.2 Отправляет серверу запрос с именем файла.

2.3 Расшифровывает сеансовый ключ при помощи закрытого ключа RSA.

2.4 Расшифровывает и отображает текстовый документ при помощи сеансового ключа.

2.5 Ключ RSA сохраняется (генерируется по нажатию кнопки). Придумать свой метод хранения закрытого ключа.  
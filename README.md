This is task#18 from Hillel computer school in JavaPro group.

This task is about unit testing.

It is necessary for program working to create two CSV files
"./uploads/order_1.csv" and "./uploads/order_2.csv". Delimiter of row in CSV
is end of line. Delimiter of column in CSV is ";".

Program groups orders (wares) by shops and stores them in
"./downloads/<shop_name>.csv" file. Then program creates file
"./downloads/summary.csv" and stores there average price of ware in different
shops and total quantity of ware in all shops.

Required structure of files "./uploads/order_<i>.csv" (i is number of file).

ИМЯ;НАИМЕНОВАНИЕ;ЦЕНА;ШТ;
АТБ;Гречка;30.25;120;
АТБ;Сахар;21.25;170;
Сильпо;Гречка;31.25;24;
Сильпо;Сахар;22.20;107;
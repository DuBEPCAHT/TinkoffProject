# TinkoffProject
Тестовое задание для банка Тинькофф

***Задание***

Реализовать на Java 1.8+ soap web-service, содержащий в себе метод Result findNumber(Integer number),
ищущий в 20 разных по составу больших текстовых файлах (каждый должен быть порядка гб.) полученное на
вход число n. Файлы состоят только из чисел, которые разделены между собой запятой. Результат работы
необходимо записать в таблицу БД и вернуть объект Result в вызывающую систему.

***Решение***

Стек: Java 8, Spring (Web, JPA, Security)
База данных: PostgreSQL
Плагины: Project Lombok

Комментарий: Я позволил себе реализовать эту задачу через REST сервис. Так как сичитаю что soap тут избыточен.

1) В Postgre cоздал базу данных TinkoffDB (port:5432) login:postgres password:admin . Организовал конектор с помощью Spring JPA.
2) Реальзовал модель Result, к ней репозиторий и сервис.
3) Реализовал контроллер с end-point /findNumber который принимает в качестве параметра "number", цело число для поиска в файлах. Сервер приложения поднимается на 8050 порту. Пример запроса: http://localhost:8050/findNumber?number=34453
4) Реализов класс логики FileServie с рядом утилитных методов. 
Метод createInstanseFiles(int n) создаёт в папке resources/static 20 файлов где n кол-во чисел. Числа генерируются случайно от 1 до 1 000 000 и разделятся запятыми.
Для создания файлов размером приблизительно 1ГБ каждый рекомендую использовать значение 120 000 000. т.е. 120 миллионов чисел в каждом файле.
Метод findNumber(int number) принимает число и ищет его в каждом файле. На основе результата создает сущность Result и заполняется поле FileNames если хотя бы в одном файле было обнаружено совпадение.
5) Подкючил логгер.

***Цикл исполнения приложения***

Стартуем приложение с класса TinkoffProjectApplication.
Делаем несколько запросов наподобие (http://localhost:8050/findNumber?number=34453). Для удобства рекомендую использовать Advanced Rest Client или Postman.
Смотрм в базу данных. Там создалась таблица Result с результатами в соответствии с условиями задачи.
Смотрим в консоль на логи для полного понимания поседовательности исполнения программы.


P.S. Код для генерации текстовых файлов находится в классе FileServie в методе createInstanseFiles(int n). Для генерации 20 текстовых файлов можете раскомментировать 21 строку в главном методе TinkoffProjectApplication и запустить приложение, после чего закоментируйте эту строку снова так как генерацию файлов необходимо делать единоразово. JPA сконфигурированно таким образом что таблица Result в базе создается автоматически если её нет. 

Код для создания таблицы руками:
***
`CREATE TABLE result (
    id          bigint NOT NULL PRIMARY KEY,
    code        integer,
    number      varchar(50),
    filenames   varchar(100),
    error       varchar(100)
);`
***

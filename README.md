# учебный проект "Библиотека"

Учебный проект, в котором реализуется базовые возможности учета книг, выданных пользователям.

Для реализации использовались Spring 6, thymeleaf и Postgresql.

В данной версии реализована работа с базой данных через ORM-фреймворк hibernate.

Запросы реализованы как через встроенные CRUD-функции (persist,get,remove), 
так и через HQL-запросы через методы createSelectionQuery и createMutationQuery.

Основными целями реализации проекта было получение опыта в работе с:
* Spring MVC;
* HTTP-запросами;
* Hibernate и HQL-запросами.

## Установка

1. Необходимо заполнить заранее подготовленный hibernate.properties;
2. Создать базу данных и создать таблицы c данными (в /resources есть файл "sql.txt" с шаблоном нужных запросов);
3. запустить приложение на локальном сервере.
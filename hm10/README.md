# Задания

## Задание 1
Для аутентифицированных пользователей выводите в Header не только имя, но и логин. Добавьте в футер вывод статистики: количество зарегистрированных пользователей и количество постов. Обратите внимание, что для этого не нужно передавать в футер сами посты и пользователей, достаточно только их количества.

## Задание 2
Добавьте возможность регистрации. Для этого изучите как устроено добавление поста. Вам надо реализовать аналогичную функциональность - добавить пользователя. В форме регистрации нужно указать login и name. Провалидируйте login (длина от 3 до 16, только строчные латинские буквы, логин уникален). Провалидируйте имя (длина от 1 до 32). После регистрации должно редиректить на страницу со входом. Форма регистрации должна визуально быть очень похожа на форму входа.

Сделайте так, чтобы в хидере для аутентифицированного пользователя отображалось не только его имя, но имя и логин в скобках. Например, “Gennady Korotkevich (tourist)”. 

## Задание 3
Сделайте страницу со списком пользователей. Страница должна быть доступна и не аутентифицированным пользователям (анонимам). Расположите там просто типичную таблицу (наш datatable) с колонками id, login, name.

## Задание 4
Добавьте на главную (Index) вывод всех постов (по убыванию id). Выводите посты в типичном виде (как article, используйте разметку из предыдущих заданий). Выводите правильное количество комментариев.

## Задание 5
Сделайте отдельную страницу поста. Сделайте туда переход по клику на View all в сайдбаре или по заголовку поста на главной. Выведите на этой странице сам пост и комментарии к нему. Не допускайте копи-пейста при отображении одного поста с пунктом 4.

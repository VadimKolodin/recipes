# Лабораторная 3

Сделан бекенд приложения на платформе Spring. 
1. В качестве БД - postgresql
2. ORM - hibernate
3. Application Server - встроенный Tomcat
4. Также использовались jackson, mapstruct, lombok 

Приложение принимает/отвечает на json/xml.
Организация модулей смесь package by layer и package by feature. (Все о БД вынесено в отдельный пакет data)
Также есть 3 XSLT представления - все рецепты, все ингредиенты, конкретный рецепт 
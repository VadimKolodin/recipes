create schema recipes;


create table recipes.ingredients
(
    id       uuid         not null,
    category varchar(255) not null,
    name     varchar(255) not null,
    primary key (id),
    unique (name));


create table recipes.recipes
(
    id          uuid         not null,
    description varchar(255),
    name        varchar(255) not null,
    primary key (id)
);

create table recipes.recipes_ingredients
(
    quantity      integer,
    recipe_id     uuid not null,
    ingredient_id uuid not null,
    measure       varchar(255),
    primary key (recipe_id, ingredient_id),
    constraint recipes_ingredients_ingredients_id_fk
        foreign key (ingredient_id) references ingredients,
    constraint recipes_ingredients_recipes_id_fk
        foreign key (recipe_id) references recipes
);

create table audit
(
    datetime   timestamp(6),
    id         uuid not null,
    event      varchar(255),
    info       varchar(255),
    table_name varchar(255),
    primary key (id),
    );

create table mailing_rules
(
    id         uuid not null,
    email      varchar(255),
    table_name varchar(255),
    primary key (id)
);

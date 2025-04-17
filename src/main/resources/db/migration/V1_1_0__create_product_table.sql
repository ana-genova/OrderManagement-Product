create table if not exists product (
    id serial primary key,
    name varchar(255) not null,
    description varchar(255) not null,
    sku varchar(255) unique,
    price numeric(10,2) check (price >= 0),
    quantity int not null check (quantity >= 0)
);
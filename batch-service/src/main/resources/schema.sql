create table if not exists problem(
    id          int primary key,
    title       text not null,
    acceptance  text not null,
    difficulty  text not null
);
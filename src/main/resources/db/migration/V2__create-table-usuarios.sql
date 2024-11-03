create table usuarios(
    id bigint not null auto_increment primary key,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    clave varchar(100) not null,
    rol varchar(100) not null
);
create table topicos(
    id bigint not null auto_increment primary key,
    titulo varchar(100) not null,
    mensaje varchar(100) not null,
    fecha_creacion datetime not null,
    status varchar(100) not null,
    usuario_id bigint not null,
    curso_id bigint not null,
    constraint fk_topico_usuario foreign key(usuario_id) references usuarios(id),
    constraint fk_topico_curso foreign key(curso_id) references cursos(id)
);
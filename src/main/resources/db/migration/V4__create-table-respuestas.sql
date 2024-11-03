create table respuestas(
    id bigint not null auto_increment primary key,
    mensaje varchar(100) not null,
    topico_id bigint not null,
    fecha_creacion datetime not null,
    usuario_id bigint not null,
    constraint fk_respuesta_topico foreign key(topico_id) references topicos(id),
    constraint fk_respuesta_usuario foreign key(usuario_id) references usuarios(id)
);
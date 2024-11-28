

create table empresa (
                         id serial primary key,
                         nombre varchar(150) not null,
                         mail varchar(200) not null,
                         cif char(9) not null,
                         fecha_fundacion timestamp(6) not null,
                         es_tecnologica bool
);

create table perfil (
                        id serial primary key,
                        nombre varchar(150) not null,
                        apellidos varchar(150) not null,
                        mail varchar(200) not null,
                        dni char(9) not null,
                        fecha_nacimiento timestamp(6) not null
);



create table experiencia_educativa(
                                      id serial primary key,
                                      centro_educativo varchar(200) not null,
                                      curso varchar(500) not null,
                                      fecha_inicio timestamp(6) not null,
                                      fecha_fin timestamp(6),
                                      id_perfil int not null,
                                      constraint fk_experiencia_educativa_perfil foreign key (id_perfil) references perfil(id)
);

create table experiencia_laboral(
                                    id serial primary key,
                                    puesto varchar(500) not null,
                                    fecha_inicio timestamp(6) not null,
                                    fecha_fin timestamp(6),
                                    id_empresa int not null,
                                    constraint fk_experiencia_laboral_empresa foreign key (id_empresa) references empresa(id)
);

alter table experiencia_laboral add column id_perfil int not null;
alter table experiencia_laboral add constraint fk_experiencia_laboral_perfil foreign key (id_perfil) references perfil(id);


create table oferta_empleo(
                              id serial primary key,
                              titular varchar(200) not null,
                              puesto varchar(500) not null,
                              requisitos text,
                              remuneracion float,
                              remuneracion_minima float,
                              remuneracion_maxima float,
                              id_empresa int not null,
                              constraint fk_oferta_empleo_empresa foreign key (id_empresa) references empresa(id)
);

drop table inscripcion_oferta_empleo;

create table inscripcion_oferta_empleo(
                                          id serial primary key,
                                          id_perfil int not null,
                                          id_oferta_empleo int not null,
                                          fecha_inscripcion timestamp default now(),
                                          estado int not null default 0,
                                          constraint fk_inscripcion_oferta_empleo_perfil foreign key (id_perfil) references perfil(id),
                                          constraint fk_inscripcion_oferta_empleo_oferta foreign key (id_oferta_empleo) references oferta_empleo(id)
);



create table aptitud(
                        id serial primary key ,
                        tipo int2 not null,
                        titulo varchar(150) not null,
                        detalle varchar(500)
);

create table aptitud_perfil(
                               id_perfil int not null,
                               id_aptitud int not null,
                               constraint fk_aptitud_perfil_perfil foreign key (id_perfil) references perfil(id),
                               constraint fk_aptitud_perfil_aptitud foreign key (id_aptitud) references aptitud(id)
);


alter table empresa add column imagen_url varchar(600);
alter table perfil add column imagen_url varchar(600);

ALTER TABLE oferta_empleo
    ALTER COLUMN requisitos
        SET DATA TYPE text;



create table seguidores(
                           id_seguidor int not null,
                           id_seguido int not null,
                           constraint fk_seguidores_seguidor foreign key (id_seguidor) references perfil(id),
                           constraint fk_seguidores_seguido foreign key (id_seguido) references perfil(id)
);


create table mensajes(
                         id serial primary key ,
                         mensaje varchar(500) not null,
                         fecha timestamp(6) not null default now(),
                         id_emisor int4 not null,
                         id_receptor int4 not null,
                         constraint fk_mensaje_emisor foreign key (id_emisor) references perfil(id),
                         constraint fk_mensaje_receptor foreign key (id_receptor) references perfil(id)
);



create table publicacion(
                            id serial primary key,
                            texto text not null,
                            imagen_url varchar(500) not null,
                            fecha timestamp(6) default now(),
                            id_perfil int,
                            id_empresa int,
                            constraint fk_publicacion_perfil foreign key (id_perfil) references perfil(id),
                            constraint fk_publicacion_empresa foreign key (id_empresa) references empresa(id)
);



select * from publicacion p ;
select * from perfil p ;



create table usuario(
                        id serial primary key,
                        username varchar(50) not null,
                        password varchar (500) not null,
                        rol int not null
);


create table token (
                       id serial primary key,
                       token text not null,
                       fecha_creacion timestamp(6) not null,
                       fecha_expiracion timestamp(6) not null,
                       id_usuario int not null,
                       constraint fk_token_usuario foreign key (id_usuario) references usuario(id)
);












alter table perfil  add column id_usuario int null;
alter table perfil  add constraint fk_perfil_usuario foreign key (id_usuario) references usuario(id);









select * from mensajes m ;


select count(id), id_receptor  from mensajes m  where id_emisor  = 1 or id_receptor = 1 group by id_receptor;

select * from mensajes m where (id_emisor  = 1 and id_receptor = 5) or (id_emisor  = 5 and id_receptor = 1);




select * from publicacion p ;



select * from empresa;
select * from perfil p ;
select * from oferta_empleo oe ;
select * from aptitud_perfil ap ;
select * from aptitud a ;







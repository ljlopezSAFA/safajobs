

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


select * from aptitud a ;









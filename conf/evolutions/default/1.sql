# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table grupo_usuario (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  descripcion               varchar(255),
  constraint pk_grupo_usuario primary key (id))
;

create table usuario (
  id                        bigint auto_increment not null,
  nombres                   varchar(255),
  apellidos                 varchar(255),
  edad                      integer,
  correo                    varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  grupo                     integer default 2,
  constraint pk_usuario primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table grupo_usuario;

drop table usuario;

SET FOREIGN_KEY_CHECKS=1;


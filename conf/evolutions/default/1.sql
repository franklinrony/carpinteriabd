# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table usuario (
  id                        bigint auto_increment not null,
  nombres                   varchar(255),
  apellidos                 varchar(255),
  edad                      integer,
  correo                    varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  constraint pk_usuario primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table usuario;

SET FOREIGN_KEY_CHECKS=1;


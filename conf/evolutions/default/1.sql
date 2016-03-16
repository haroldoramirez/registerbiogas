# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cidade (
  id                            bigserial not null,
  nome                          varchar(60) not null,
  constraint pk_cidade primary key (id)
);

create table estado (
  id                            bigserial not null,
  nome                          varchar(60) not null,
  sigla                         varchar(3) not null,
  pais_id                       bigint not null,
  constraint uq_estado_nome_pais_id unique (nome,pais_id),
  constraint pk_estado primary key (id)
);

create table inscrito (
  id                            bigserial not null,
  nome                          varchar(60) not null,
  sexo                          varchar(255) not null,
  data_nascimento               timestamp not null,
  escolaridade                  varchar(30) not null,
  profissao                     varchar(30) not null,
  instituicao                   varchar(45) not null,
  cidade_id                     bigint not null,
  estado_id                     bigint not null,
  pais_id                       bigint not null,
  telefone                      varchar(12) not null,
  cpf                           varchar(15) not null,
  email                         varchar(35) not null,
  ead                           boolean not null,
  fonte                         varchar(25) not null,
  observacoes                   varchar(60) not null,
  data_cadastro                 timestamp,
  data_alteracao                timestamp,
  constraint uq_inscrito_cpf unique (cpf),
  constraint pk_inscrito primary key (id)
);

create table pais (
  id                            bigserial not null,
  sigla                         varchar(2) not null,
  nome                          varchar(80) not null,
  constraint uq_pais_sigla unique (sigla),
  constraint uq_pais_nome unique (nome),
  constraint pk_pais primary key (id)
);

alter table estado add constraint fk_estado_pais_id foreign key (pais_id) references pais (id) on delete restrict on update restrict;
create index ix_estado_pais_id on estado (pais_id);

alter table inscrito add constraint fk_inscrito_cidade_id foreign key (cidade_id) references cidade (id) on delete restrict on update restrict;
create index ix_inscrito_cidade_id on inscrito (cidade_id);

alter table inscrito add constraint fk_inscrito_estado_id foreign key (estado_id) references estado (id) on delete restrict on update restrict;
create index ix_inscrito_estado_id on inscrito (estado_id);

alter table inscrito add constraint fk_inscrito_pais_id foreign key (pais_id) references pais (id) on delete restrict on update restrict;
create index ix_inscrito_pais_id on inscrito (pais_id);


# --- !Downs

alter table if exists estado drop constraint if exists fk_estado_pais_id;
drop index if exists ix_estado_pais_id;

alter table if exists inscrito drop constraint if exists fk_inscrito_cidade_id;
drop index if exists ix_inscrito_cidade_id;

alter table if exists inscrito drop constraint if exists fk_inscrito_estado_id;
drop index if exists ix_inscrito_estado_id;

alter table if exists inscrito drop constraint if exists fk_inscrito_pais_id;
drop index if exists ix_inscrito_pais_id;

drop table if exists cidade cascade;

drop table if exists estado cascade;

drop table if exists inscrito cascade;

drop table if exists pais cascade;


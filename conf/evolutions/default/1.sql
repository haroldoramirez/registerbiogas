# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cidade (
  id                            bigserial not null,
  nome                          varchar(60) not null,
  estado_id                     bigint not null,
  constraint uq_cidade_nome_estado_id unique (nome,estado_id),
  constraint pk_cidade primary key (id)
);

create table estado (
  id                            bigserial not null,
  nome                          varchar(60) not null,
  sigla                         varchar(3),
  pais_id                       bigint not null,
  constraint uq_estado_nome_pais_id unique (nome,pais_id),
  constraint pk_estado primary key (id)
);

create table inscricao (
  id                            bigserial not null,
  nome                          varchar(60) not null,
  sexo                          varchar(255) not null,
  data_nascimento               timestamp not null,
  escolaridade                  varchar(30) not null,
  profissao                     varchar(30) not null,
  instituicao                   varchar(45) not null,
  cidade_id                     bigint not null,
  telefone                      varchar(12) not null,
  cpf                           varchar(15) not null,
  email                         varchar(35) not null,
  ead                           boolean not null,
  fonte                         varchar(25) not null,
  observacoes                   varchar(60) not null,
  data_cadastro                 timestamp,
  data_alteracao                timestamp,
  constraint uq_inscricao_cpf unique (cpf),
  constraint pk_inscricao primary key (id)
);

create table pais (
  id                            bigserial not null,
  sigla                         varchar(2) not null,
  nome                          varchar(80) not null,
  constraint uq_pais_sigla unique (sigla),
  constraint uq_pais_nome unique (nome),
  constraint pk_pais primary key (id)
);

alter table cidade add constraint fk_cidade_estado_id foreign key (estado_id) references estado (id) on delete restrict on update restrict;
create index ix_cidade_estado_id on cidade (estado_id);

alter table estado add constraint fk_estado_pais_id foreign key (pais_id) references pais (id) on delete restrict on update restrict;
create index ix_estado_pais_id on estado (pais_id);

alter table inscricao add constraint fk_inscricao_cidade_id foreign key (cidade_id) references cidade (id) on delete restrict on update restrict;
create index ix_inscricao_cidade_id on inscricao (cidade_id);


# --- !Downs

alter table if exists cidade drop constraint if exists fk_cidade_estado_id;
drop index if exists ix_cidade_estado_id;

alter table if exists estado drop constraint if exists fk_estado_pais_id;
drop index if exists ix_estado_pais_id;

alter table if exists inscricao drop constraint if exists fk_inscricao_cidade_id;
drop index if exists ix_inscricao_cidade_id;

drop table if exists cidade cascade;

drop table if exists estado cascade;

drop table if exists inscricao cascade;

drop table if exists pais cascade;


create database SkillsAssessment;
use SkillsAssessment;


create table usuario (
	usu_codigo int auto_increment,
    usu_login varchar(250) ,
    usu_senha varchar(100) ,
    usu_situacao smallint default 0,
    usu_verificado smallint default 0,
    usu_tipo varchar (50) ,
    constraint pk_usu primary key (usu_codigo)
)engine=innodb;

create table aluno (
	alu_codigo int not null auto_increment,
    alu_nome varchar(250) ,
    alu_ra varchar(25) ,
    alu_cpf varchar (25) ,
    alu_nascimento date,
    alu_cep varchar (25) ,
    alu_endereco varchar (250),
    alu_bairro varchar (250) ,
    alu_cidade varchar (250) ,
	alu_uf varchar(10) ,
    alu_numero int ,
    alu_complemento varchar (250) ,
    alu_telefone varchar (20),
    alu_celular varchar (25),
    alu_data_cadastro timestamp,
    alu_usuario_cadastro int,
    usu_codigo int ,
    constraint pk_alu primary key (alu_codigo),
	constraint fk_usr foreign key (usu_codigo) references usuario (usu_codigo)
)engine=innodb;

create table funcionario (
	fun_codigo int auto_increment,
    fun_nome varchar(250) ,
    fun_rg varchar(25) ,
    fun_nascimento date ,
    fun_sexo smallint ,
    fun_cep varchar(25) ,
    fun_endereco varchar(250) ,
    fun_bairro varchar(250),
    fun_cidade varchar(250) ,
    fun_numero int not null,
    fun_telefone varchar(25),
    fun_celular varchar(25),
    fun_data_cadastro timestamp,
    usu_codigo int,
    constraint fk_usr_fun foreign key (usu_codigo) references usuario (usu_codigo),
    constraint pk_fun primary key (fun_codigo)
)engine=innodb;

create table pergunta(
	per_codigo int auto_increment,
    per_pergunta varchar(250) ,
    per_fundo varchar(250),
    per_introducao varchar(250) ,
    per_situacao int,
    constraint pk_per primary key (per_codigo)
)engine=innodb;

create table competencia (
	com_codigo int auto_increment,
    com_tipo  varchar(250) ,
    com_data_cadastro timestamp,
    constraint pk_com primary key (com_codigo)
)engine=innodb;

create table alternativas(
	alt_codigo int auto_increment,
    alt_descricao varchar(250) ,
    per_codigo int ,
    constraint pk_alt primary key (alt_codigo),
    constraint fk_per foreign key (per_codigo) references pergunta (per_codigo)
)engine=innodb;

create table rsp_com (
	rsc_codigo int auto_increment,
    alt_codigo int,
    com_codigo int,
    rsc_peso int,
    constraint pf_rsc primary key (rsc_codigo),
    constraint fk_alt foreign key (alt_codigo) references alternativas(alt_codigo),
    constraint fk_cmp foreign key (com_codigo) references competencia (com_codigo)
);

create table questionario(
	que_codigo int auto_increment,
    alu_codigo int ,
    per_codigo int ,
    alt_codigo int ,
    que_data timestamp,
    que_duracao time,
    constraint pk_pro primary key (que_codigo),
    constraint fk_alu foreign key (alu_codigo) references aluno (alu_codigo),
    constraint fk_pgt foreign key (per_codigo) references pergunta (per_codigo),
    constraint fk_rsp foreign key (alt_codigo) references alternativas (alt_codigo)
)engine=innodb;

create table resultado(
	rst_codigo int auto_increment,
    alu_codigo int ,
    rst_data_final timestamp,
    rst_concluido smallint default 0,
    rst_avaliado smallint default 0,
    constraint pk_rlt primary key (rst_codigo),
    constraint fk_alo foreign key (alu_codigo) references aluno (alu_codigo)
)engine=innodb;

create table media(
	med_codigo int auto_increment,
    rst_codigo int,
    com_codigo int,
    med_final int,
    constraint pk_med primary key (med_codigo),
    constraint fk_rst foreign key (rst_codigo) references resultado (rst_codigo),
    constraint fk_cpt foreign key (com_codigo) references competencia (com_codigo)
);

insert into aluno (alu_nome,alu_ra,alu_cpf,alu_nascimento,alu_cep,
		alu_endereco,alu_bairro,alu_cidade,alu_uf,alu_numero,alu_complemento,alu_telefone,
        alu_celular,alu_data_cadastro) values (
		'EDUARDO ALVES','123456789','42487690852','1994-08-07','12232-090','RUA ALFREDO C', 'BOSQUE', 'SJC', 'SP', 1602, 'CASA', '39334446', '988849273',
        '2008-01-01 00:00:01');
        
insert into aluno (alu_nome,alu_ra,alu_cpf,alu_nascimento,alu_cep,
		alu_endereco,alu_bairro,alu_cidade,alu_uf,alu_numero,alu_complemento,alu_telefone,
        alu_celular,alu_data_cadastro) values (
		'Pedro alves','123456789','42487690852','1994-08-07','12232-090','RUA ALFREDO C', 'BOSQUE', 'SJC', 'SP', 1602, 'CASA', '39334446', '988849273',
        '2008-01-01 00:00:01');

insert into funcionario (
    fun_nome,fun_rg,fun_nascimento,fun_sexo,fun_cep ,fun_endereco ,fun_bairro ,fun_cidade ,
    fun_numero ,fun_telefone,fun_celular ,fun_data_cadastro) values (
    'JOAO' , '26125181515', '1992-05-04',1,'12232050','AQUI','LA', 'SJC', 100,
    '1239335566','955145151','2016-01-01 00:00:01');


insert into usuario (
    usu_login ,usu_senha ,usu_situacao ,usu_verificado ,usu_tipo,frk_pessoa) 
    values ('edu','1234',1,1,'aluno',1);
insert into usuario (
    usu_login ,usu_senha ,usu_situacao ,usu_verificado ,usu_tipo,frk_pessoa) 
    values ('joao','1234',1,1,'funcionario',1);    
insert into usuario (
    usu_login ,usu_senha ,usu_situacao ,usu_verificado ,usu_tipo,frk_pessoa) 
    values ('pedro','1234',1,1,'aluno',2); 
    
select funcionario.fun_nome ,
		aluno.alu_nome
from aluno inner join funcionario on aluno.alu_usuario_cadastro = funcionario.fun_codigo
where funcionario.fun_codigo=1;

select * from aluno;
select * from funcionario;
drop database SkillsAssessment;

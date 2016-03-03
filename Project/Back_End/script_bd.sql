create database SkillsAssessment;
use SkillsAssessment;


create table user (
	usr_code int auto_increment,
    usr_userName varchar(250) ,
    usr_password varchar(100) ,
    usr_situation smallint default 0,
    usr_verified smallint default 0,
    usr_kind varchar (50) ,
    usr_token varchar(500),
    constraint pk_usr primary key (usr_code)
)engine=innodb;

create table student (
	std_code int not null auto_increment,
    std_name varchar(250) ,
    std_ra varchar(25) ,
    std_cpf varchar (25) ,
    std_birth date,
    std_cep varchar (25) ,
    std_address varchar (250),
    std_neighborhood varchar (250) ,
    std_city varchar (250) ,
	std_uf varchar(10) ,
    std_number int ,
    std_complement varchar (250) ,
    std_telephone varchar (20),
    std_cellphone varchar (25),
    std_registration_date timestamp,
    std_user_register int,
    usr_code int ,
    constraint pk_alu primary key (std_code),
	constraint fk_usr foreign key (usr_code) references user (usr_code)
)engine=innodb;


create table course (
	crs_code int auto_increment,
    crs_name varchar (250),
    crs_situation smallint,
    crs_registration_date timestamp,
    constraint crs_pk primary key (crs_code)
)engine=innodb;

create table period (
	prd_code int auto_increment,
	prd_year int ,
	prd_period int,
    crs_code int,
	constraint pk_prd primary key (prd_code),
    constraint fk_crs foreign key (crs_code) references course (crs_code)
)engine=innodb;

create table employee (
	emp_code int auto_increment,
    emp_name varchar(250) ,
    emp_cpf varchar(25) ,
    emp_birth date ,
    emp_cep varchar(25) ,
    emp_address varchar(250) ,
    emp_neighborhood varchar(250),
    emp_city varchar(250) ,
    emp_number int not null,
    emp_telephone varchar(25),
    emp_cellphone varchar(25),
    emp_registration_date timestamp,
    usr_code int,
    constraint fk_usr_emp foreign key (usr_code) references user (usr_code),
    constraint pk_emp primary key (emp_code)
)engine=innodb;

create table question(
	qst_code int auto_increment,
    qst_question varchar(250) ,
    qst_introduction varchar(250) ,
    qst_situation int,
    constraint pk_qst primary key (qst_code)
)engine=innodb;

create table competence (
	com_code int auto_increment,
    com_kind  varchar(250) ,
    com_registration_date timestamp,
    constraint pk_com primary key (com_code)
)engine=innodb;

create table alternatives(
	alt_code int auto_increment,
    alt_description varchar(250) ,
    qst_code int ,
    constraint pk_alt primary key (alt_code),
    constraint fk_qst foreign key (qst_code) references question (qst_code)
)engine=innodb;

create table alt_com (
	rsc_code int auto_increment,
    alt_code int,
    com_code int,
    rsc_weight int,
    constraint pf_rsc primary key (rsc_code),
    constraint fk_alt foreign key (alt_code) references alternatives(alt_code),
    constraint fk_cmp foreign key (com_code) references competence (com_code)
);

create table quiz(
	quz_code int auto_increment,
    std_code int ,
    qst_code int ,
    alt_code int ,
    quz_date timestamp,
    quz_duration time,
    constraint pk_pro primary key (quz_code),
    constraint fk_alu foreign key (std_code) references student (std_code),
    constraint fk_pgt foreign key (qst_code) references question (qst_code),
    constraint fk_rsp foreign key (alt_code) references alternatives (alt_code)
)engine=innodb;

create table result(
	rst_code int auto_increment,
    std_code int ,
    rst_date_final timestamp,
    rst_completed smallint default 0,
    rst_measured smallint default 0,
    constraint pk_rlt primary key (rst_code),
    constraint fk_alo foreign key (std_code) references student (std_code)
)engine=innodb;

create table average(
	avr_code int auto_increment,
    rst_code int,
    com_code int,
    avr_final int,
    constraint pk_avr primary key (avr_code),
    constraint fk_rst foreign key (rst_code) references result (rst_code),
    constraint fk_cpt foreign key (com_code) references competence (com_code)
)engine=innodb;

insert into student (std_name,std_ra,std_cpf,std_birth,std_cep,
		std_address,std_neighborhood,std_city,std_uf,std_number,std_complement,std_telephone,
        std_cellphone,std_registration_date,std_user_register,usr_code) values (
		'EDUARDO ALVES','123456789','42487690852','1994-08-07','12232-090','RUA ALFREDO C', 'BOSquz', 'SJC', 'SP', 1602, 'CASA', '39334446', '988849273',
        '2008-01-01 00:00:01',1,2);
        
insert into student (std_name,std_ra,std_cpf,std_birth,std_cep,
		std_address,std_neighborhood,std_city,std_uf,std_number,std_complement,std_telephone,
        std_cellphone,std_registration_date,std_user_register,usr_code) values (
		'Pedro alves','123456789','42487690852','1994-08-07','12232-090','RUA ALFREDO C', 'BOSquz', 'SJC', 'SP', 1602, 'CASA', '39334446', '988849273',
        '2008-01-01 00:00:01',1,3);

insert into employee (
    emp_name,emp_cpf,emp_birth,emp_cep ,emp_address ,emp_neighborhood ,emp_city ,
    emp_number ,emp_telephone,emp_cellphone ,emp_registration_date,usr_code) values (
    'JOAO' , '26125181515', '1992-05-04','12232050','AQUI','LA', 'SJC', 100,
    '1239335566','955145151','2016-01-01 00:00:01',1);

insert into user (
    usr_userName ,usr_password ,usr_situation ,usr_verified ,usr_kind) 
    values ('joao','1234',1,1,'employee');  
    
insert into user (
    usr_userName ,usr_password ,usr_situation ,usr_verified ,usr_kind) 
    values ('edu','1234',1,1,'student');
    
insert into user (
    usr_userName ,usr_password ,usr_situation ,usr_verified ,usr_kind) 
    values ('pedro','1234',1,1,'student'); 
    
  
insert into question (qst_introduction,qst_question,qst_situation) 
values ("imagem.jpeg","Quando terminou a 2º guerra?", 0);

insert into question (qst_introduction,qst_question,qst_situation) 
values ("imagem.jpeg","Quem criou o computdor?", 0);

insert into question (qst_introduction,qst_question,qst_situation) 
values ( "imagem.jpeg","2 + 2 ?", 0);

insert into question (qst_introduction,qst_question,qst_situation) 
values ( "imagem.jpeg","5 + 4 ?", 0);

insert into question (qst_introduction,qst_question,qst_situation) 
values ("imagem.jpeg","Quando acaba?", 1);

insert into question (qst_introduction,qst_question,qst_situation) 
values ("imagem.jpeg","Quando vou tirar carta?", 1);

insert into alternatives (alt_description,qst_code) values ("A",1);
insert into alternatives (alt_description,qst_code) values ("B",2);
insert into alternatives (alt_description,qst_code) values ("C",3);
insert into alternatives (alt_description,qst_code) values ("D",4);
insert into alternatives (alt_description,qst_code) values ("E",5);

insert into alternatives (alt_description,qst_code) values ("F",1);
insert into alternatives (alt_description,qst_code) values ("G",2);
insert into alternatives (alt_description,qst_code) values ("H",3);
insert into alternatives (alt_description,qst_code) values ("I",4);
insert into alternatives (alt_description,qst_code) values ("J",5);
insert into alternatives (alt_description,qst_code) values ("K",6);
insert into alternatives (alt_description,qst_code) values ("L",6);

insert into competence (com_kind,com_registration_date) values ('Determinação','2008-01-01 00:00:01');
insert into competence (com_kind,com_registration_date) values ('Individualismo','2008-01-01 00:00:02');
insert into competence (com_kind,com_registration_date) values ('Persuasão','2008-01-01 00:00:03');
insert into competence (com_kind,com_registration_date) values ('Persistência','2008-01-01 00:00:04');
insert into competence (com_kind,com_registration_date) values ('Obediência','2008-01-01 00:00:05');

insert into alt_com (alt_code,com_code,rsc_weight) values (1,1,1);
insert into alt_com (alt_code,com_code,rsc_weight) values (1,2,7);
insert into alt_com (alt_code,com_code,rsc_weight) values (2,4,8);
insert into alt_com (alt_code,com_code,rsc_weight) values (2,3,5);
insert into alt_com (alt_code,com_code,rsc_weight) values (3,1,8);
insert into alt_com (alt_code,com_code,rsc_weight) values (3,5,5);

select * from alternatives where qst_code = 2;

insert into quiz (std_code,qst_code,alt_code,quz_date,quz_duration) values (1,1,1,current_date(),'00:39:38');
-- insert into quiz (std_code,qst_code,alt_code,quz_date,quz_duration) values (2,1,1,current_date(),'00:40:38');
-- insert into quiz (std_code,qst_code,alt_code,quz_date,quz_duration) values (1,2,2,current_date(),'00:41:38');
-- insert into quiz (std_code,qst_code,alt_code,quz_date,quz_duration) values (2,2,2,current_date(),'00:45:38');
-- insert into quiz (std_code,qst_code,alt_code,quz_date,quz_duration) values (1,3,3,current_date(),'00:41:38');
truncate quiz;



select question.qst_code as qst_code,question.qst_question,qst_introduction,alternatives.alt_code,alt_description,
competence.com_code,competence.com_kind
from question inner join alternatives on alternatives.qst_code = question.qst_code 
inner join alt_com on alt_com.alt_code = alternatives.alt_code
inner join competence on alt_com.com_code = competence.com_code
where question.qst_situation <> 1 and question.qst_code not in (select quiz.qst_code from quiz where std_code = 1) order by question.qst_code ;

select * from question where qst_code = 2;

select min(question.qst_code) 
from question where question.qst_situation <> 1 and question.qst_code not in (select quiz.qst_code from quiz where std_code = 1) order by question.qst_code;


select employee.emp_name ,
		student.std_name
from student inner join employee on student.std_user_register = employee.emp_code
where employee.emp_code=1;

select * from student;
select * from employee;
select * from user;

select * from course;


insert into course values (1,'Banco de Dados',1,'2008-01-01 00:00:01');
insert into course values (2,'Estrutura Leves',1,'2008-01-01 00:00:01');
insert into course values (3,'Logistica',1,'2008-01-01 00:00:01');
insert into course values (4,'Manutenção de Aeronaves',1,'2008-01-01 00:00:01');
insert into course values (5,'Gestão de Produção Industrial',1,'2008-01-01 00:00:01');
insert into course values (5,'Gestão de Produção Industrial',1,sysdate());

delete from course where crs_code = 1;

drop database SkillsAssessment;

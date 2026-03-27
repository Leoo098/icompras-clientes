create table clientes(
	codigo serial not null primary key,
	nome varchar(150) not null,
	cpf char(11) not null,
	logradouro varchar(100),
	numero varchar(10),
	bairro varchar(100),
	email varchar(150),
	telefone varchar(20),
	ativo boolean
);
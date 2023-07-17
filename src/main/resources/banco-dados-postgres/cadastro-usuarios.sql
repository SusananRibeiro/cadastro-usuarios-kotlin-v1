--- Tabela
CREATE TABLE usuarios (
	id serial PRIMARY KEY NOT NULL,	
	usuario varchar(120) NOT NULL UNIQUE,
	senha varchar(20) NOT NULL
	
);

CREATE TABLE cadastros (
	id serial PRIMARY KEY NOT NULL,
	id_usuario integer NOT NULL UNIQUE, -- não pode ter mais de um id por usuário
	nome varchar(120) NOT NULL,
	cpf char(11) NOT NULL UNIQUE, 
	endereco varchar(150) NULL,
	telefone varchar(11) NULL, 
	FOREIGN KEY (id_usuario) REFERENCES usuarios(id) 	
);

--- Insert
INSERT INTO usuarios (usuario, senha) VALUES ('SISTEMA', 'canditado123');



--- Tabela
CREATE TABLE usuarios (
	id serial PRIMARY KEY NOT NULL,	
	usuario varchar(120) NOT NULL,
	senha varchar(20) NOT NULL
	
);

CREATE TABLE cadastros (
	id serial PRIMARY KEY NOT NULL,
	id_usuario integer NOT NULL UNIQUE, -- não pode ter mais de um id por usuário
	nome varchar(120) NOT NULL,
	cpf char(11) UNIQUE NOT NULL,
	endereco varchar(150) NULL,
	telefone varchar(11) NULL,
	FOREIGN KEY (id_usuario) REFERENCES usuarios(id) 	
);

--- Consultar
SELECT * FROM usuarios;
SELECT * FROM cadastros;


--- Excluir
DROP TABLE cadastros;
DROP TABLE usuarios; 

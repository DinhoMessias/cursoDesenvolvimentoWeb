CREATE TABLE categoria(
	id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) values ('Alimentacao');
INSERT INTO categoria (nome) values ('Combustivel');
INSERT INTO categoria (nome) values ('Medicamentos'); 
INSERT INTO categoria (nome) values ('Lazer');
INSERT INTO categoria (nome) values ('Aluguel'); 
INSERT INTO categoria (nome) values ('Outros');

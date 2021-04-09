CREATE TABLE pessoa(
	id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	ativo boolean NOT NULL,
	logradouro VARCHAR(150),
	numero VARCHAR(10),
	complemento VARCHAR(100),
	bairro VARCHAR(50),
	cep VARCHAR(10),
	cidade VARCHAR(100),
	estado VARCHAR(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Pedro Santos', true,'Rua da Bateria', '23', null, 'Penha', '29650-000', 'Santa Teresa', 'ES');
INSERT INTO pessoa (nome, ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Henrique Medeiros', true,'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12400-102', 'Rio de Janeiro', 'RJ');
INSERT INTO pessoa (nome, ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Carlos Santana', true,'Rua da Manga', '433', null, 'Centro', '31400-012', 'Belo Horizonte', 'MG');
INSERT INTO pessoa (nome, ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Leonardo Oliveira', true,'Rua do Músico', '566', null, 'Segismundo Pereira', '38400-000', 'Salvador', 'BA');
INSERT INTO pessoa (nome, ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Isabela Martins', true, 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99400-244', 'Manaus', 'AM');


CREATE TABLE lancamento (
	id BIGINT(30) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(100) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	cod_categoria BIGINT(20) NOT NULL,
	cod_pessoa BIGINT(20) NOT NULL,
	FOREIGN KEY (cod_categoria) REFERENCES categoria(id),
	FOREIGN KEY (cod_pessoa) REFERENCES pessoa(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, cod_categoria, cod_pessoa) values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, cod_categoria, cod_pessoa) values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'DESPESA', 2, 2);

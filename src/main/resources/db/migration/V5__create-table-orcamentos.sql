CREATE TABLE orcamentos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_orcamento DATETIME,
    imagem_url VARCHAR(255),
    status VARCHAR(255),
    cliente_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL
);

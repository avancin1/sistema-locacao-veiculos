-- Sistema de Locação de Veículos - PostgreSQL
-- Disciplina: Banco de Dados

DROP TABLE IF EXISTS locacoes;
DROP TABLE IF EXISTS veiculos;
DROP TABLE IF EXISTS clientes;

CREATE TABLE clientes (
    id_cliente  INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    cpf         CHAR(11)     NOT NULL UNIQUE,
    telefone    VARCHAR(15),
    cnh         VARCHAR(11)  NOT NULL
);

CREATE TABLE veiculos (
    id_veiculo    INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    placa         VARCHAR(8)    NOT NULL UNIQUE,
    modelo        VARCHAR(50)   NOT NULL,
    marca         VARCHAR(50)   NOT NULL,
    ano           INTEGER       NOT NULL CHECK (ano >= 1990),
    valor_diaria  NUMERIC(10,2) NOT NULL CHECK (valor_diaria > 0),
    disponivel    CHAR(1)       NOT NULL DEFAULT 'S' CHECK (disponivel IN ('S','N'))
);

CREATE TABLE locacoes (
    id_locacao               INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_cliente               INTEGER NOT NULL,
    id_veiculo               INTEGER NOT NULL,
    data_retirada            DATE    NOT NULL,
    data_devolucao_prevista  DATE    NOT NULL,
    data_devolucao           DATE,
    valor_total              NUMERIC(10,2),
    CONSTRAINT fk_locacao_cliente FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente),
    CONSTRAINT fk_locacao_veiculo FOREIGN KEY (id_veiculo) REFERENCES veiculos (id_veiculo),
    CONSTRAINT ck_datas CHECK (data_devolucao_prevista >= data_retirada)
);

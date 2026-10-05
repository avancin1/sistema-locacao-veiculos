-- Dados de teste (opcional). Rode depois de criacao_tabelas.sql
INSERT INTO clientes (nome, cpf, telefone, cnh) VALUES
 ('Maria Souza',  '11111111111', '27999990001', '12345678901'),
 ('João Lima',    '22222222222', '27999990002', '10987654321'),
 ('Carla Alves',  '33333333333', '27999990003', '55566677788');

INSERT INTO veiculos (placa, modelo, marca, ano, valor_diaria, disponivel) VALUES
 ('ABC1D23', 'Onix',    'Chevrolet', 2022, 150.00, 'S'),
 ('DEF4G56', 'HB20',    'Hyundai',   2021, 140.00, 'S'),
 ('GHI7J89', 'Onix',    'Chevrolet', 2023, 160.00, 'N'),
 ('JKL0M12', 'Compass', 'Jeep',      2022, 320.00, 'S');

INSERT INTO locacoes (id_cliente, id_veiculo, data_retirada, data_devolucao_prevista, data_devolucao, valor_total) VALUES
 (1, 1, '2026-09-01', '2026-09-04', '2026-09-04',  450.00),
 (2, 2, '2026-09-05', '2026-09-10', '2026-09-11',  840.00),
 (3, 3, '2026-09-20', '2026-09-25', NULL,          NULL),
 (1, 4, '2026-09-12', '2026-09-14', '2026-09-14',  640.00);

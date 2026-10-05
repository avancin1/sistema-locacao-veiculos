SELECT l.id_locacao,
       c.nome AS cliente,
       v.placa,
       v.modelo,
       l.data_retirada,
       l.data_devolucao_prevista,
       l.data_devolucao,
       l.valor_total
FROM locacoes l
JOIN clientes c ON c.id_cliente = l.id_cliente
JOIN veiculos v ON v.id_veiculo = l.id_veiculo
ORDER BY l.data_retirada DESC

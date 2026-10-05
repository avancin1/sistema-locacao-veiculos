SELECT v.modelo,
       COUNT(l.id_locacao) AS total_locacoes,
       ROUND(AVG(COALESCE(l.data_devolucao, l.data_devolucao_prevista) - l.data_retirada), 1) AS duracao_media_dias,
       SUM(l.valor_total) AS valor_total
FROM veiculos v
JOIN locacoes l ON l.id_veiculo = v.id_veiculo
GROUP BY v.modelo
ORDER BY total_locacoes DESC

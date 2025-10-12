-- Usuário administrador
INSERT INTO usuarios (nome, email, senha, is_admin)
VALUES ('Admin', 'admin@admin', 'sorinan', TRUE);

-- Usuários comuns aleatórios
INSERT INTO usuarios (nome, email, senha)
VALUES
    ('João Silva', 'joao.silva@example.com', 'senha123'),
    ('Maria Oliveira', 'maria.oliveira@example.com', 'senha456');
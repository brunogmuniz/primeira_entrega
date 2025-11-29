-- Usuário administrador
INSERT INTO usuarios (nome, email, senha, role_user)
VALUES ('Admin', 'admin@admin', '$2a$10$3t8ULunB1nX.zqJrF7DxW.SqFheoTWBh8k4FcW12K0tD4/Sq2WmH6', 'ADMIN');

-- Usuários comuns aleatórios
INSERT INTO usuarios (nome, email, senha)
VALUES
    ('João Silva', 'joao.silva@example.com', '$2a$10$14JTXj7eXpnvG3eJVpVZ7OyRyUtq6P5ZWYZXOENX3CVv5igpq4RDG'),
    ('Maria Oliveira', 'maria.oliveira@example.com', '$2a$10$IlQU4XXVHWReQITQ2OntR.2IWeYsQ.u9UBeJpSQS0NQWCuV07wBfK');
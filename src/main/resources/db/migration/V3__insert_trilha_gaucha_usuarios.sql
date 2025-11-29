-- Usuário administrador
INSERT INTO usuarios (nome, email, senha, role_user)
VALUES ('Admin', 'admin@admin', '$2a$10$q5xN7HHKff.pm2eAjKMNXe3lQHdlj5czxthnZY8/8aB2PHujd1UIC', 'ADMIN');

-- Usuários comuns aleatórios
INSERT INTO usuarios (nome, email, senha)
VALUES
    ('João Silva', 'joao.silva@example.com', '$2a$10$14JTXj7eXpnvG3eJVpVZ7OyRyUtq6P5ZWYZXOENX3CVv5igpq4RDG'),
    ('Maria Oliveira', 'maria.oliveira@example.com', '$2a$10$IlQU4XXVHWReQITQ2OntR.2IWeYsQ.u9UBeJpSQS0NQWCuV07wBfK');
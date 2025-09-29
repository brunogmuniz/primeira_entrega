-- 1️⃣ Criar ENUM regioes
CREATE TYPE regioes AS ENUM (
    'Norte',
    'Serra',
    'Litoral',
    'CentroOeste',
    'Missoes',
    'Metropolitana'
);

-- 2️⃣ Tabela usuarios
CREATE TABLE usuarios (
id SERIAL PRIMARY KEY,
uuid UUID DEFAULT gen_random_uuid() UNIQUE,
nome VARCHAR(100) NOT NULL,
email VARCHAR(150) UNIQUE NOT NULL,
senha VARCHAR(255) NOT NULL,
is_admin BOOLEAN DEFAULT FALSE,
data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3️⃣ Tabela cidades
CREATE TABLE cidades (
id SERIAL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
regiao regioes NOT NULL,
latitude DECIMAL(9,6),
longitude DECIMAL(9,6)
);

-- 4️⃣ Tabela checklists
CREATE TABLE checklists (
id SERIAL PRIMARY KEY,
id_usuario INT REFERENCES usuarios(id) ON DELETE CASCADE,
id_cidade INT REFERENCES cidades(id) ON DELETE CASCADE,
visitado BOOLEAN DEFAULT FALSE,
data_visita TIMESTAMP
);


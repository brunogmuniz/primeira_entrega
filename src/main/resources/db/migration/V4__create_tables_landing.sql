-- 1 Tabela inscritos
CREATE TABLE userLanding (
                          id SERIAL PRIMARY KEY,
                          uuid UUID DEFAULT gen_random_uuid() UNIQUE,
                          nome_completo VARCHAR(255) NOT NULL,
                          primeiro_nome VARCHAR(100),
                          email VARCHAR(150) UNIQUE NOT NULL,
                          whatsapp VARCHAR(13) UNIQUE NOT NULL,
                          data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
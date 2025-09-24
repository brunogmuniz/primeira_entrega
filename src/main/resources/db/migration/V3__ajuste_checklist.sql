-- v3__ajuste_checklists.sql

ALTER TABLE checklists
    RENAME COLUMN id_usuario TO usuario_id;

ALTER TABLE checklists
    RENAME COLUMN id_cidade TO cidade_id;


ALTER TABLE checklists
    ADD CONSTRAINT unq_usuario_cidade UNIQUE (usuario_id, cidade_id);

CREATE INDEX idx_checklists_usuario ON checklists(usuario_id);
CREATE INDEX idx_checklists_cidade ON checklists(cidade_id);

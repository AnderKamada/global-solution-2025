-- TRILHA
CREATE TABLE trilha (
  id BIGSERIAL PRIMARY KEY,
  nome VARCHAR(120) NOT NULL,
  descricao VARCHAR(500)
);
CREATE INDEX idx_trilha_nome ON trilha (LOWER(nome));

-- MODULO
CREATE TABLE modulo (
  id BIGSERIAL PRIMARY KEY,
  titulo VARCHAR(120) NOT NULL,
  trilha_id BIGINT NOT NULL REFERENCES trilha(id) ON DELETE CASCADE
);
CREATE INDEX idx_modulo_titulo ON modulo (LOWER(titulo));
CREATE INDEX idx_modulo_trilha ON modulo (trilha_id);

CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
  id SERIAL PRIMARY KEY,
  number VARCHAR(255) NOT NULL UNIQUE,
  owner_id BIGINT NOT NULL,
  balance NUMERIC(19,2) NOT NULL,
  version BIGINT,
  CONSTRAINT fk_account_owner FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS user_roles (
  user_id BIGINT NOT NULL,
  role VARCHAR(255),
  CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS transactions (
  id SERIAL PRIMARY KEY,
  from_account_id BIGINT NOT NULL,
  to_account_id BIGINT NOT NULL,
  amount NUMERIC(19,2) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  type VARCHAR(255) NOT NULL,
  description VARCHAR(500),
  CONSTRAINT fk_transaction_from_account FOREIGN KEY (from_account_id) REFERENCES accounts(id),
  CONSTRAINT fk_transaction_to_account FOREIGN KEY (to_account_id) REFERENCES accounts(id)
);

INSERT INTO users (email, password)
VALUES
  ('alice@example.com', '$2b$12$kt8U1KNb6.PjX0H3.dlF9.ZlrgEVvbIiBswmJSVFYTH5.qSwdtEFK'),
  ('bob@example.com', '$2b$12$kt8U1KNb6.PjX0H3.dlF9.ZlrgEVvbIiBswmJSVFYTH5.qSwdtEFK')
ON CONFLICT DO NOTHING;

INSERT INTO user_roles (user_id, role)
VALUES
  (1, 'ROLE_USER'),
  (2, 'ROLE_USER')
ON CONFLICT DO NOTHING;

INSERT INTO accounts (number, owner_id, balance, version)
VALUES
  ('ACC-1001', 1, 1000.00, 0),
  ('ACC-1002', 2, 500.00, 0)
ON CONFLICT DO NOTHING;

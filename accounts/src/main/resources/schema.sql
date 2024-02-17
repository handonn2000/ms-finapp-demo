CREATE TABLE IF NOT EXISTS customers (
  id serial PRIMARY KEY,
  name varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  mobile_number varchar(20) NOT NULL,
  created_at date NOT NULL,
  created_by varchar(20) NOT NULL,
  updated_at date DEFAULT NULL,
  updated_by varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
  customer_id serial  NOT NULL,
  account_number varchar(100) PRIMARY KEY,
  account_type varchar(100) NOT NULL,
  branch_address varchar(200) NOT NULL,
  created_at date NOT NULL,
  created_by varchar(20) NOT NULL,
  updated_at date DEFAULT NULL,
  updated_by varchar(20) DEFAULT NULL
);

INSERT INTO CUSTOMERS (name, email, mobile_number, created_at, created_by)
VALUES ('Han', 'handonn@example.com', '0778098295', CURRENT_TIMESTAMP, 'ADMIN');

INSERT INTO ACCOUNTS (customer_id, account_number, account_type, branch_address, created_at, created_by)
VALUES (1, 'ACCT123', 'ADMIN', '123 Main St', CURRENT_TIMESTAMP, 'ADMIN');




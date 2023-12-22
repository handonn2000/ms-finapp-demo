CREATE TABLE IF NOT EXISTS cards (
  id serial PRIMARY KEY,
  mobile_number varchar(15) NOT NULL,
  card_number varchar(100) NOT NULL,
  card_type varchar(100) NOT NULL,
  total_limit int NOT NULL,
  amount_used int NOT NULL,
  available_amount int NOT NULL,
  created_at date NOT NULL,
  created_by varchar(20) NOT NULL,
  modified_at date DEFAULT NULL,
  modified_by varchar(20) DEFAULT NULL
);

INSERT INTO CARDS (mobile_number, card_number, card_type, total_limit, amount_used, available_amount, created_at, created_by)
VALUES
('8498074646', '898457138919', 'MASTERCARD', 55, 33, 22, CURRENT_TIMESTAMP, 'YourCreatedByValue'),
('8294054265', '315344933785', 'VISA', 66, 55, 11, CURRENT_TIMESTAMP, 'YourCreatedByValue'),
('3184829562', '159327494442', 'JCB', 53, 13, 40, CURRENT_TIMESTAMP, 'YourCreatedByValue'),
('3883884571', '557897732690', 'AMERICAN_EXPRESS', 77, 56, 21, CURRENT_TIMESTAMP, 'YourCreatedByValue'),
('3883880571', '557897736690', 'DINERS_CLUB', 90, 11, 9, CURRENT_TIMESTAMP, 'YourCreatedByValue'),
('3883880570', '557897736610', 'DISCOVER', 90, 11, 9, CURRENT_TIMESTAMP, 'YourCreatedByValue'),
('3883880571', '557897736613', 'VISA', 90, 11, 9, CURRENT_TIMESTAMP, 'YourCreatedByValue'),
('3883880576', '557897736612', 'VISA', 90, 11, 9, CURRENT_TIMESTAMP, 'YourCreatedByValue');
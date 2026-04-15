DELETE FROM tb_transaction;
DELETE FROM tb_account;
DELETE FROM tb_operation_type;

INSERT INTO tb_operation_type (id_operation_type, description) VALUES (1, 'PURCHASE');
INSERT INTO tb_operation_type (id_operation_type, description) VALUES (2, 'INSTALLMENT_PURCHASE');
INSERT INTO tb_operation_type (id_operation_type, description) VALUES (3, 'WITHDRAWAL');
INSERT INTO tb_operation_type (id_operation_type, description) VALUES (4, 'PAYMENT');

INSERT INTO tb_operation_type (id_operation_type, description)
VALUES
    (1, 'PURCHASE'),
    (2, 'INSTALLMENT_PURCHASE'),
    (3, 'WITHDRAWAL'),
    (4, 'PAYMENT')
ON CONFLICT (id_operation_type) DO NOTHING;

SELECT setval(
    pg_get_serial_sequence('tb_operation_type', 'id_operation_type'),
    (SELECT COALESCE(MAX(id_operation_type), 1) FROM tb_operation_type)
);

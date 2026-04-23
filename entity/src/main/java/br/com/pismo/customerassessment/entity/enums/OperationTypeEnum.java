package br.com.pismo.customerassessment.entity.enums;

public enum OperationTypeEnum {
    PURCHASE(1),
    INSTALLMENT_PURCHASE(2),
    WITHDRAWAL(3),
    PAYMENT(4);

    private Integer id;
    public Integer getId() {
        return this.id;
    }


    OperationTypeEnum(int id) {
        this.id = id;
    }
}

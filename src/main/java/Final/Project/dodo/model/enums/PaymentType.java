package Final.Project.dodo.model.enums;

public enum PaymentType {
    CASH("Наличные"),
    CASHLESS("Без наличные"),
    CARD("Оплата картой курьеру");
    private final String def;

    PaymentType(String def) {
        this.def = def;
    }
}

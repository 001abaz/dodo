package Final.Project.dodo.model.enums;

public enum OrderStatus {
    NEW("Новый"),
    PREPARING("Готовится"),
    DELIVERING("Доставляется"),
    DELIVERED("Доставлен"),
    CANCELED("Отменен");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


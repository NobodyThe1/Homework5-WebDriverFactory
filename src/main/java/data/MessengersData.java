package data;

public enum MessengersData {
    WHATSAPP("whatsapp"),
    TELEGRAM("telegram");

    private String name;

    MessengersData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

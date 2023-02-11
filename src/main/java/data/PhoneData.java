package data;

public enum PhoneData {
    PHONE1("+7 999 876 54 32"),
    PHONE2("+7 999 123 45 67");


    private String name;

    PhoneData (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

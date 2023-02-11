package data;

public enum IdData {
    FNAME("fname"),
    LNAME("lname"),
    FNAME_LATIN("fname_latin"),
    LNAME_LATIN("lname_latin"),
    BLOG_NAME("blog_name"),
    CONTACT_1("contact-0-value"),
    CONTACT_2("contact-1-value");

    private String name;

    IdData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

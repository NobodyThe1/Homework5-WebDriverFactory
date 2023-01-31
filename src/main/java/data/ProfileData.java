package data;

public enum ProfileData {
    FNAME("fname"),
    LNAME("lname"),
    FNAME_LATIN("fname_latin"),
    LNAME_LATIN("lname_latin"),
    BLOG_NAME("blog_name");

    private String name;

    ProfileData (String name) {
        this.name = name;
    }
}

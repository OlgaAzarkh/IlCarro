package utils;

public enum HeaderMenuItem {
    SEARCH("//a[text()=' Search ']"),
    LET_CAR_WORK("//a[text()=' Let the car work ']"),
    TERMS("//a[text()=' Terms of use ']"),
    SIGN_UP("//a[text()=' Sign up ']"),
    LOGIN("//a[text()=' Log in ']");
    private final String locator;

    HeaderMenuItem(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}

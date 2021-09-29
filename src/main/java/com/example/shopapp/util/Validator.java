package com.example.shopapp.util;

public class Validator {

    private static final String LOGIN_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$";

    private static final String PRODUCT_NAME_REGEX = "^[0-9A-Za-zА-Яа-яіїІЇйЙєЄ\\s]+$";
    private static final String PRODUCT_DESCRIPTION_REGEX = "^[0-9A-Za-zА-Яа-яіїІЇйЙєЄ!()\\s]+$";
    private static final String PRODUCT_COLOR_REGEX = "^[A-Za-zА-Яа-яіїІЇйЙєЄ\\s]+$";
    private static final String PRODUCT_PRICE_REGEX = "^[ 0-9]+$";
    private static final String PRODUCT_MADE_IN_REGEX = "^[A-Za-zА-Яа-яіїІЇйЙєЄ\\s]+$";

    public static boolean validateProductNameWithRegex(String productName) {
        return productName.matches(PRODUCT_NAME_REGEX);
    }

    public static boolean validateDescriptionWithRegex(String description) {
        return description.matches(PRODUCT_DESCRIPTION_REGEX);
    }

    public static boolean validateProductColorWithRegex(String productColor) {
        return productColor.matches(PRODUCT_COLOR_REGEX);
    }

    public static boolean validateProductPriceWithRegex(String productPrice) {
        return productPrice.matches(PRODUCT_PRICE_REGEX);
    }

    public static boolean validateProductMadeInWithRegex(String productMadeIn) {
        return productMadeIn.matches(PRODUCT_MADE_IN_REGEX);
    }

    public static boolean validateLoginWithRegex(String login) {
        return login.matches(LOGIN_REGEX);
    }

    public static boolean validatePasswordWithRegex(String password) {
        return password.matches(PASSWORD_REGEX);
    }
}

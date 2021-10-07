package com.example.shopapp.util;

import java.util.HashSet;

import java.util.Set;

public class RoleAccess {

    private static Set<String> guestCommands = new HashSet<>();

    static {
        guestCommands.add("login");
        guestCommands.add("getAllProducts");
        guestCommands.add("addToCart");
        guestCommands.add("quantityIncDec");
        guestCommands.add("removeFromCart");
        guestCommands.add("filterProducts");
        guestCommands.add("sortProducts");
        guestCommands.add("pagination");
        guestCommands.add("switchLang");
        guestCommands.add("registration");
        guestCommands.add("buy");
    }

    public static Set<String> getGuestCommands() {
        return guestCommands;
    }

    private static Set<String> userCommands = new HashSet<>();

    static {
        userCommands.add("login");
        userCommands.add("getAllProducts");
        userCommands.add("addToCart");
        userCommands.add("quantityIncDec");
        userCommands.add("removeFromCart");
        userCommands.add("filterProducts");
        userCommands.add("sortProducts");
        userCommands.add("pagination");
        userCommands.add("switchLang");
        userCommands.add("registration");
        userCommands.add("logout");
        userCommands.add("buy");
        userCommands.add("getUserOrders");
        userCommands.add("getOrderItems");
        userCommands.add("error");
    }

    public static Set<String> getUserCommands() {
        return userCommands;
    }

    private static  Set<String> adminCommands = new HashSet<>();

    static {
        adminCommands.add("login");
        adminCommands.add("error");
        adminCommands.add("logout");
        adminCommands.add("getAllProducts");
        adminCommands.add("addToCart");
        adminCommands.add("quantityIncDec");
        adminCommands.add("removeFromCart");
        adminCommands.add("buy");
        adminCommands.add("getOrderItems");
        adminCommands.add("addNewProduct");
        adminCommands.add("openModifyForm");
        adminCommands.add("modifyProduct");
        adminCommands.add("deleteProduct");
        adminCommands.add("changeOrderState");
        adminCommands.add("blockUnblockUser");
        adminCommands.add("filterProducts");
        adminCommands.add("sortProducts");
        adminCommands.add("pagination");
        adminCommands.add("switchLang");
        adminCommands.add("registration");
        guestCommands.add("getUserOrders");
        adminCommands.add("getAllUsers");
        adminCommands.add("getAllOrders");
    }

    public static Set<String> getAdminCommands() {
        return adminCommands;
    }
}

package com.example.shopapp.command;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private static Map<String, ICommand> commands;

    static {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("error", new ErrorCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("getAllProducts", new FindAllProductsCommand());
        commands.put("addToCart", new AddToCartCommand());
        commands.put("quantityIncDec", new QuantityIncDecCommand());
        commands.put("removeFromCart", new RemoveFromCartCommand());
        commands.put("buy", new BuyCommand());
        commands.put("getOrderItems", new GetOrderItemsCommand());
        commands.put("addNewProduct", new AddNewProductCommand());
        commands.put("openModifyForm", new OpenModifyFormCommand());
        commands.put("modifyProduct", new ModifyProductCommand());
        commands.put("deleteProduct", new DeleteProductCommand());
        commands.put("changeOrderState", new ChangeOrderStateCommand());
        commands.put("blockUnblockUser", new BlockUnblockUserCommand());
        commands.put("filterProducts", new FilterProductsCommand());
        commands.put("sortProducts", new SortProductsCommand());
        commands.put("pagination", new PaginationCommand());
        commands.put("switchLang", new SwitchLangCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("getUserOrders", new GetUserOrdersCommand());
        commands.put("getAllUsers", new GetAllUsersCommand());
        commands.put("getAllOrders", new GetAllOrdersCommand());
    }

    public static ICommand getCommand(String commandName) {
        return commands.get(commandName);
    }
}

package com.example.shopapp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * The {@code SwitchLangCommand} class, responsible for changing languages(ua - en).
 *
 * @author Andrii Vasianovych
 * @version 1.0
 */
public class SwitchLangCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String localeParameter = req.getParameter("locale");
        Locale.Builder builder = new Locale.Builder();
        builder.setLanguageTag(localeParameter);
        Locale locale = builder.build();
        req.getSession().setAttribute("locale", locale);
        return req.getHeader("referer");
    }
}

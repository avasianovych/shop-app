package com.example.shopapp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class SwitchLangCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String localeParameter = req.getParameter("locale");
        Locale.Builder builder = new Locale.Builder();
        builder.setLanguageTag(localeParameter);
        Locale locale = builder.build();
        req.getSession().setAttribute("locale", locale);
        String header = req.getHeader("referer");
        return header;
    }
}

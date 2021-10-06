package com.example.shopapp.command;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandFactory} class, responsible for defining a proper command
 * for a particular URL query.
 *
 * @author Andrii Vasianovych
 * @version 1.0
 */
public class CommandFactory {
    private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);

    private CommandFactory() {
    }

    public static ICommand getCommand(HttpServletRequest req) {
        String command = req.getParameter("command");
        ICommand iCommand;

        if (command != null) {
            try {
                iCommand = CommandContainer.getCommand(command);
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.INFO, e);
                iCommand = CommandContainer.getCommand("error");
            }
        } else {
            iCommand = CommandContainer.getCommand("error");
        }
        return iCommand;
    }
}

package com.example.shopapp.command;

import com.example.shopapp.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code ICommand} interface, represent command pattern.
 *
 * @author Andrii Vasianovych
 * @version 1.0
 */
public interface ICommand {

    String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException;
}

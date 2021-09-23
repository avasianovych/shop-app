package com.example.shopapp.command;

import com.example.shopapp.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {

    String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException;
}

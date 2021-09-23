package com.example.shopapp.command;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

    private CommandFactory(){
    }

    public static ICommand getCommand(HttpServletRequest req){
        String command = req.getParameter("command");
        ICommand iCommand;

        if(command != null){
            try {
                iCommand = CommandContainer.getCommand(command);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
                iCommand = CommandContainer.getCommand("error");
            }
        }
        else {
            iCommand = CommandContainer.getCommand("error");
        }
        return iCommand;
    }
}

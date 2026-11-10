
package interpreter;

import java.util.Scanner;

import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyStack;
import interpreter.model.adt.MyList;

import interpreter.model.PrgState;
import interpreter.view.Examples;
import interpreter.model.stmt.IStmt;

import interpreter.repository.Repository;
import interpreter.controller.Controller;

import java.util.List;
import interpreter.model.PrgState;
import interpreter.model.stmt.IStmt;
import interpreter.repository.Repository;
import interpreter.controller.Controller;
import interpreter.view.Examples;
import interpreter.view.TextMenu;
import interpreter.view.ExitCommand;
import interpreter.view.RunExampleCommand;

public class Main {
    public static void main(String[] args) {
        List<IStmt> programs = List.of(
                Examples.example1,
                Examples.example2,
                Examples.example3,
                Examples.example4,
                Examples.example5
        );

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));

        for (int i = 0; i < programs.size(); i++) {
            IStmt program = programs.get(i);
            var repo = new Repository(PrgState.of(program), "log" + (i + 1) + ".txt");
            var controller = new Controller(repo, true);
            menu.addCommand(new RunExampleCommand(
                    String.valueOf(i + 1),
                    program.toString(),
                    controller
            ));
        }
        menu.show();
    }
}
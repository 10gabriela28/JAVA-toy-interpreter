
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

public class Main {
    public static void main(String[] args) {
        Examples ex = new Examples();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("------ TOY LANGUAGE INTERPRETER MENU ------ ");
            System.out.println("1. RUN EXAMPLE #1");
            System.out.println("2. RUN EXAMPLE #2");
            System.out.println("3. RUN EXAMPLE #3");
            System.out.println("4. EXIT");
            System.out.print("> ");

            String option = scanner.nextLine();
            IStmt st = null;

            switch (option) {
                case "1":
                    st = ex.example1;
                    break;
                case "2":
                    st = ex.example2;
                    break;
                case "3":
                    st = ex.example3;
                    break;
                case "4":
                    System.out.println("Exiting interpreter...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please choose between 1 and 4.");
                    continue;
            }

            try {
                PrgState prgState = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), st);
                Repository repo = new Repository(prgState);
                Controller controller = new Controller(repo);

                controller.allStep();
            } catch (Exception e) {
                System.out.println("runtime error! :(\n" + e.getMessage());
            }
        }
    }
}
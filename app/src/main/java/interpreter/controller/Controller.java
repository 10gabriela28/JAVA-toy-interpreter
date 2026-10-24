
package interpreter.controller;

import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

import interpreter.repository.IRepository;
import interpreter.model.PrgState;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IStack;
import interpreter.model.adt.IList;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;

public class Controller {
    private IRepository repo;
    private boolean display = true;
    private void display(PrgState state){
        if(display){
            System.out.println(state);
        }
    }

    public Controller(IRepository repo) {
        this.repo = repo;
    }


    public PrgState oneStep(PrgState state) throws MyException {
        var stack = state.getExeStack();
        if (stack.isEmpty()) {
            throw new StatementException("program state stack is empty");
        }
        IStmt crtStmt = stack.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws MyException {
        PrgState prg = repo.getCrtPrg();
        display(prg);                         // only if the display flag is on
        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);
            display(prg);

        }
    }
}



package interpreter.model.stmt;


import java.util.Stack;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import interpreter.model.PrgState;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.exp.IExp;
import interpreter.model.value.IValue;
import interpreter.model.type.IType;



public record AssignStmt(String id, IExp exp) implements IStmt {

    @Override
    public PrgState execute(PrgState state) throws MyException {
        var symTable = state.getSymTable();
        if(!symTable.isDefined(id)){
            throw new StatementException("this variable was not declared!");
        }
        IValue val = exp.eval(symTable);
        if(!val.getType().equals(symTable.lookup(id).getType())){
            throw new StatementException("the declared type of variable and the type of the assigned expression DO NOT match.");
        }
        symTable.update(id, val);
        return state;
    }

    @Override
    public IStmt deepCopy(){
        return new AssignStmt(id, exp.deepCopy());
    }

    @Override
    public String toString(){
        return id + " = " + exp;
    }
}


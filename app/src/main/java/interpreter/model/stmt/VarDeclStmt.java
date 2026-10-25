
package interpreter.model.stmt;


import java.util.Stack;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import interpreter.model.PrgState;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.exp.IExp;
import interpreter.model.type.IType;



public record VarDeclStmt(String id, IType val) implements IStmt {

    @Override
    public PrgState execute(PrgState state) throws MyException {
        var symTable = state.getSymTable();
        if (symTable.isDefined(id)) {
            throw new StatementException("this variable has already been declared!");
        }
        symTable.put(id, val.defaultValue());
        return state;
    }

    @Override
    public IStmt deepCopy(){
        return new VarDeclStmt(id, val);
    }

    @Override
    public String toString(){
        return id + val + ";";
    }
}


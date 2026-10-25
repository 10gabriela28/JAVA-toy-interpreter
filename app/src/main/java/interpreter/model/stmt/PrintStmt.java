
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.exp.IExp;
import interpreter.model.type.IType;
import interpreter.model.value.IValue;

public record PrintStmt(String id, IExp exp) implements IStmt {

    @Override
    public PrgState execute(PrgState state) throws MyException {
        var out = state.getOut();
        var symTable = state.getSymTable();
        if(!symTable.isDefined(id)){
            throw new StatementException("this variable was not defined!");
        }
        out.add(id);
        return state;
    }

    @Override
    public IStmt deepCopy(){
        return new PrintStmt(id, exp);
    }

    @Override
    public String toString(){
        return "print(" + id + ");" + " => " + exp;
    }
}

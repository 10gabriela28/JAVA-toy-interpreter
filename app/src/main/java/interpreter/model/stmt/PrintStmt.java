
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.exp.IExp;
import interpreter.model.type.IType;
import interpreter.model.value.IValue;

public record PrintStmt(IExp exp) implements IStmt {

    @Override
    public PrgState execute(PrgState state) throws MyException {
        var out = state.getOut();
        var symTable = state.getSymTable();

        IValue val = exp.eval(symTable);
        out.add(val);

        return state;
    }

    @Override
    public IStmt deepCopy(){
        return new PrintStmt(exp.deepCopy());
    }

    @Override
    public String toString(){
        return "print(" + exp + ");" + " => " + exp;
    }
}

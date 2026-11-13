
package interpreter.model.stmt;

import interpreter.exceptions.*;
import interpreter.model.exp.*;
import interpreter.model.value.*;
import interpreter.model.adt.*;
import interpreter.model.PrgState;


public record WhileStmt(IExp cond, IStmt body) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        var symTable = state.getSymTable();
        var heap = state.getHeap();
        var exeStack = state.getExeStack();

        IValue val = cond.eval(symTable, heap);
        if (!(val instanceof BoolValue(boolean conditionVal))) {
            throw new StatementException("While condition is not a boolean value.");
        }

        if (conditionVal) {
            exeStack.push(this);
            exeStack.push(body);
        }

        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(cond.deepCopy(), body.deepCopy());
    }

    @Override
    public String toString() {
        return "(while (" + cond + ") " + body + ")";
    }
}

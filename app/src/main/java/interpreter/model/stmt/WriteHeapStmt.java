
package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.IValue;
import interpreter.model.value.RefValue;

public record WriteHeapStmt(String varName, IExp exp) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        var symTable = state.getSymTable();
        var heap = state.getHeap();

        if (!symTable.isDefined(varName)) {
            throw new StatementException("this variable was not defined!");
        }
        if (!(symTable.lookup(varName) instanceof RefValue ref)) {
            throw new StatementException("this variable does not have a refvalue!");
        }
        int address = ref.address();
        if (!heap.isDefined(address)) {
            throw new StatementException("this address was not defined!");
        }

        IValue value = exp.eval(symTable, heap);
        if (!value.getType().equals(ref.locationType())) {
            throw new StatementException("the expression type does not match the location type!");
        }

        heap.update(address, value);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new WriteHeapStmt(varName, exp.deepCopy());
    }

    @Override
    public String toString() {
        return "wH(" + varName + ", " + exp + ")";
    }
}
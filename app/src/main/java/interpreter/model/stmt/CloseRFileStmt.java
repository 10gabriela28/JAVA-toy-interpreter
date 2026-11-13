
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.type.StringType;
import interpreter.model.value.IValue;
import interpreter.model.value.StringValue;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;

import java.io.BufferedReader;
import java.io.IOException;

public record CloseRFileStmt(IExp exp) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        var fileTable = state.getFileTable();

        IValue val = exp.eval(state.getSymTable(), state.getHeap());
        if (!val.getType().equals(new StringType())) {
            throw new StatementException("closeRFile: expression " + exp + " is not a string");
        }
        StringValue fileName = (StringValue) val;

        if (!fileTable.isDefined(fileName)) {
            throw new StatementException("closeRFile: file " + fileName + " is not open in FileTable");
        }

        BufferedReader reader = fileTable.lookup(fileName);
        try {
            reader.close();
            fileTable.remove(fileName);
        } catch (IOException e) {
            throw new StatementException("closeRFile error: " + e.getMessage());
        }

        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new CloseRFileStmt(exp.deepCopy());
    }

    @Override
    public String toString() {
        return "closeRFile(" + exp + ")";
    }
}
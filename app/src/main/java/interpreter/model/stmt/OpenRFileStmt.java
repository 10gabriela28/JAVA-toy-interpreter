
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.type.IType;
import interpreter.model.type.StringType;
import interpreter.model.value.IValue;
import interpreter.model.value.StringValue;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public record OpenRFileStmt(IExp exp) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        var fileTable = state.getFileTable();

        IValue val = exp.eval(state.getSymTable(), state.getHeap());
        if (!val.getType().equals(new StringType())) {
            throw new StatementException("cannot work with a non-string expression!");
        }
        StringValue filePath = (StringValue) val;

        if (fileTable.isDefined(filePath)) {
            throw new StatementException("this file is already open!");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath.val()));
            fileTable.put(filePath, reader);
        } catch (IOException e) {
            throw new StatementException("Error opening file: " + e.getMessage());
        }

        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new OpenRFileStmt(exp.deepCopy());
    }

    @Override
    public String toString() {
        return "openRFile(" + exp + ")";
    }
}

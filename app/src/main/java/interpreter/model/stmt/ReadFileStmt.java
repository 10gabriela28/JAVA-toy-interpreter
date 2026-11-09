
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.type.IntType;
import interpreter.model.type.StringType;
import interpreter.model.value.IntValue;
import interpreter.model.value.IValue;
import interpreter.model.value.StringValue;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;

import java.io.BufferedReader;
import java.io.IOException;

public record ReadFileStmt(IExp exp, String id) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        var symTable = state.getSymTable();
        var fileTable = state.getFileTable();

        if (!symTable.isDefined(id)) {
            throw new StatementException("this variable was not declared!");
        }

        if (!symTable.lookup(id).getType().equals(new IntType())) {
            throw new StatementException("this variable must be an integer!");
        }

        IValue val = exp.eval(symTable);
        if (!val.getType().equals(new StringType())) {
            throw new StatementException("this value must be a string!");
        }
        StringValue fileName = (StringValue) val;

        if (!fileTable.isDefined(fileName)) {
            throw new StatementException("this filename is not open in fileTable!");
        }

        BufferedReader reader = fileTable.lookup(fileName);
        try {
            String line = reader.readLine();
            int n = (line == null) ? 0 : Integer.parseInt(line.strip());
            symTable.update(id, new IntValue(n));
        } catch (IOException | NumberFormatException e) {
            throw new StatementException("readFile error: " + e.getMessage());
        }

        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFileStmt(exp.deepCopy(), id);
    }

    @Override
    public String toString() {
        return "readFile(" + exp + ", " + id + ")";
    }
}
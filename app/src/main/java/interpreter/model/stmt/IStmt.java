
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    IStmt deepCopy();
}



package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.exceptions.StatementException;

public interface IStmt {
    PrgState execute(PrgState state) throws StatementException;
    IStmt deepCopy();
}


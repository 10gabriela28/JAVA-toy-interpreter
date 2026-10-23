
package interpreter.model.stmt;

public interface IStmt {
    PrgState execute(PrgState state) throws StatementException;
    IStmt deepCopy();
    MyIDictionary
}

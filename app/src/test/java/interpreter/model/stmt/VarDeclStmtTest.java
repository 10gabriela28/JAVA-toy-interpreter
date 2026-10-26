
package interpreter.model.stmt;

import interpreter.model.value.IValue;
import interpreter.model.type.IntType;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyStack;
import interpreter.model.adt.MyList;
import interpreter.model.PrgState;
import interpreter.exceptions.MyException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class VarDeclStmtTest {
    @Test
    void testVarDecl() throws MyException {
        IDictionary<String, IValue> symTable = new MyDictionary<>();
        PrgState state = new PrgState(new MyStack<>(), symTable, new MyList<>(), null);

        IStmt decl = new VarDeclStmt("n", new IntType());
        decl.execute(state);

        assertNotNull(symTable.lookup("n"));
    }

    @Test
    void redeclaredVariableThrows() {
        IDictionary<String, IValue> symTable = new MyDictionary<>();
        PrgState state = new PrgState(new MyStack<>(), symTable, new MyList<>(), null);

        IStmt decl1 = new VarDeclStmt("n", new IntType());
        IStmt decl2 = new VarDeclStmt("n", new IntType());

        assertDoesNotThrow(() -> decl1.execute(state));
        assertThrows(MyException.class, () -> decl2.execute(state));
    }
}
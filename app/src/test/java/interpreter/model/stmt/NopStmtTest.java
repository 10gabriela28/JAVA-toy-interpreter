
package interpreter.model.stmt;

import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.IStack;
import interpreter.model.adt.MyStack;
import interpreter.model.adt.IList;
import interpreter.model.adt.MyList;
import interpreter.model.PrgState;
import interpreter.exceptions.MyException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NopStmtTest {
    @Test
    void testNopExecutionDoesNothing() throws MyException {
        IDictionary<String, interpreter.model.value.IValue> symTable = new MyDictionary<>();
        MyStack<IStmt> exeStack = new MyStack<>();
        PrgState state = new PrgState(exeStack, symTable, new MyList<>(), null);

        IStmt nop = new NopStmt();

        assertDoesNotThrow(() -> nop.execute(state));
    }
}
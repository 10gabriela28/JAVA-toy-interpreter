
package interpreter.model.stmt;

import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.exp.ValueExp;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyStack;
import interpreter.model.adt.MyList;
import interpreter.model.PrgState;
import interpreter.exceptions.MyException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PrintStmtTest {
    @Test
    void testPrintExecutionAddsToList() throws MyException {
        IDictionary<String, IValue> symTable = new MyDictionary<>();
        MyList<IValue> outList = new MyList<>();
        PrgState state = new PrgState(new MyStack<>(), symTable, outList, null);

        IStmt printStmt = new PrintStmt(new ValueExp(new IntValue(42)));
        printStmt.execute(state);

        assertEquals(1, outList.size());
        assertEquals(new IntValue(42), outList.get(0));
    }
}
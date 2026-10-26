
package interpreter.model.stmt;

import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.type.IntType;
import interpreter.model.exp.ValueExp;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyStack;
import interpreter.model.adt.MyList;
import interpreter.model.PrgState;
import interpreter.exceptions.MyException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CompStmtTest {

    @Test
    void testCompStmtPushesToStack() throws MyException {
        IDictionary<String, IValue> symTable = new MyDictionary<>();
        MyStack<IStmt> exeStack = new MyStack<>();
        PrgState state = new PrgState(exeStack, symTable, new MyList<>(), null);

        IStmt comp = new CompStmt(
                new VarDeclStmt("n", new IntType()),
                new AssignStmt("n", new ValueExp(new IntValue(10)))
        );

        comp.execute(state);

        assertFalse(exeStack.isEmpty());
    }
}
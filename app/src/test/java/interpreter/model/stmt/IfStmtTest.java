
package interpreter.model.stmt;

import interpreter.model.value.IValue;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import interpreter.model.type.BoolType;
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

class IfStmtTest {
    @Test
    void testIfExecution() throws MyException {
        IDictionary<String, IValue> symTable = new MyDictionary<>();
        MyStack<IStmt> exeStack = new MyStack<>();
        PrgState state = new PrgState(exeStack, symTable, new MyList<>(), null);

        new VarDeclStmt("n", new IntType()).execute(state);

        IStmt ifStmt = new IfStmt(
                new ValueExp(new BoolValue(true)),
                new AssignStmt("n", new ValueExp(new IntValue(2))),
                new AssignStmt("n", new ValueExp(new IntValue(3)))
        );

        ifStmt.execute(state);
        assertFalse(exeStack.isEmpty());
    }

    @Test
    void nonBooleanIfConditionThrows() {
        IDictionary<String, IValue> symTable = new MyDictionary<>();
        PrgState state = new PrgState(new MyStack<>(), symTable, new MyList<>(), null);

        IStmt ifStmt = new IfStmt(new ValueExp(new IntValue(1)),
                new NopStmt(),
                new NopStmt());

        assertThrows(MyException.class, () -> ifStmt.execute(state)); //[cite: 5]
    }
}
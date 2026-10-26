
package interpreter.model.exp;

import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.BoolValue;

import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;

import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;

import interpreter.model.exp.LogicOp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LogicExpTest {
    private final IDictionary<String, IValue> symTable = new MyDictionary<>();

    private static IExp boo(boolean v) { return new ValueExp(new BoolValue(v)); }

    @Test
    void testAndOperation() throws MyException {
        IExp e = new LogicExp(LogicOp.AND, boo(true), boo(false));
        assertEquals(new BoolValue(false), e.eval(symTable));
    }

    @Test
    void testOrOperation() throws MyException {
        IExp e = new LogicExp(LogicOp.OR, boo(true), boo(false));
        assertEquals(new BoolValue(true), e.eval(symTable));
    }

    @Test
    void nonBooleanOpThrows() {
        IExp e = new LogicExp(LogicOp.AND, new ValueExp(new IntValue(1)), boo(true));
        assertThrows(MyException.class, () -> e.eval(symTable));
    }

}

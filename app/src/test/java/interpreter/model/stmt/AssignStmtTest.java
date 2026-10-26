
package interpreter.model.stmt;

import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.BoolValue;

import interpreter.model.stmt.IStmt;
import interpreter.model.stmt.CompStmt;
import interpreter.model.stmt.VarDeclStmt;
import interpreter.model.stmt.AssignStmt;

import interpreter.model.type.IType;
import interpreter.model.type.IntType;

import interpreter.model.exp.ValueExp;
import interpreter.model.exp.VarExp;

import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;

import interpreter.model.adt.IStack;
import interpreter.model.adt.MyStack;

import interpreter.model.adt.IList;
import interpreter.model.adt.MyList;

import interpreter.model.PrgState;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;

import interpreter.model.exp.LogicOp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AssignStmtTest {
    private final IDictionary<String, IValue> symTable = new MyDictionary<>();
    PrgState state = new PrgState(new MyStack<>(), symTable, new MyList<>(), null);


    @Test
    void testAssign() throws MyException{
        IStmt decl = new VarDeclStmt("n", new IntType());
        IStmt assign = new AssignStmt("n", new ValueExp(new IntValue(5)));

        decl.execute(state);
        assign.execute(state);

        assertEquals(new IntValue(5), symTable.lookup("n"));
    }
}

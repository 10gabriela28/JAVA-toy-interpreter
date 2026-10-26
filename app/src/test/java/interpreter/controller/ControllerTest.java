
package interpreter.controller;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IntValue;
import interpreter.repository.Repository;
import interpreter.view.Examples;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ControllerTest {
    @Test
    void example2PrintsEighteen() throws MyException {
        Examples examples = new Examples();
        PrgState state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), examples.example2);
        Repository repo = new Repository(state);
        Controller controller = new Controller(repo);

        controller.allStep();

        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(17), state.getOut().get(0));
    }

    @Test
    void oneStepOnEmptyStackThrows() {
        PrgState state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), null);
        Repository repo = new Repository(state);
        Controller controller = new Controller(repo);

        assertThrows(MyException.class, () -> controller.oneStep(state));
    }
}

package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class StackTest {
    @Test
    void testLIFOOrder() throws ADTException {
        IStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void popEmptyStackThrows() {
        IStack<Integer> stack = new MyStack<>();
        assertThrows(ADTException.class, stack::pop);
    }
}
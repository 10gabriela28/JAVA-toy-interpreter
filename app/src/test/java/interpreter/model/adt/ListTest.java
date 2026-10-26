
package interpreter.model.adt;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ListTest {
    @Test
    void testListOperations() {
        IList<String> list = new MyList<>();
        list.add("first");
        list.add("second");

        assertEquals(2, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
    }
}
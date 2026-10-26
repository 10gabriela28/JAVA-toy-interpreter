
package interpreter.model.adt;

import interpreter.exceptions.MyException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DictionaryTest {
    @Test
    void testDictionaryOperations() throws MyException {
        IDictionary<String, Integer> dict = new MyDictionary<>();
        assertFalse(dict.isDefined("a"));

        dict.put("a", 10);
        assertTrue(dict.isDefined("a"));
        assertEquals(10, dict.lookup("a"));

        dict.update("a", 20);
        assertEquals(20, dict.lookup("a"));
    }

    @Test
    void lookupMissingKeyThrows() {
        IDictionary<String, Integer> dict = new MyDictionary<>();
        assertThrows(MyException.class, () -> dict.lookup("missing"));
    }

    @Test
    void updateMissingKeyThrows() {
        IDictionary<String, Integer> dict = new MyDictionary<>();
        assertThrows(MyException.class, () -> dict.update("missing", 5));
    }
}

package interpreter.model.adt;

import java.util.Map;
import interpreter.model.value.IValue;
import interpreter.exceptions.ADTException;

public interface IHeap {
    int allocate(IValue value);
    IValue get(int address) throws ADTException;
    void update(int address, IValue value) throws ADTException;
    boolean isDefined(int address);
    Map<Integer, IValue> getContent();
    void setContent(Map<Integer, IValue> content);
}
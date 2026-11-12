
package interpreter.model.adt;

import java.util.Map;
import java.util.HashMap;

import interpreter.model.value.IValue;
import interpreter.exceptions.MyException;
import interpreter.exceptions.ADTException;

public class MyHeap implements IHeap {
    private Map<Integer, IValue> heap = new HashMap<>();
    private int nextFree = 1; // address 0 means null.

    @Override
    public int allocate(IValue value){
        int address = nextFree++;
        heap.put(address, value);
        return address;
    }

    @Override
    public IValue get(int address) throws ADTException {
        if (!isDefined(address)) {
            throw new ADTException("Address " + address + " is not defined in the heap!");
        }
        return heap.get(address);
    }

    @Override
    public void update(int address, IValue value) throws ADTException {
        if(!isDefined(address)){
            throw new ADTException("Address " + address + " has not been defined yet!");
        }
        heap.put(address, value);
    }

    @Override
    public boolean isDefined(int address){
        return heap.containsKey(address);
    }

    @Override
    public Map<Integer, IValue> getContent(){
        return new HashMap<>(heap);
    }

    @Override
    public void setContent(Map<Integer, IValue> content){
        heap = new HashMap<>(content);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
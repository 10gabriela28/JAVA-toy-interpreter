
package interpreter.model.exp;

import interpreter.exceptions.*;
import interpreter.model.adt.*;
import interpreter.model.value.*;
import interpreter.model.stmt.*;
import interpreter.model.PrgState;

public record ReadHeapExp(IExp exp) implements IExp{
    @Override
    public IValue eval(IDictionary<String, IValue> symTable, IHeap heap) throws MyException {
        IValue val = exp.eval(symTable, heap);
        if (!(val instanceof RefValue ref)) {
            throw new ExpressionException("rH: " + exp + " is not a RefValue");
        }
        int address = ref.address();
        if (!heap.isDefined(address)) {
            throw new ExpressionException("rH: address " + address + " is not defined in the heap");
        }
        return heap.get(address);
    }

    @Override
    public IExp deepCopy(){
        return new ReadHeapExp(exp.deepCopy());
    }

    @Override
    public String toString(){
        return "rH(" + exp + ")";
    }

}



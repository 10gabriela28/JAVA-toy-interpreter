
package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.IDictionary;
import interpreter.model.value.IValue;

public record VarExp(String id) implements IExp {
    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {
        if(!symTable.isDefined(id)){
            throw new ExpressionException("value does not exist!");
        }
    }

    @Override
    public String toString(){
        return id;
    }
}

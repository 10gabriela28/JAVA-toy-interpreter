
package interpreter.model.exp;

import interpreter.model.exp.IExp;
import interpreter.model.exp.RelOp;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.BoolValue;
import interpreter.model.adt.IDictionary;
import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;



public record RelationalExp(RelOp op, IExp left, IExp right) implements IExp {
    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException{
        if(!(left.eval(symTable) instanceof IntValue(int n1))){
            throw new ExpressionException("this variable wasn't defined!");
        }
        if(!(right.eval(symTable) instanceof IntValue(int n2))){
            throw new ExpressionException("this variable wasn't defined!");
        }
        return switch(op){
            case SMALLERTHAN -> new BoolValue(n1 < n2);
            case SMALLEROREQUALTO -> new BoolValue(n1 <= n2);
            case GREATERTHAN -> new BoolValue(n1 > n2);
            case GREATEROREQUALTO -> new BoolValue(n1 >= n2);
            case EQUALTO -> new BoolValue(n1 == n2);
            case NOTEQUALTO -> new BoolValue(n1 != n2);
        };
    }

    @Override
    public IExp deepCopy(){
        return new RelationalExp(op, left.deepCopy(), right.deepCopy());
    }

    @Override
    public String toString(){
        return "(" + left + " " + op + " " + right + ")";
    }
}

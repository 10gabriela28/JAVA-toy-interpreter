
package interpreter.model.exp;

import interpreter.model.exp.IExp;
import interpreter.model.exp.ArithOp;
import interpreter.model.value.IValue;
import interpreter.model.adt.IDictionary;
import interpreter.exceptions.MyException;

public record ArithExp(ArithOp op, IExp left, IExp right) {
    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {
        if(!(left.eval(symTable) instanceof IntValue(int n1))){
            throw new ExpressionException("the first operand is not an integer.");
        }
        if(!(right.eval(symTable) instanceof IntValue(int n2))){
            throw newExpressionException("the second operand is not an integer.");
        }
        return switch(op) {
            case ADD -> new IntValue(n1 + n2);
            case SUB -> new IntValue(n1 - n2);
            case MUL -> new IntValue(n1 * n2);
            case DIV -> {
                if (n2==0) {
                    throw new ExpressionException("cannot divide by zero!");
                }
                yield new IntValue(n1 / n2);
            }
        };
    }
    @Override
    public String toString(){
        return "(" + left + " " + op + " " + right + ")";
    }
}


// LEFT TO DO:
// ValueExp (returns its value), VarExp (looks the variable up)
// LogicOp + LogicExp (both "and" and "or" must be booleans.)

package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.type.IType;
import interpreter.model.type.BoolType;
import interpreter.model.value.IValue;
import interpreter.model.value.BoolValue;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;

public record IfStmt(IExp exp, IStmt thenStmt, IStmt elseStmt) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException{
        var symTable = state.getSymTable();
        var exeStack = state.getExeStack();

        IValue val = exp.eval(symTable);
        if(!val.getType().equals(new BoolType())){
            throw new StatementException("the expression can ONLY evaluate to a boolean value.");
        }

        BoolValue boolVal = (BoolValue) val;

        if(boolVal.val()){
            exeStack.push(thenStmt);
        }
        else{
            exeStack.push(elseStmt);
        }
        return state;
    }


    @Override
    public IStmt deepCopy(){
        return new IfStmt(exp.deepCopy(), thenStmt.deepCopy(), elseStmt.deepCopy());
    }

    @Override
    public String toString(){
        return "if " + exp + ", then " + thenStmt + "\n" + "else " + elseStmt + ".";
    }

}

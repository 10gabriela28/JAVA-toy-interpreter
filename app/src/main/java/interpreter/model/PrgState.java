
package interpreter.model;

import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IStack;
import interpreter.model.adt.IList;


public class PrgState {
    private IStack<IStmt> exeStack;
    private IDictionary<String, IValue> symTable;
    private IList<IValue> out;
    private final IStmt originalProgram;

    public PrgState(IStack<IStmt> exeStack, IDictionary<String, IValue> symTable, IList<IValue> out, IStmt program){
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = program;
        exeStack.push(program);
    }

    public IStack getExeStacl(){
        return exeStack;
    }

    public IDictionary getSymTable(){
        return symTable;
    }

    public IList getOut(){
        return out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setExeStack(IStack exeStack){
        this.exeStack = exeStack;
    }

    public void setSymTable(IDictionary symTable){
        this.symTable = symTable;
    }

    public void setOut(IList out){
        this.out = out;
    }

    @Override
    public String toString() {
        return "PrgState:\n" + exeStack + "\n" +
                symTable + "\n" + out + "\n";
    }

}


package interpreter.model;

import java.io.BufferedReader;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;
import interpreter.model.value.StringValue;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IStack;
import interpreter.model.adt.IList;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyStack;
import interpreter.model.adt.MyList;

public class PrgState {
    private IStack<IStmt> exeStack;
    private IDictionary<String, IValue> symTable;
    private IDictionary<StringValue, BufferedReader> fileTable; // Correct types!
    private IList<IValue> out;
    private final IStmt originalProgram;

    public PrgState(IStack<IStmt> exeStack, IDictionary<String, IValue> symTable,
                    IDictionary<StringValue, BufferedReader> fileTable, IList<IValue> out, IStmt program){
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.fileTable = fileTable; // Correctly assigned!
        this.out = out;
        this.originalProgram = program;
        if (program != null) {
            exeStack.push(program);
        }
    }

    public static PrgState of(IStmt program) {
        return new PrgState(new MyStack<>(), new MyDictionary<>(), new MyDictionary<>(), new MyList<>(), program);
    }

    public IStack<IStmt> getExeStack(){
        return exeStack;
    }

    public IDictionary<String, IValue> getSymTable(){
        return symTable;
    }

    public IDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IList<IValue> getOut(){
        return out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setExeStack(IStack<IStmt> exeStack){
        this.exeStack = exeStack;
    }

    public void setSymTable(IDictionary<String, IValue> symTable){
        this.symTable = symTable;
    }

    public void setFileTable(IDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public void setOut(IList<IValue> out){
        this.out = out;
    }

    @Override
    public String toString() {
        return "PrgState:\n" + exeStack + "\n" +
                symTable + "\n" + fileTable + "\n" + out + "\n";
    }

    public String toLogString() {
        var sb = new StringBuilder();
        sb.append("ExeStack:\n");
        exeStack.toList().forEach(s -> sb.append(s).append('\n'));
        sb.append("SymTable:\n");
        symTable.toMap().forEach((k, v) ->
                sb.append(k).append(" --> ").append(v).append('\n'));
        sb.append("Out:\n");
        out.toList().forEach(v -> sb.append(v).append('\n'));
        sb.append("FileTable:\n");
        fileTable.toMap().keySet().forEach(f -> sb.append(f.val()).append('\n'));
        return sb.toString();
    }

}
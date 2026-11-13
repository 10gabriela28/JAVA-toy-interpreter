
package interpreter.model;

import java.io.BufferedReader;
import java.util.TreeMap;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;
import interpreter.model.value.StringValue;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IStack;
import interpreter.model.adt.IList;
import interpreter.model.adt.IHeap;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyStack;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyHeap;

public class PrgState {
    private IStack<IStmt> exeStack;
    private IDictionary<String, IValue> symTable;
    private IList<IValue> out;
    private IDictionary<StringValue, BufferedReader> fileTable;
    private IHeap heap;
    private final IStmt originalProgram;

    public PrgState(IStack<IStmt> exeStack, IDictionary<String, IValue> symTable,
                    IList<IValue> out, IDictionary<StringValue, BufferedReader> fileTable,
                    IHeap heap, IStmt program){
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = program;
        if (program != null) {
            exeStack.push(program);
        }
    }

    public PrgState(IStack<IStmt> exeStack, IDictionary<String, IValue> symTable,
                    IList<IValue> out, IStmt program) {
        this(exeStack, symTable, out, new MyDictionary<>(), new MyHeap(), program);
    }

    public static PrgState of(IStmt program){
        return new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyDictionary<>(),
                new MyHeap(),
                program
        );
    }

    public IStack<IStmt> getExeStack(){
        return exeStack;
    }

    public IDictionary<String, IValue> getSymTable(){
        return symTable;
    }

    public IList<IValue> getOut(){
        return out;
    }

    public IDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IHeap getHeap() {
        return heap;
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

    public void setOut(IList<IValue> out){
        this.out = out;
    }

    public void setFileTable(IDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public void setHeap(IHeap heap) {
        this.heap = heap;
    }

    @Override
    public String toString() {
        return "PrgState:\n" + exeStack + "\n" +
                symTable + "\n" + out + "\n" + fileTable + "\n" + heap + "\n";
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

        sb.append("Heap:\n");
        new TreeMap<>(heap.getContent()).forEach((addr, val) ->
                sb.append(addr).append(" --> ").append(val).append('\n'));

        return sb.toString();
    }
}
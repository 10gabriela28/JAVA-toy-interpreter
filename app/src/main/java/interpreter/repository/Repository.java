
package interpreter.repository;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import interpreter.model.PrgState;
import interpreter.exceptions.MyException;
import interpreter.exceptions.RepositoryException;

public class Repository implements IRepository {
    private List<PrgState> prgList;
    private int currPos;
    private final String logFilePath;

    public Repository(PrgState initialPrgState, String logFilePath) {
        this.prgList = new ArrayList<>();
        this.prgList.add(initialPrgState);
        this.currPos = 0;
        this.logFilePath = logFilePath;
    }

    @Override
    public PrgState getCrtPrg() {
        return prgList.get(currPos);
    }

    @Override
    public void setPrgList(List<PrgState> newPrgList){
        this.prgList = newPrgList;
    }

    @Override
    public void add(PrgState prgstate){
        prgList.add(prgstate);
    }

    @Override
    public List<PrgState> getPrgList(){
        return prgList;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (PrgState state : prgList) {
            sb.append(state.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void logPrgStateExec() throws MyException {
        try (var logFile = new PrintWriter(
                new BufferedWriter(new FileWriter(logFilePath, true)))) { // append
            logFile.println(getCrtPrg().toLogString());
        } catch (IOException e) {
            throw new RepositoryException("cannot write the log file " + logFilePath
                    + ": " + e.getMessage());
        }
    }
}
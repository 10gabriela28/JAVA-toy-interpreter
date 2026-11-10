
package interpreter.repository;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import java.util.List;

public interface IRepository {
    PrgState getCrtPrg();
    void add(PrgState prgstate);
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> prgList);
    void logPrgStateExec() throws MyException;
}


package interpreter.repository;

import interpreter.model.PrgState;
import java.util.List;

public interface IRepository {
    PrgState getCrtPrg();
    void add(PrgState prgstate);
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> prgList);
}

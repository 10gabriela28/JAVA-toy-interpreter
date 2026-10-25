
package interpreter.repository;

import java.util.List;
import java.util.ArrayList;
import interpreter.model.PrgState;

public class Repository implements IRepository {
    private List<PrgState> prgList;
    private int currPos;

    public Repository(PrgState initialPrgState) {
        this.prgList = new ArrayList<>();
        this.prgList.add(initialPrgState);
        this.currPos = 0;
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


}

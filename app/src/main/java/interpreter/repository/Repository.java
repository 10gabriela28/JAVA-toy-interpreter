
package interpreter.repository;

import java.util.ArrayList;
import interpreter.model.PrgState;

public class Repository implements IRepository {


    @Override
    public int getCrtPrg(){
        return getOriginalProgram();
    }
}

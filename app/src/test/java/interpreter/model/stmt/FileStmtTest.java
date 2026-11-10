
package interpreter.model.stmt;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;

import interpreter.model.PrgState;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import interpreter.model.type.IntType;

class FileStmtTest {
    private final String fileName = "numbers.in";
    private PrgState state;
    private ValueExp fileExp;

    @BeforeEach
    void setUp() throws Exception {
        Files.writeString(Path.of(fileName), "7\n");
        fileExp = new ValueExp(new StringValue(fileName));
        state = PrgState.of(new NopStmt());
        new VarDeclStmt("x", new IntType()).execute(state);
    }

    @AfterEach
    void tearDown() throws Exception {
        Files.deleteIfExists(Path.of(fileName));
    }

    @Test
    void readGivesTheNextNumberThenZeroAtTheEnd() throws MyException {
        new OpenRFileStmt(fileExp).execute(state);
        new ReadFileStmt(fileExp, "x").execute(state);
        assertEquals(new IntValue(7), state.getSymTable().lookup("x"));
        new ReadFileStmt(fileExp, "x").execute(state);
        assertEquals(new IntValue(0), state.getSymTable().lookup("x"));
        new CloseRFileStmt(fileExp).execute(state);
    }

    @Test
    void openingTheSameFileTwiceThrows() throws MyException {
        new OpenRFileStmt(fileExp).execute(state);
        assertThrows(StatementException.class,
                () -> new OpenRFileStmt(fileExp).execute(state));
        new CloseRFileStmt(fileExp).execute(state);
    }
}
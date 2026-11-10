
package interpreter.view;

import interpreter.model.exp.*;
import interpreter.model.stmt.IStmt;
import interpreter.model.stmt.VarDeclStmt;
import interpreter.model.stmt.AssignStmt;
import interpreter.model.stmt.IfStmt;
import interpreter.model.stmt.PrintStmt;
import interpreter.model.stmt.NopStmt;
import interpreter.model.stmt.CompStmt;
import interpreter.model.stmt.OpenRFileStmt;
import interpreter.model.stmt.ReadFileStmt;
import interpreter.model.stmt.CloseRFileStmt;
import interpreter.model.type.IType;
import interpreter.model.type.IntType;
import interpreter.model.type.BoolType;
import interpreter.model.type.StringType;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.BoolValue;
import interpreter.model.value.StringValue;

public class Examples {
    public static final IStmt example1 = new CompStmt(new VarDeclStmt("v", new IntType()),
            new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                    new PrintStmt(new VarExp("v"))));


    public static final IStmt example2 = new CompStmt(new VarDeclStmt("n", new IntType()),
            new CompStmt(new AssignStmt("n", new ValueExp(new IntValue(4))),
                    new CompStmt(new VarDeclStmt("m", new IntType()),
                            new CompStmt(new AssignStmt("m", new ValueExp(new IntValue(13))),
                                    new PrintStmt(new ArithExp(ArithOp.ADD, new VarExp("n"), new VarExp("m")))))));


    public static final IStmt example3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
            new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                            new CompStmt(new IfStmt(new VarExp("a"),
                                    new AssignStmt("v", new ValueExp(new IntValue(2))),
                                    new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                    new PrintStmt(new VarExp("v"))))));


    public static final IStmt example4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
            new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                    new CompStmt(new OpenRFileStmt(new VarExp("varf")),
                            new CompStmt(new VarDeclStmt("varc", new IntType()),
                                    new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                            new CompStmt(new PrintStmt(new VarExp("varc")),
                                                    new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                            new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                    new CloseRFileStmt(new VarExp("varf"))))))))));

    public static final IStmt example5 = new CompStmt(
            new VarDeclStmt("a", new IntType()),
            new CompStmt(new VarDeclStmt("b", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(5))),
                            new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(7))),
                                    new IfStmt(
                                            new RelationalExp(RelOp.SMALLERTHAN, new VarExp("a"), new VarExp("b")),
                                            new PrintStmt(new VarExp("a")),
                                            new PrintStmt(new VarExp("b"))
                                    )
                            )
                    )
            )
    );
}

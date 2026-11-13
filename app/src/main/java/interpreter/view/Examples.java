
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

    // this is for testing. can't directly use example 4 because of hardcoding. yay
    public static IStmt fileExample(String fileName) {
        return new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue(fileName))),
                        new CompStmt(new OpenRFileStmt(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFileStmt(new VarExp("varf"))))))))));
    }

    // my own example :) - very similar to example 4. just genuinely trying to understand this concept.
    // string fileP  ; fileP = "test2.in"; int a; string customMsg1; customMsg1 = "oops :("; string customMsg2; customMsg2 = "tough luck :("; if (!fileP.isEmpty()) { openRFile(fileP); readFile(fileP, a)  ; if (a > 10) { print(a) } else { print(customMsg1); } closeRFile(fileP); } else { print(customMsg2); }
    public static IStmt example6 = new CompStmt(
            new VarDeclStmt("fileP", new StringType()),
            new CompStmt(
                    new AssignStmt("fileP", new ValueExp(new StringValue("test2.in"))),
                    new CompStmt(
                            new VarDeclStmt("a", new IntType()),
                            new CompStmt(
                                    new VarDeclStmt("customMsg1", new StringType()),
                                    new CompStmt(
                                            new AssignStmt("customMsg1", new ValueExp(new StringValue("oops :("))),
                                            new CompStmt(
                                                    new VarDeclStmt("customMsg2", new StringType()),
                                                    new CompStmt(
                                                            new AssignStmt("customMsg2", new ValueExp(new StringValue("tough luck :("))),
                                                            new IfStmt(
                                                                    new RelationalExp(RelOp.NOTEQUALTO, new VarExp("fileP"), new ValueExp(new StringValue(""))),
                                                                    new CompStmt(
                                                                            new OpenRFileStmt(new VarExp("fileP")),
                                                                            new CompStmt(
                                                                                    new ReadFileStmt(new VarExp("fileP"), "a"),
                                                                                    new CompStmt(
                                                                                            new IfStmt(
                                                                                                    new RelationalExp(RelOp.GREATERTHAN, new VarExp("a"), new ValueExp(new IntValue(10))),
                                                                                                    new PrintStmt(new VarExp("a")),
                                                                                                    new PrintStmt(new VarExp("customMsg1"))
                                                                                            ),
                                                                                            new CloseRFileStmt(new VarExp("fileP"))
                                                                                    )
                                                                            )
                                                                    ),
                                                                    new PrintStmt(new VarExp("customMsg2"))
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            )
    );

    //int a; int b; bool c; a=1; b=2; c=a!=b; print(c); // 7 statements
    public static IStmt labExample1 = new CompStmt(
            new VarDeclStmt("a", new IntType()),
            new CompStmt(
                    new VarDeclStmt("b", new IntType()),
                    new CompStmt(
                            new VarDeclStmt("c", new BoolType()),
                            new CompStmt(
                                    new AssignStmt("a", new ValueExp(new IntValue(1))),
                                    new CompStmt(
                                            new AssignStmt("b", new ValueExp(new IntValue(2))),
                                            new CompStmt(
                                                    new AssignStmt("c", new RelationalExp(RelOp.NOTEQUALTO, new VarExp("a"), new VarExp("b"))),
                                                    new PrintStmt(new VarExp("c"))
                                            )
                                    )
                            )
                    )
            )
    );

}

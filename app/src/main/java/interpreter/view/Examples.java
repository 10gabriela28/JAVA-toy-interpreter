
package interpreter.view;

import interpreter.model.stmt.IStmt;
import interpreter.model.stmt.VarDeclStmt;
import interpreter.model.stmt.AssignStmt;
import interpreter.model.stmt.IfStmt;
import interpreter.model.stmt.PrintStmt;
import interpreter.model.stmt.NopStmt;
import interpreter.model.stmt.CompStmt;
import interpreter.model.type.IType;
import interpreter.model.type.IntType;
import interpreter.model.type.BoolType;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.BoolValue;
import interpreter.model.exp.IExp;
import interpreter.model.exp.ValueExp;
import interpreter.model.exp.VarExp;

import interpreter.model.exp.IExp;
import interpreter.model.exp.ArithOp;
import interpreter.model.exp.ArithExp;
import interpreter.model.exp.LogicExp;
import interpreter.model.exp.LogicOp;
import interpreter.model.exp.ValueExp;
import interpreter.model.exp.VarExp;

public class Examples {
    public IStmt example1 = new CompStmt(new VarDeclStmt("v", new IntType()),
            new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                    new PrintStmt(new VarExp("v"))));


    public IStmt example2 = new CompStmt(new VarDeclStmt("n", new IntType()),
            new CompStmt(new AssignStmt("n", new ValueExp(new IntValue(4))),
                    new CompStmt(new VarDeclStmt("m", new IntType()),
                            new CompStmt(new AssignStmt("m", new ValueExp(new IntValue(13))),
                                    new PrintStmt(new ArithExp(ArithOp.ADD, new VarExp("n"), new VarExp("m")))))));


    public IStmt example3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
            new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                            new CompStmt(new IfStmt(new VarExp("a"),
                                    new AssignStmt("v", new ValueExp(new IntValue(2))),
                                    new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                    new PrintStmt(new VarExp("v"))))));

}

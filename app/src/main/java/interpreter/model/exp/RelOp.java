
package interpreter.model.exp;

public enum RelOp {
    SMALLERTHAN("<"), SMALLEROREQUALTO("<="), GREATERTHAN(">"), GREATEROREQUALTO(">="),
    EQUALTO("=="), NOTEQUALTO("!=");
    private final String symbol;
    RelOp(String symbol) { this.symbol = symbol; }
    @Override
    public String toString() { return symbol; }
}

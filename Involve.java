public class Involve extends Operator
{
    public Involve()
    {
        super(EOperator.Involve);
    }

    @Override
    public char getKey() {
        return '>';
    }

    @Override
    public void display() {
        // todo
    }

    @Override
    public String toString() {
        return ">";
    }
}

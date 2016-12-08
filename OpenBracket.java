
public class OpenBracket extends Bracket
{
    public OpenBracket()
    {
        super(EBracket.Open);
    }

    @Override
    int getBracketNumber()
    {
        return +1;
    }

    @Override
    public char getKey() {
        return '(';
    }

    @Override
    public void display() {
        // todo
    }

    @Override
    public String toString() {
        return "(";
    }
}

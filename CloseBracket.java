
public class CloseBracket extends Bracket
{
    public CloseBracket()
    {
        super(EBracket.Close);
    }

    @Override
    int getBracketNumber()
    {
        return -1;
    }

    @Override
    public char getKey()
    {
        return ')';
    }

    @Override
    public void display()
    {
        // todo
    }

    @Override
    public String toString() {
        return ")";
    }
}

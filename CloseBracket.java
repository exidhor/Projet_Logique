
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

    public char getKey()
    {
        return ')';
    }

    public void display()
    {
        // todo
    }
}

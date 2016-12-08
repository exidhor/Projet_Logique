public class Literal implements IElement
{

    private char value;

    public Literal(char c)
    {
        value = c;
    }

    @Override
    public EElement getElementType()
    {
        return EElement.Literal;
    }

    @Override
    public char getKey() {
        return value;
    }

    @Override
    public void display() {
        // todo
    }

    @Override
    public String toString() {
        return Character.toString(value);
    }
}

public class Not implements IElement
{
    @Override
    public EElement getElementType() {
        return EElement.Not;
    }

    @Override
    public char getKey() {
        return '!';
    }

    @Override
    public void display() {
        // todo
    }
}

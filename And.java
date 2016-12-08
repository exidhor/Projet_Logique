public class And extends Operator
{
	public And()
    {
        super(EOperator.And);
    }

    @Override
    public char getKey() {
        return '&';
    }

    @Override
    public void display() {
        // todo
    }

    @Override
    public String toString() {
        return "&";
    }
}

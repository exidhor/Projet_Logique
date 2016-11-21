public abstract class Operator implements IElement
{
	private EOperator type;
	
	public Operator(EOperator type)
	{
		this.type = type;
	}
	
	public EElement getElementType()
	{
		return EElement.Operator;
	}

	public EOperator getOperatorType()
    {
        return type;
    }
}
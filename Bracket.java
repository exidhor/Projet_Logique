public abstract class Bracket implements IElement
{
	private EBracket type;
	
	public Bracket(EBracket type)
	{
		this.type = type;
	}

    public EElement getElementType()
    {
        return EElement.Bracket;
    }

	public EBracket getBracketType()
    {
        return type;
    }
}

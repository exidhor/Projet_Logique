
public class SubDivision
{
	private Formula first;
	private EResolution resolutionType;
	private Formula second;
	
	public SubDivision(Formula first, Operator operator, Formula second)
	{
		this.first = first;
		this.second = second;

		computeResolution();
	}

	public void computeResolution()
	{
		// todo : calcule le type de resolution en fonction de l'operator
	}
	
	public Formula getFirstFormula()
	{
		return first;
	}
	
	public EResolution getResolutionType()
	{
		return resolutionType;
	}
	
	public Formula getSecondFormula()
	{
		return second;
	}
}

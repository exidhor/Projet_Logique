
public class SubDivision
{
	private Formula first;
	private EResolution resolutionType;
	private Formula second;
	
	public SubDivision(Formula first, EResolution resolutionType, Formula second)
	{
		this.first = first;
		this.resolutionType = resolutionType;
		this.second = second;
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

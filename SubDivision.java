
public class SubDivision
{
	private Formula first;
	private EResolution resolutionType;
	private Formula second;
	
	public SubDivision(Formula first, Operator operator, Formula second)
	{
		this.first = first;
		this.second = second;

		computeResolution(operator);
	}

	public void computeResolution(Operator operator)
	{
        switch (operator.getOperatorType())
        {
            case And :
                resolutionType = EResolution.Conjunction;
                break;

            case Or :
                resolutionType = EResolution.Disjunction;
                break;

            case Involve :
                resolutionType = EResolution.Disjunction;

                // ajout de la negation sur la premiere formule
                // et de bracket (au cas ou la formule fait plus d'un litteral)
                first.insert(0, new OpenBracket());
                first.insert(0, new Not());
                first.push_back(new CloseBracket());
                break;
        }
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

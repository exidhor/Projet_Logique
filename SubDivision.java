import java.util.ArrayList;

public class SubDivision
{
    private EOperator type;
    private ArrayList<IElement> subElements;
    private ArrayList<Integer> hingeOperatorIndices;

    public SubDivision(ArrayList<IElement> elements,
                       ArrayList<Integer> hingeOperatorIndices,
                       EOperator operatorType)
    {
        this.type = operatorType;
        this.subElements = elements;
        this.hingeOperatorIndices = hingeOperatorIndices;
    }

    public EOperator getType()
    {
        return type;
    }

    public ArrayList<IElement> getSubElements()
    {
        return subElements;
    }

    public ArrayList<Integer> getHingeOperatorIndices()
    {
        return hingeOperatorIndices;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Resolution
{
	private ArrayList<Formula> formulas;
	private EResolution resolutionType;

    public Resolution(Formula onlyOne, EResolution resolutionType)
    {
        formulas = new ArrayList<Formula>();

        formulas.add(onlyOne);

        if(resolutionType == EResolution.Impossible)
        {
            this.resolutionType = EResolution.Impossible;
        }
        else if(resolutionType == EResolution.Negation)
        {
            this.resolutionType = EResolution.Negation;

            //resolveNegation();
        }
        else
        {
            System.out.println("Mauvais constructeur ou parametre !");
        }
    }

    public Resolution(SubDivision subDivision)
    {
        formulas = new ArrayList<Formula>();

        split(subDivision.getSubElements(), subDivision.getHingeOperatorIndices());

        computeResolution(subDivision.getType());
    }

	private void computeResolution(EOperator operatorType)
	{
        switch (operatorType)
        {
            case And :
                resolutionType = EResolution.Conjunction;
                break;

            case Or :
                resolutionType = EResolution.Disjunction;
                break;

            case Involve :
                resolutionType = EResolution.Disjunction;

                // on ne gere pas le cas ou il y a plusieurs "->" a la chaine
                if(formulas.size() > 2)
                {
                    // on fusionne alors les autres formules
                    ArrayList<IElement> newFormulaElements = new ArrayList<>();

                    for(int i = 1; i < formulas.size(); i++)
                    {
                        newFormulaElements.add(new Involve());
                        newFormulaElements.addAll(formulas.get(i).getElements());
                    }

                    // puis on retire tous ces formules
                    while(formulas.size() > 1)
                    {
                        formulas.remove(formulas.size() - 1);
                    }

                    // et enfin on ajoute la nouvelle formule crée (qui fusionne les formules qu'on a retiré)
                    formulas.add(new Formula(newFormulaElements));
                }

                // ajout de la negation sur la premiere formule
                // et de bracket (au cas ou la formule fait plus d'un litteral)
                formulas.get(0).insert(0, new OpenBracket());
                formulas.get(0).insert(0, new Not());
                formulas.get(0).push_back(new CloseBracket());
                break;
        }
	}
	
	public ArrayList<Formula> getFormulas()
    {
        return formulas;
    }

    public EResolution getResolutionType()
    {
        return resolutionType;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Type de resolution : " + resolutionType + '\n');
        string.append("nombre de formule : " + formulas.size() + '\n');

        for(int i = 0; i < formulas.size(); i++)
        {
            string.append(formulas.get(i) + "\n");
        }

        return string.toString();
    }

    private void resolveNegation()
    {
        ArrayList<IElement> elements = formulas.get(0).getElements();

        if(elements.get(0).getElementType() != EElement.Not)
        {
            System.out.println("Erreur, operation negation sur la mauvaise formule(" + formulas.get(0) + ")");
            return;
        }

        elements.remove(0); // on retire la negation vu qu'on va la resoudre

        formulas.get(0).removeExterneBracket();

        SubDivision subDivision = formulas.get(0).subDivid();
        ArrayList<IElement> subElements = new ArrayList<>(subDivision.getSubElements());

        switch(subDivision.getType())
        {
            case Involve:
                ArrayList<IElement> first =  new ArrayList<>(subElements.subList(0,
                                                             subDivision.getHingeOperatorIndices().get(0)));

                // todo : gerer les differents cas de la negation


                if(first.size() > 1)
                {

                }
                break;

            case And:
                break;

            case Or :
                break;
        }

        for(int i = 0; i < subDivision.getSubElements().size(); i++)
        {
            if(subElements.size() > 1)
            {
                subElements.add(0, new OpenBracket());
                subElements.add(subElements.size(), new CloseBracket());
            }

            subElements.add(0, new Not());
        }
    }

    private void split(ArrayList<IElement> elements, ArrayList<Integer> hingeOperatorIndices)
    {
        int currentListIndex = 0;
        for(int i = 0; i < hingeOperatorIndices.size(); i++)
        {
            int operatorIndex =  hingeOperatorIndices.get(i).intValue();
            List<IElement> currentList = elements.subList(currentListIndex,operatorIndex);
            currentListIndex = operatorIndex + 1;

            formulas.add(new Formula(currentList));
        }

        formulas.add(new Formula(elements.subList(currentListIndex,elements.size())));
    }
}

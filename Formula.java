import java.util.ArrayList;
import java.util.List;

public class Formula
{
	private ArrayList<IElement> elements;

    public Formula(String stringFormula)
    {
        elements = new ArrayList<IElement>();

            for(int i = 0; i < stringFormula.length(); i++)
            {
                char c = stringFormula.charAt(i);
                switch(c)
                {
                    // space
                    case ' ' :
                        // do nothing
                        break;

                    // operator
                    case '&' :
                        elements.add(new And());
                        break;

                    case '|' :
                        elements.add(new Or());
                        break;

                    case '>' :
                        elements.add(new Involve());
                        break;

                    // not
                    case '!' :
                        elements.add(new Not());
                        break;

                    // brackets
                    case '(' :
                        elements.add(new OpenBracket());
                        break;

                    case ')' :
                        elements.add(new CloseBracket());
                        break;

                    // litteral
                    default :
                        elements.add(new Literal(c));
                        break;
                }
        }

        if(!formulaIsCorrect())
        {
            elements = null; // error
        }
        else
        {
            removeExterneBracket();
        }
    }
	
	public Formula(List<IElement> elements)
	{
        this.elements = new ArrayList<>(elements);

        if(!formulaIsCorrect())
        {
            elements = null; // error
        }
        else
        {
            removeExterneBracket();
        }
	}

	public Resolution resolve()
    {
        // Algo :
        // 1 - Verifier la negation
        // 2 - Recuperer les operateurs charnieres
        // 3 - Resolution

        // 1 - Verifier la negation
        if(elements.size() > 2
                && elements.get(0).getElementType() == EElement.Not
                && elements.get(1).getElementType() == EElement.Bracket)
        {
            return new Resolution(new Formula(elements), EResolution.Negation);
        }

        // 2 - Recuperer les operateurs charnieres, pour cela :
        SubDivision subDivision = subDivid();

        if(subDivision == null)
        {
            return new Resolution(new Formula(elements), EResolution.Impossible);
        }

        return new Resolution(subDivision);
    }

    public SubDivision subDivid()
    {
        // 2 - Recuperer les operateurs charnieres, pour cela :
        // compter les parentheses pour trouver les operateurs charnieres
        // les operateurs charnieres sont les operateurs qui sépareront les formules
        // Pour le trouver, on cherche l'operateur avec le plus petit bracket Number (appelé "bestBracketNumber")

        int bracketNumber = 0; // augmente de 1 si la bracket est ouverte, -1 si fermée
        EOperator operatorType = null;
        ArrayList<Integer> hingeOperatorIndices = new ArrayList<Integer>(); // indices des operateurs charnieres

        for(int i = 0; i < this.elements.size(); i++)
        {
            IElement element = this.elements.get(i);

            if(element.getElementType() == EElement.Operator
                    && bracketNumber == 0)
            {
                Operator operator = (Operator) element;

                if(operatorType == null)
                {
                    operatorType = operator.getOperatorType();
                    hingeOperatorIndices.add(i);
                }
                else if(operatorType == operator.getOperatorType())
                {
                    hingeOperatorIndices.add(i);
                }
            }

            if(element.getElementType() == EElement.Bracket)
            {
                // on peut faire ce cast car on a tester l'element juste au dessus
                Bracket bracket = (Bracket) element;

                bracketNumber += bracket.getBracketNumber();
            }
        }

        if(hingeOperatorIndices.size() == 0)
        {
            //System.out.println("Erreur, aucun operateur charniere trouvé !");
            return null;
        }

        return new SubDivision(elements, hingeOperatorIndices, operatorType);
    }

    /*
	public Resolution subDivid()
	{
        // si jamais la taille est de 1, la subdivision est impossible
        if(elements.size() == 1)
        {
            return new Resolution(new Formula(elements));
        }

		// compter les parentheses pour trouver l'operateur charniere
        // l'operateur charniere est l'operateur qui departagera la formule en 2 formules
        // Pour le trouver, on cherche l'operateur avec le plus petit bracket Number (appelé "bestBracketNumber")

		int bracketNumber = 0; // augmente de 1 si la bracket est ouverte, -1 si fermée
        int bestBracketNumber = Integer.MAX_VALUE;
        int bestBracketIndice = -1;

		for(int i = 0; i < this.elements.size(); i++)
		{
            IElement element = this.elements.get(i);

            if(element.getElementType() == EElement.Operator
                    && bracketNumber < bestBracketNumber)
            {
                // cet element devient alors le meilleur operateur charniere potentiel
                bestBracketNumber = bracketNumber;
                bestBracketIndice = i;
            }

			if(element.getElementType() == EElement.Bracket)
			{
				// on peut faire ce cast car on a tester l'element juste au dessus
				Bracket bracket = (Bracket) element;

				bracketNumber += bracket.getBracketNumber();
			}
		}

		if(bestBracketIndice == -1)
        {
            System.out.println("Erreur lors de la Subdivision. Operator charniere introuvable !");

            return null; // error
        }

        // on peut faire ce cast car on a tester l'element juste au dessus
        Operator operator = (Operator) elements.get(bestBracketIndice);

        // Dans la premiere formule, on ajoute les elements dans l'intervalle [0, bestBracketIndice[
        ArrayList<IElement> first = new ArrayList<IElement>(this.elements.subList(0, bestBracketIndice));
        // Dans la seconde, on ajoute les elements dans l'intervalle [bestBracketIndice+1, fin[
        ArrayList<IElement> second = new ArrayList<IElement>(this.elements.subList(bestBracketIndice+1, this.elements.size()));

        return new Resolution(new Formula(first), operator, new Formula(second));
	}*/

	/**
	 * @brief Insert l'element a l'index specifié et deplace ce qui etait a cet index
	 * 		  vers la droite
	 * @param index
	 * @param element
	 */
	void insert(int index, IElement element)
	{
		elements.add(index, element);
	}

    /**
     * @brief Ajoute un element a la fin
     * @param element
     */
	void push_back(IElement element)
    {
        elements.add(element);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size(");
        string.append(elements.size());
        string.append(") : ");
        for(int i = 0; i < elements.size(); i++)
        {
            string.append(elements.get(i).toString());
            string.append(" ");
        }

        return string.toString();
    }

    public boolean formulaIsCorrect()
    {
        // Ce nombre augmente a chaque bracket ouverte et
        // diminue a chaque bracket fermée. Il permet de verifier
        // que la formule est bien parenthésée
        int bracketNumber = 0;

        // Ce nombre augmente a chaque litteral et diminue a chaque operateur
        // Il permet de verifié qu'il n'y a pas 2 operateurs a la suite ou
        // 2 litterals a la suite
        int litteralNumber = 0;

        // ces 2 variables sont utilisées pour vérifier qu'il n'y a pas 2 parentheses
        // de types differents qui se suivent
        EBracket lastBracketType = null;
        boolean lastElementWasBracket = false;

        for(int i = 0; i < elements.size(); i++)
        {
            IElement element = elements.get(i);

            if(element.getElementType() == EElement.Bracket)
            {
                Bracket bracket = (Bracket) element;

                // verifications
                if(lastElementWasBracket && lastBracketType != bracket.getBracketType())
                {
                    System.out.println("Formule pas bien parenthésée ! "
                        + "Deux types de parenthèse différents se suivent !");
                    return false;
                }

                // actualisation des variables de verification
                lastElementWasBracket = true;
                lastBracketType = bracket.getBracketType();

                bracketNumber += bracket.getBracketNumber();
            }
            else
            {
                lastElementWasBracket = false;
            }

            if(element.getElementType() == EElement.Literal)
            {
                litteralNumber++;
            }

            if(element.getElementType() == EElement.Operator)
            {
                litteralNumber--;
            }

            // verifications
            if(bracketNumber < 0)
            {
                System.out.println("Formule pas bien parenthésée !");
                return false;
            }

            if(litteralNumber < 0)
            {
                System.out.println("Formule pas bien ecrite (pas d'équilibre entre"
                        + " le nombre de litteral et le nombre d'operateur !");
                return false;
            }
        }

        if(bracketNumber != 0)
        {
            System.out.println("Formule pas bien parenthésée !");
        }

        if(litteralNumber != 1)
        {
            System.out.println("Formule pas bien ecrite (pas d'équilibre entre"
                + " le nombre de litteral et le nombre d'operateur !");
        }

        return bracketNumber == 0
                && litteralNumber == 1;
    }

    /**
     * @brief supprime les brackets du début s'il y en a
     */
    public void removeExterneBracket()
    {
        while (elements.size() > 3
                && elements.get(0).getElementType() == EElement.Bracket
                && elements.get(elements.size() - 1).getElementType() == EElement.Bracket)
        {
            int bracketNumber = 0; // augmente de 1 si la bracket est ouverte, -1 si fermée

            // verifier que le bracketnumber ne "revient" jamais a 0,
            // ce qui signifie que le bracket du debut "va" avec celui de la fin

            for(int i = 0; i < this.elements.size(); i++)
            {
                IElement element = this.elements.get(i);

                if(element.getElementType() == EElement.Bracket)
                {
                    Bracket bracket = (Bracket) element;

                    bracketNumber += bracket.getBracketNumber();
                }


                if(bracketNumber == 0 && i != elements.size() - 1)
                {
                    // cela signifie que le bracket du debut "ne va pas" avec celui de la fin
                    return;
                }
            }

            elements.remove(0);
            elements.remove(elements.size() - 1);
        }
    }

    public ArrayList<IElement> getElements()
    {
        return elements;
    }

    public void setElements(ArrayList<IElement> elements)
    {
        this.elements = elements;
    }
}

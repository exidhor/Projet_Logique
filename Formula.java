import java.util.ArrayList;

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
    }
	
	public Formula(ArrayList<IElement> elements)
	{
		this.elements = elements;
	}

	public SubDivision subDivid()
	{
		// compter les parentheses pour trouver l'operateur charniere

		int bracketNumber = 0; // augmente de 1 si la bracket est ouverte, -1 si fermée

		// Algo : on cherche le premier operator alors que le bracketnumber est a 0
		for(int i = 0; i < this.elements.size(); i++)
		{
            IElement element = this.elements.get(i);

            if(element.getElementType() == EElement.Operator
                    && bracketNumber == 0)
            {
                // on peut faire ce cast car on a tester l'element juste au dessus
                Operator operator = (Operator) element;

                // Dans la premiere formule, on ajoute les elements dans l'intervalle [0, i[
                ArrayList<IElement> first = new ArrayList<IElement>(this.elements.subList(0, i));
                // Dans la seconde, on ajoute les elements dans l'intervalle [i+1, fin[
                ArrayList<IElement> second = new ArrayList<IElement>(this.elements.subList(i+1, this.elements.size()));

                return new SubDivision(new Formula(first), operator, new Formula(second));
            }

			if(element.getElementType() == EElement.Bracket)
			{
				// on peut faire ce cast car on a tester l'element juste au dessus
				Bracket bracket = (Bracket) element;

				bracketNumber += bracket.getBracketNumber();
			}
		}

		// message d'erreur
        System.out.println("Erreur lors de la Subdivision. Operator charniere introuvable !");

		return null; // error
	}

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
}

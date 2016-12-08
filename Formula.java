import java.util.ArrayList;

public class Formula
{
	private ArrayList<IElement> elements;
	
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
}

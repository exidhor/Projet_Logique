interface IElement
{
    /**
     * Utilisé pour savoir rapidement de quel type est l'element
     * @return le type de l'element (Bracket, Literal, Not ou Operator)
     */
	EElement getElementType();

    /**
     * Utilisé pour recupérer la Key. Cette Key donne plus de détails que
     * le type d'element, elle renseigne par exemple la valeur d'un littéral
     * ou encore l'opérateur. Elle est utilisé pour la résolution des
     * contradictions ou encore le check des opérateurs
     * @return la key qui represente l'ecriture de l'element
     */
	char getKey();

    /**
     * Dessine à l'ecran l'element. (todo : a la fin)
     */
	void display();
}

/*
* Les fichiers commencant par I sont des interfaces (IElement par exemple).
* Les fichiers commencant par E sont des enumerations (EBracket par exemple).
*
* IElement est implémenté par :
*   - Bracket (parent de : OpenBracket, CloseBracket)
*   - Literal
*   - Operator (parent de : And, Or, Involve)
*   - Not
*
* Chaque classe qui implémente IElement doit implementer ces 3 méthodes :
*   - getElementType()
 *  - getKey()
 *  - display()
 *
 *  (voir l'interface IElement pour plus de details sur ces methodes)
* */



public class Main
{
    public static void main(String[] args)
    {
        String testString = "( a & b) > c";
        System.out.println("Formule sous chaine de caracteres : " + testString);

        Formula formula = new Formula(testString);

        System.out.println("Formule transformée en utilisant IElements : " + formula);

        System.out.println("-------------- Subdivision ----------------");
        SubDivision subDivision = formula.subDivid();

        System.out.println("Premiere formule : " + subDivision.getFirstFormula());
        System.out.println("Type de resolution : " + subDivision.getResolutionType());
        System.out.println("Seconde formule : " + subDivision.getSecondFormula());
    }
}
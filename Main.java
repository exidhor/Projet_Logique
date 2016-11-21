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
    public static void main(String args)
    {
        // todo
    }
}
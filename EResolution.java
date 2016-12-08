public enum EResolution
{
    Disjunction, // separation en 2 branches
    Conjunction, // separation en 2 groupes sur la meme branche
    Negation,    // resolution d'un "Not"
    Impossible   // Seulement une formule
}
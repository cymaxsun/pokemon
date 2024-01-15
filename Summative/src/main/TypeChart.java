package main;
public class TypeChart {
    // Types
    private static final int NORMAL = 0, FIRE = 1, WATER = 2, ELECTRIC = 3, GRASS = 4,
            ICE = 5, FIGHTING = 6, POISON = 7, GROUND = 8, FLYING = 9, PSYCHIC = 10,
            BUG = 11, ROCK = 12, GHOST = 13, DRAGON = 14, DARK = 15, STEEL = 16, FAIRY = 17;

    // Effectiveness chart
    private static final double[][] effectiveness = {
        //                 Normal  Fire   Water  Electric  Grass  Ice  Fighting  Poison  Ground  Flying  Psychic  Bug  Rock  Ghost  Dragon  Dark  Steel  Fairy
        /* Normal */    { 1.0,   1.0,   1.0,   1.0,     1.0,  1.0,  2.0,      1.0,    1.0,    1.0,    1.0,    1.0, 0.5, 0.0,   1.0,   1.0,  0.5,   1.0 },
        /* Fire */      { 1.0,   0.5,   0.5,   1.0,     2.0,  2.0,  1.0,      1.0,    1.0,    1.0,    1.0,    2.0, 0.5, 1.0,   0.5,   1.0,  2.0,   1.0 },
        /* Water */     { 1.0,   2.0,   0.5,   1.0,     0.5,  1.0,  1.0,      1.0,    2.0,    1.0,    1.0,    1.0, 2.0, 1.0,   0.5,   1.0,  1.0,   1.0 },
        /* Electric */  { 1.0,   1.0,   2.0,   0.5,     0.5,  1.0,  1.0,      1.0,    0.0,    2.0,    1.0,    1.0, 1.0, 1.0,   0.5,   1.0,  1.0,   1.0 },
        /* Grass */     { 1.0,   0.5,   2.0,   1.0,     0.5,  1.0,  1.0,      0.5,    2.0,    0.5,    1.0,    0.5, 2.0, 1.0,   0.5,   1.0,  0.5,   1.0 },
        /* Ice */       { 1.0,   0.5,   0.5,   1.0,     2.0,  0.5,  1.0,      1.0,    2.0,    2.0,    1.0,    1.0, 1.0, 1.0,   2.0,   1.0,  0.5,   1.0 },
        /* Fighting */  { 2.0,   1.0,   1.0,   1.0,     1.0,  2.0,  1.0,      0.5,    1.0,    0.5,    0.5,    0.5, 2.0, 0.0,   1.0,   2.0,  2.0,   0.5 },
        /* Poison */    { 1.0,   1.0,   1.0,   1.0,     2.0,  1.0,  1.0,      0.5,    0.5,    1.0,    1.0,    0.5, 1.0, 0.5,   1.0,   1.0,  0.0,   2.0 },
        /* Ground */    { 1.0,   2.0,   1.0,   2.0,     0.5,  1.0,  1.0,      2.0,    1.0,    0.0,    1.0,    0.5, 2.0, 1.0,   1.0,   1.0,  2.0,   1.0 },
        /* Flying */    { 1.0,   1.0,   1.0,   0.5,     2.0,  1.0,  2.0,      1.0,    1.0,    1.0,    1.0,    2.0, 0.5, 1.0,   1.0,   1.0,  0.5,   1.0 },
        /* Psychic */   { 1.0,   1.0,   1.0,   1.0,     1.0,  1.0,  0.5,      2.0,    1.0,    1.0,    0.0,    2.0, 1.0, 1.0,   1.0,   0.0,  0.5,   1.0 },
        /* Bug */       { 1.0,   0.5,   1.0,   1.0,     2.0,  1.0,  0.5,      0.5,    1.0,    0.5,    2.0,    1.0, 1.0, 0.5,    1.0,   2.0,  0.5,   0.5 },
        /* Rock */      { 1.0,   2.0,   1.0,   1.0,     1.0,  2.0,  0.5,      1.0,    0.5,    2.0,    1.0,    2.0, 1.0, 1.0,   1.0,   1.0,  0.5,   1.0 },
        /* Ghost */     { 0.0,   1.0,   1.0,   1.0,     1.0,  1.0,  0.0,      0.5,    1.0,    1.0,    1.0,    1.0, 1.0, 2.0,   1.0,   1.0,  0.5,   1.0 },
        /* Dragon */    { 1.0,   1.0,   1.0,   1.0,     1.0,  1.0,  1.0,      1.0,    1.0,    1.0,    1.0,    1.0, 1.0, 1.0,   2.0,   1.0,  0.5,   0.0 },
        /* Dark */      { 1.0,   1.0,   1.0,   1.0,     1.0,  1.0,  0.5,      1.0,    1.0,    1.0,    2.0,    1.0, 1.0, 2.0,   1.0,   0.5,  0.5,   1.0 },
        /* Steel */     { 1.0,   0.5,   0.5,   0.5,     1.0,  2.0,  2.0,      0.0,    2.0,    1.0,    1.0,    0.5, 2.0, 1.0,   0.5,   1.0,  0.5,   2.0 },
        /* Fairy */     { 1.0,   0.5,   1.0,   1.0,     1.0,  1.0,  2.0,      0.5,    1.0,    1.0,    1.0,    1.0, 1.0, 1.0,   2.0,   2.0,  0.5,   1.0 },
    };

    public static void main(String[] args) {
        // Example usage
        double effectiveness1 = getTypeEffectiveness(FIRE, WATER);
        System.out.println("Fire against Water: " + effectiveness1);

        double effectiveness2 = getTypeEffectiveness(GROUND, GRASS);
        System.out.println("Ground against GRASS: " + effectiveness2);
    }

    // Method to get the effectiveness of a move against a type
    private static double getTypeEffectiveness(int attackingType, int defendingType) {
        return effectiveness[attackingType][defendingType];
    }
}

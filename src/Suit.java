/* Suit is either spades, clubs, hearts, or diamonds, each with a corresponding letter
 * @author Harrison Chen
 * @version 9/4/22
 */
public enum Suit
{
    SPADES("S"), CLUBS("C"), HEARTS("H"), DIAMONDS("D");
    private final String SUITCHARACTER;

    // Constructs a suit with a string letter of the suit
    private Suit(String suit)
    {
        SUITCHARACTER = suit;
    }

    /* Returns the Suit corresponding to the passed String suit character
     * @param suitChar the character of the suit returned
     * @return Royal with tag rankTag
     */
    public static Suit getSuit(String suitChar) {
        for (Suit s : Suit.values()) {
            if (s.SUITCHARACTER == suitChar) return s;
        }
        throw new IllegalArgumentException("No suit of that character");
    }
    /* Returns the letter of the suit
     * @return suitCharacter
     */ 
    public String toString() {return SUITCHARACTER;}
}



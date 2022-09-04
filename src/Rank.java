/* Rank from ace-king, each with their own letter values and number tags
 * Royal cards have special power values
 * @author Harrison Chen
 * @version 9/4/22
 */
public enum Rank {
    
    ACE("A", 1), TWO("2", 2), THREE("3", 3), 
        FOUR("4", 4), FIVE("5", 5), SIX("6", 6), 
        SEVEN("7", 7), EIGHT("8", 8), NINE("9", 9), 
        TEN("10", 10), JACK("J", 11), QUEEN("Q", 12),
        KING("K", 13), JOKER("JOK", 0), NORANK("NO",-1);
    private final String RANKCHARACTER;
    private final int TAG;

    /* Constructs a Rank with a letter value and integer TAG
     * @param rank the letter value of this Rank
     * @param tag the TAG of this Rank
     */
    private Rank(String rank, int tag)
    {
        RANKCHARACTER = rank;
        this.TAG = tag;
    }

    // Accessor for TAG
    public int getTag() {return TAG;}

    /* Returns the Rank corresponding to the passed integer TAG
     * @param rankTag the TAG of the Rank returned
     * @return Rank with TAG rankTag
     */
    public static Rank getRank(int rankTag) {
        for (Rank r : Rank.values()) {
            if (r.TAG == rankTag) return r;
        }
        throw new IllegalArgumentException("No rank of that number");
    }

    /* Returns the letter of the Rank
     * @return rankCharacter
     */ 
    public String toString() {return RANKCHARACTER;}
    /* Returns the power of the Rank
     * @return the special power of the royal Ranks, otherwise returns TAG
     */ 
    public int getPower()
    {
        switch(TAG)
        {
            case(11): return 10;
            case(12): return 15;
            case(13): return 20;
            case(-1): return 0;
        }
        return TAG;
    }
}

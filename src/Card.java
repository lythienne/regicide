/* Card class has a rank, suit, and power value
 * @author Harrison Chen
 * @version 9/4/22
 */
public class Card {
    
    private final Rank RANK;
    private final Suit SUIT;
    private final int VALUE;

    /* Constructs a card with a rank, suit, and power value
     * @param rank
     * @param suit
     */
    public Card(Rank rank, Suit suit)
    {
        this.RANK = rank;
        this.SUIT = suit;
        VALUE = rank.getPower();
    }
    /* Constructs a card with a rank and power value but no suit
     * used to construct jokers
     * @param rank
     */
    public Card(Rank rank)
    {
        this.RANK = rank;
        this.SUIT = null;
        VALUE = rank.getPower();
    }

    // Accessor for suit
    public Suit getSuit() {return SUIT;}
    // Accessor for rank
    public Rank getRank() {return RANK;}
    // Accessor for value
    public int getValue() {return VALUE;}
    
    /* Returns a string representation of this card with the
     * rank first then the suit 
     * @return short string representing this card
     */
    public String toString() 
    {
        if (RANK == Rank.JOKER)
            return RANK.toString();
        else
            return RANK.toString()+SUIT.toString();
    }
    /* Compares two cards to see if they are equal
     * @return true if the cards have the same suit and rank, false otherwise
     */
    public boolean equals(Card card)
    {
        if (card.RANK == RANK && card.SUIT == SUIT)
            return true;
        else
            return false;
    }
}

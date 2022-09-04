/* Royal class represents the card player is currently fighting
 * it has a power, suit, and toughness
 * @author Harrison Chen
 * @version 9/4/22
 */
public class Royal {
    
    private final int STARTPOWER;
    private final Suit SUIT;
    private final Rank RANK;
    private int toughness;
    private int power;
    
    /* Constructs a Royal with suit, power, and toughness based
     * on a Card
     * @param c the card the Royal represents
     */
    public Royal(Card c)
    {
        SUIT = c.getSuit();
        RANK = c.getRank();
        STARTPOWER = c.getValue();
        power = STARTPOWER;
        toughness = power*2;
    }

    // Accessor for suit
    public Suit getSuit() {return SUIT;}
    // Accessor for toughness
    public int getToughness() {return toughness;}
    // Accessor for power
    public int getPower() {return power;}

    /* Returns the name of this Royal
     * @return NAME of SUIT
     */
    public String toString() {return RANK.name()+" OF "+SUIT.name();}
    /* Returns the card this Royal represents
     * @return Card with the 
     */
    public Card toCard() {return new Card(RANK, SUIT);}
    /* Takes damage
     * @param damage the amount of damage
     */
    public void takeDamage(int damage) {toughness -= damage;}
    /* Decreases power
     * @param amount the amount of power decrease
     */
    public void powerDecrease(int amount) 
    {
        power -= amount;
        if (power<0)
            power=0;
    }
}

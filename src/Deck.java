import java.util.Collections;
import java.util.ArrayList;

/* Deck class stores, adds, removes, and shuffles cards
 * @author Harrison Chen
 * @version 9/4/22
 */
public class Deck {
    
    private ArrayList<Card> cards = new ArrayList<Card>();

    //Default Constructor
    public Deck() {}
    /* Constructs a Deck of a card of every suit of a certain rank
     * @param value the rank of the cards
     */
    public Deck(int value)
    {
        for (Suit s : Suit.values())
        {
            Card c = new Card(Rank.getRank(value), s);
            cards.add(c);
        }
        this.shuffle();
    }
    /* Constructs a Deck with a card of every suit from a minimum
     * rank to a maximum
     * @param start the minimum rank
     * @param end the maximum rank
     */
    public Deck(int start, int end)
    {
        if (start == 0)
        {
            cards.add(new Card(Rank.JOKER));
            cards.add(new Card(Rank.JOKER));
            start++;
        }
        for (Suit s : Suit.values())
        {
            for (int i=start;i<=end;i++)
            {
                Card c = new Card(Rank.getRank(i), s);
                cards.add(c);
            }
        }
        this.shuffle();
    }

    /* Returns the ArrayList of cards
     * @return ArrayList<Card> cards
     */
    public ArrayList<Card> getDeckList() {return cards;}
    /* Returns the size of the cards list
     * @return cards.size()
     */
    public int getSize() {return cards.size();}

    // Randomizes the order of the Cards in cards
    public void shuffle() {Collections.shuffle(cards);}
    /* Returns a String of each Card in this Deck in order
     * @return String of cards in this Deck
     */
    public String toString() 
    {
        String deckString = "";
        for (Card c : cards)
            deckString=deckString + c.toString()+" "; 
        deckString.trim();
        return deckString;
    }
    /* Checks if this Deck has a specific Card
     * @param card the Card this Deck might have
     * @return the index of the Card, -1 if this Deck does 
     * not contain the Card
     */
    public int has(Card card)
    {
        for (int i = 0;i<cards.size();i++)
        {
            if (cards.get(i).equals(card))
                return i;
        }
        return -1;
    }
    /* Checks if this Deck has a Card of a specific rank
     * @param rank the Rank of Card this Deck might have
     * @return true if the Deck contains a Card with Rank rank,
     * false otherwise
     */
    public boolean has(Rank rank)
    {
        for (Card c : cards)
        {
            if (c.getRank() == rank)
                return true;
        }
        return false;
    }
    /* Retrieves the card with the same name as the input String
     * @param input the String of the Card to be retrieved
     * @return the Card with the same name as input, null otherwise
     */
    public Card get(String input)
    {
        for (Card c : getDeckList())
        {
            if (c.toString().equals(input))
                return c;
        }
        return null;
    }
    /* Removes the top Card of this Deck
     * @return the removed Card
     */
    public Card remove()
    {
        return cards.remove(0);
    }
    /* Removes a specific Card from this Deck
     * @param card the Card to be removed
     * @return the removed Card
     */
    public Card remove(Card card)
    {
        int index = has(card);
        if (index == -1)
            throw new IllegalArgumentException("Card not in deck");
        else
            return cards.remove(index);
    }
    /* Adds a Card to the top or the bottom of this Deck
     * @param top whether to add the Card to the top or bottom
     * @param c the Card to be added
     */
    public void add(boolean top, Card c)
    {
        if (top)
            cards.add(0, c);
        else
            cards.add(c);
    }
    /* Moves x Cards from the top of this Deck to the top of Deck d
     * @param x the number of Cards to be moved
     * @param d the Deck to move cards from
     */
    public void moveTo(int x, Deck d)
    {
        if (x == -1)
        {
            int deckSize = this.getSize();
            for (int i=0;i<deckSize;i++)
                d.add(true, this.remove());
        }
        else
        {
            for (int i=0;i<x;i++)
                d.add(true, this.remove());
        }
    }
    /* Moves a specific Card from this Deck to a Deck d
     * @param cardPlayed the Card to be moved
     * @param d the Deck the Card is moved to
     * @return the Card moved
     */ 
    public Card moveTo(Card cardPlayed, Deck d)
    {
        for (Card c : getDeckList())
        {
            if (c.equals(cardPlayed))
            {
                d.add(true, remove(c));
                return c;
            }
        }
        return null;
    }
}

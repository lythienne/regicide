import java.util.ArrayList;

/* Game class runs a game of regicide
 * @author Harrison Chen
 * @version 9/4/22
 */
public class Game
{
    private enum Status{
        ALIVE, DEAD, WON
    }

    private Status gameStatus;

    private Deck library;
    private Deck jacks;
    private Deck queens;
    private Deck kings;

    private Deck discard;
    private Deck limbo;

    private Deck hand;

    private Royal enemy;

    private Dialogue d;

    public Game(){
    
        gameStatus = Status.ALIVE;

        library = new Deck(1,10);
        jacks = new Deck(11);
        queens = new Deck(12);
        kings = new Deck(13);

        discard = new Deck();
        limbo = new Deck();

        hand = new Deck();

        d = new Dialogue(library, discard, limbo, hand);
    }

    // Starts the game, player draws 8, and the first enemy is drawn
    public void playGame() 
    {
        d.setName();
        enemy = killRoyal();
        draw(8);
        do
            {turn();}
        while (gameStatus == Status.ALIVE);
        switch (gameStatus)
        {
            case DEAD: 
                d.deathText();
                break;
            case WON: 
                d.winText();
                break;
        }
    }
    /* A player turn with main text, player plays a card, 
     * then enemy deals damage if it isn't dead
     */ 
    public void turn()
    {
        d.mainText();
        playCard();
        if (enemy.getToughness()<0)
        {
            d.enemyDeathText(false);
            discard.add(true, enemy.toCard());
            enemy = killRoyal();
        }
        else if (enemy.getToughness()==0)
        {
            d.enemyDeathText(true);
            library.add(true, enemy.toCard());
            enemy = killRoyal();
        }
        else
        {
            playerTakeDamage();
        }
    }
    /* Puts x Cards from the top of library to hand to a hand size
     * limit (8)
     * @param x the number of Cards to be drawn
     */
    public void draw(int x)
    {
        if (x>8-hand.getSize())
            library.moveTo(8-hand.getSize(), hand);
        else
            library.moveTo(x, hand);
    }
    // Player uses a joker to discard their hand and draw 8 new cards
    public void useJoker()
    {
        hand.moveTo(-1, discard);
        draw(8);
        d.jokers--;
    }
    /* Player plays a card from their hand to deal damage to the enemy
     * draws cards if it is a diamond, heals cards if it is a heart
     * Player can play combos with ACEs, TWOs, THREEs, FOURs, and FIVES
     * Player can refill hand with JOKERs
     */
    public void playCard()
    {
        ArrayList<Suit> suits = new ArrayList<Suit>();
        Card playedCard = d.playCardText();

        if (playedCard.getRank()==Rank.JOKER)
        {
            useJoker();
            playedCard = d.playCardText();
        }
        hand.moveTo(playedCard, limbo);
        int totalValue = playedCard.getValue();
        suits.add(playedCard.getSuit());

        switch(playedCard.getRank())
        {
            case ACE:
                if (hand.getSize()>0)
                {
                    playedCard = d.playCardText(Rank.ACE);
                    totalValue += playedCard.getValue();
                    suits.add(playedCard.getSuit());
                }
                break;
            case TWO, THREE, FOUR, FIVE:
                totalValue += playCombo(playedCard.getRank(), suits);
                break;
            default:
                break;      
        }
        if (suits.contains(Suit.DIAMONDS) && enemy.getSuit() != Suit.DIAMONDS) 
        {
            draw(totalValue);
            d.cardEffectText(totalValue, Suit.DIAMONDS);
        }  
        if (suits.contains(Suit.HEARTS) && enemy.getSuit() != Suit.HEARTS)
        {
            discard.shuffle();
            int discardSize = discard.getSize();
            if (totalValue>discardSize)
            {
                for (int i=0;i<discardSize;i++)
                    library.add(false, discard.remove());
            }
            else
            {
                for (int i=0;i<totalValue;i++)
                    library.add(false, discard.remove());
            }
            d.cardEffectText(totalValue, Suit.HEARTS);
        }
        if (suits.contains(Suit.SPADES) && enemy.getSuit() != Suit.SPADES)
        {
            enemy.powerDecrease(totalValue);
            d.cardEffectText(totalValue, Suit.SPADES);
        }
        if (suits.contains(Suit.CLUBS) && enemy.getSuit() != Suit.CLUBS) 
        {
            totalValue *= 2;
        }

        if (totalValue>0)
        {
            enemy.takeDamage(totalValue);
            d.cardEffectText(totalValue, null);
        }
        
    }
    /* Allows players to play multiple cards of the same rank as long
     * as their total combined value is less than 10
     * @param rank the Rank of the cards played
     * @param sList the list of Suits that have been played
     * @return the combined value of the Card(s) just played
     */
    public int playCombo(Rank rank, ArrayList<Suit> sList)
    {
        int combValue = 0;

        int count = 7;
        count-=rank.getTag();
        while (hand.has(rank) && count>0)
        {
            Card playedCard = d.playCardText(rank);
            combValue += playedCard.getValue();
            sList.add(playedCard.getSuit());
            count-=rank.getTag();
        }
        return combValue;
    }
    /* Removes and returns the current royal, and replaces it
     * with the next royal in line
     * @return Royal just killed
     */
    public Royal killRoyal()
    {
        Royal newRoyal;

        limbo.moveTo(-1, discard);

        if (jacks.getSize()==0)
        {
            if (queens.getSize()==0)
            {
                if (kings.getSize()==0)
                {
                    gameStatus = Status.WON;
                    return null;
                }
                else
                    newRoyal = new Royal(kings.remove());
            }
            else
            newRoyal = new Royal(queens.remove());
        }
        else
            newRoyal = new Royal(jacks.remove());
        d.setCurrentEnemy(newRoyal);
        return newRoyal;
            
    }
    /* Prompts the player to discard cards from their hand equal to or
     * above the enemy's dealt damage
     * @return String of cards in this deck
     */
    public void playerTakeDamage()
    {
        int damageLeft = enemy.getPower();
        while (damageLeft > 0)
        {
            Card discardedCard = d.playerTakeDamageText(damageLeft);
            if (discardedCard.getRank()==Rank.JOKER)
            {
                useJoker();
                discardedCard = d.playerTakeDamageText(damageLeft);
            }
            damageLeft -= discardedCard.getValue();
            hand.moveTo(discardedCard, discard);
            if (hand.getSize()==0)
            {
                gameStatus = Status.DEAD;
                damageLeft = -1;    
            }
        }
    }
}   


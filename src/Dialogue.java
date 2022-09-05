import java.util.Scanner;
import java.lang.Math;

/* Dialogue class handles the input and output of the game
 * @author Harrison Chen
 * @version 9/4/22
 */
public class Dialogue {
    
    private Scanner scanner = new Scanner(System.in);

    private Deck library;
    private Deck discard;
    private Deck limbo;
    private Deck hand;
    public int jokers;
    private Royal enemy;
    private String name = "you";

    /* Constructs a dialogue object with the library, discard, limbo,
     * player, and enemy of the game
     */ 

    public Dialogue(Deck library, Deck discard, Deck limbo, 
        Deck hand)
    {
        this.library = library;
        this.discard = discard;
        this.limbo = limbo;
        this.hand = hand;
        jokers = 2;
    }

    /* Sets current enemy
     * @param currentEnemy the current enemy
     */
    public void setCurrentEnemy (Royal currentEnemy) {enemy = currentEnemy;}
    // Sets player name
    public void setName()
    {
        System.out.print("\nUsername: ");
        name = scanner.nextLine();
    }
    // Prints the hand
    public void printHand() {System.out.println("Hand: "+hand);}
    /* Main text dialogue, tells player deck+discard size, 
     * current enemy stats, hand, played cards, and # of jokers left
     */
    public void mainText()
    {
        System.out.println("\nRegicide: Deck "+library.getSize()+" | Discard "
            +discard.getSize()+"\nCurrent Enemy: "+enemy
            +" ["+enemy.getToughness()+" hp / "+enemy.getPower()
            +" dmg]\nPlayed cards: "+limbo+"| Jokers left: "+jokers+"\n");    
    }
    /* Prompts player for a Card until they return a Card that is in Deck d
     * @param input the String of a Card to be checked
     * @param d the Deck the Card should be part of
     * @return the Card that is in the Deck
     */
    public Card getValidCard(String input, Rank cardRank)
    {
        if (input.equals("JOKER") && jokers>0 && cardRank == Rank.NORANK) {return new Card(Rank.JOKER);}
        if (input.equals("NO")) {return new Card(Rank.NORANK);}

        Card movedCard = hand.get(input);

        while (movedCard == null)
        {    
            System.out.print("Invalid card, try again: ");
            input = scanner.nextLine();
            System.out.println("");
            movedCard = hand.get(input);
        }
        if (cardRank != Rank.NORANK)
        {
            while ((cardRank == Rank.ACE && movedCard.getRank() == cardRank
                || cardRank != Rank.ACE && movedCard.getRank() != cardRank))
            {    
                System.out.print("Invalid card, try again: ");
                input = scanner.nextLine();
                System.out.println("");
                movedCard = hand.get(input);
            }
        }
        return movedCard;
    }
    /* Prompts player to play a card, returns the card played
     * @return a valid card to be played
     */
    public Card playCardText()
    {
        String jokerText = "";
        if (jokers>0)
            jokerText = ", use a JOKER";
        printHand();
        System.out.print("Play a card (ACEs first)"+jokerText+", or type \"NO\": ");
        String input = scanner.nextLine();
        System.out.println("");
        
        return getValidCard(input, Rank.NORANK);
    }
    public Card playCardText(Rank rank)
    {
        printHand();
        String rankName = rank.name();
        if (rank==Rank.ACE) {rankName = "non-ACE";}
        System.out.print("Play another "+rankName+" card or type \"NO\": ");

        String input = scanner.nextLine();
        System.out.println("");

        return hand.moveTo(getValidCard(input, rank), limbo);
    }
    /* Prompts the player to discard cards from their hand equal to or
     * above the enemy's dealt damage
     * @return String of cards in this deck
     */
    public Card playerTakeDamageText(int damageLeft)
    {
        System.out.print("\n--You have taken "+enemy.getPower()
            +" total damage.\n\nHand: "+hand+"\nDiscard card(s) with total value "+damageLeft+" or more: ");

        String input = scanner.nextLine();
        System.out.println("");

        return getValidCard(input, Rank.NORANK);
    }
    public void cardEffectText(int value, Suit suit)
    {
        if (suit == Suit.DIAMONDS)
            System.out.println("--You have drawn "+value+" cards.");
        else if (suit == Suit.HEARTS)
            System.out.println("--You have healed "+value+" cards.");
        else if (suit == Suit.SPADES)
            System.out.println("--"+enemy+"'s power has been reduced by "+value+".");
        else
            System.out.println("--You have dealt "+value+" damage.");
    }
    // Prints a congratulation message for killing the enemy
    public void enemyDeathText(boolean perfect)
    {
        if (perfect)
        {    
            System.out.println("\n--Congratulations, you have exactly killed "+enemy
                +"! It will go to the top of your deck.");
        }
        else
        {
            System.out.println("\n--Congratulations, you have killed "+enemy
                +"! It will go to your discard pile.");
        }
    }
    // Prints a win message
    public void winText() {
        int random = (int) (10*Math.random())+1;

        String message;

        switch(random)
        {
            case 1: message = "KAZUYA MISHIMA WINS";
                    break;
            case 2: message = "YoU'Ll nEVeR DeFEAt mE!";
                    break;
            default: message = "You win!";
                    break;
        }
        System.out.println(message);
    }
    // Prints a lose message
    public void deathText() 
    {
        int random = (int) (10*Math.random())+1;
        String message;

        System.out.println("\nYou Died\n");

        switch(random)
        {
            case 1: message = "--You little sussy amogus baka impostor, why would you vent right in front of "+enemy;
                    break;                    
            case 2: message = "--"+name+" tried to swim in lava.";
                    break;
            case 3: message = "--"+name+" was prickled to death by a berry bush whilst trying to escape "
                +enemy+" wielding your mom.";
                    break;
            case 4: message = "--Hilda! Why didn't you retreat? I counted on you retreating...";
                    break;
            case 5: message = "--"+name+" was The Impostor.\n0 Impostors remain.";
                    break;
            case 6: message = "--"+enemy+" minecart up smashed "+name+" at 80%.";
                    break;
            case 7: message = "--"+name+" just remembered tmrs AP Chem test.";
                    break;
            case 8: message = "--"+name+" got HUR HA HEH HA-ed by Roy at 30 on the side plat of town and city.";
                    break;
            case 9: message = "--"+name+
                " died of a heart attack at age 70, rumored to be caused by an orgasm during intercourse with their secretary "+enemy;
                    break;
            case 10: message = "--"+name+" has not been hugged in "+enemy.getPower()+" years";
                    break;
            default: message = "--"+enemy+" deals "+enemy.getPower()
            +" damage. Your body explodes like a pinata of blood and guts.";
                    break;
        }
        System.out.println(message);
    }
}

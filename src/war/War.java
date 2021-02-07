package war;

import war.*;

/**
 * The main program for the card game, War.  It is run on the command line as:<br>
 * <br>
 * java War cards-per-player seed<br>
 * <br>
 *
 * @author RIT CS
 * @author YOUR NAME HERE
 */

public class War {
    /** The maximum number of cards a single player can have */
    public final static int MAX_CARDS_PER_PLAYER = 26;
    private final Player p1 = new Player(1);
    private final Player p2 = new Player(2);
    private int round = 1;

    /**
     * Initialize the game.
     *
     * @param cardsPerPlayer the number of cards for a single player
     */
    public War(int cardsPerPlayer) {

        Pile initial = new Pile("Initial");

        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                initial.addCard(new Card(rank, suit));
            }
        }

        initial.shuffle();
        System.out.println(initial.toString());

        for (int i = 0; i < cardsPerPlayer; i++) {
            p1.addCard(initial.drawCard(false));
            p2.addCard(initial.drawCard(false));
        }

    }

    /** Play the full game. */
    public void playGame() {
        while (!p1.isWinner() && !p2.isWinner()) {
            if(!p2.hasCard()) {
                p1.setWinner();
            } else if (!p1.hasCard()) {
                p2.setWinner();
            } else {
                System.out.println("ROUND " + round + "\n" + p1.toString() + "\n" + p2.toString());
                playRound();
            }
        }

        if (p1.isWinner()) {
            System.out.println(p1.toString() + "\n" + p2.toString() + "\nP1 WINS!");
        } else {
            System.out.println(p1.toString() + "\n" + p2.toString() + "\nP2 WINS!");
        }
    }

    /** Play a single round of the game. */
    private void playRound() {

        Pile trick = new Pile("Trick");
        Card p1Card = p1.drawCard();
        Card p2Card = p2.drawCard();
        trick.addCard(p1Card);
        trick.addCard(p2Card);

        System.out.println("P1 card: " + p1Card.toString() + "\n" + "P2 card: " + p2Card.toString());

        while (p1Card.equals(p2Card)) {
            if(!p2.hasCard()) {
                p1.setWinner();
                return;
            } else if (!p1.hasCard()) {
                p2.setWinner();
                return;
            } else {
                System.out.println("WAR!\n" + p1.toString() + "\n" + p2.toString());
                p1Card = p1.drawCard();
                p2Card = p2.drawCard();
                trick.addCard(p1Card);
                trick.addCard(p2Card);
                System.out.println("P1 card: " + p1Card.toString() + "\n" + "P2 card: " + p2Card.toString());
            }
        }

        if (p1Card.beats(p2Card)) {
            System.out.println("P1 wins round gets " + trick.toString());
            p1.addCards(trick);
        } else if (p2Card.beats(p1Card)) {
            System.out.println("P2 wins round gets " + trick.toString());
            p2.addCards(trick);
        }

        round++;
    }

    /**
     * The main method get the command line arguments, then constructs
     * and plays the game.  The Pile's random number generator and seed
     * need should get set here using Pile.setSeed(seed).
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java War cards-per-player seed");
        } else {
            int cardsPerPlayer = Integer.parseInt(args[0]);
            // must be between 1 and 26 cards per player
            if (cardsPerPlayer <= 0 || cardsPerPlayer > MAX_CARDS_PER_PLAYER) {
                System.out.println("cards-per-player must be between 1 and " + MAX_CARDS_PER_PLAYER);
            } else {
                // set the rng seed
                Pile.setSeed(Integer.parseInt(args[1]));
                War war = new War(cardsPerPlayer);
                war.playGame();
            }
        }
    }
}

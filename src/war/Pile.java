package war;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Represents a pile of cards to be used in the game.
 *
 * @author Clinten Hopkins
 */

public class Pile {

    /** The recognizable name of a pile */
    private final String name;

    /** The Random object to create predictability outcomes */
    private static Random rng;

    /** The cards in the pile */
    ArrayList<Card> cards;

    /**
     * Create a pile with the name. The pile is empty to begin with.
     *
     * @param name the pile's name
     */
    public Pile(String name) {
        cards = new ArrayList<>();
        this.name = name;
    }

    /**
     * Sets a seed for the random function, to create predictability.
     *
     * @param seed the seed for the random object
     */
    public static void setSeed(long seed) {
        rng = new Random();
        rng.setSeed(seed);
    }

    /**
     * Shuffles the deck using the seed provided. Sets all of the cards to be face down as well.
     *
     */
    public void shuffle() {
        System.out.println("Shuffling " + name + " pile");
        for (Card card : cards) {
            card.setFaceDown();
        }
        Collections.shuffle(cards, rng);
    }

    /**
     * Get all of the cards to be used as a way to transfer piles to other piles.
     *
     * @return all of the cards
     */
    public ArrayList<Card> getCards(){
        return cards;
    }
    /** Add a card to the pile. */
    public void addCard(Card card) {
            cards.add(card);
    }

    /**
     * Draws a card and shuffles the deck if the card to be drawn is face up (the pile has worked its way
     * through the deck).
     *
     * @param faceUp if the drawn card should be turned to face up
     * @return the card drawn
     */
    public Card drawCard(boolean faceUp) {

        if (cards.get(0).isFaceUp()) {
            shuffle();
            System.out.println(toString());
        }

        Card drawn = cards.get(0);

        if (faceUp) {
            drawn.setFaceUp();
        } else {
            drawn.setFaceDown();
        }
        cards.remove(0);
        return drawn;
    }

    /**
     * Is there one or more cards in the pile?
     *
     * @return true if there is at least one card in the pile
     */
    public boolean hasCard() {
        return cards.size() >= 1;
    }

    /** Clears all the cards in the pile. */
    public void clear() {
        cards.clear();
    }

    /**
     * Converts the pile to a printable string using the pile's name and all the cards in the pile.
     * For example, if the pile's name is Trick and the cards are the Ace of Clubs and the Seven of
     * Spades, it will return "Trick pile: A♧(U) 7♠(U) "
     *
     * @return the string described above
     */
    @Override
    public String toString() {

        String result = name + " pile: ";

        for (Card card : cards) {
            result = result + card.toString() + " ";
        }

        return result;
    }
}

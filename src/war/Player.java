package war;

public class Player {

    /** The pile to hold all of the player's cards. */
    private Pile pile;

    /** The state of if the player has won or not. */
    boolean winner = false;

    /**
     * Creates a new player with an empty pile of cards.
     *
     * @param playerNum the player number, to be used in the name of the pile
     */
    public Player(int playerNum) {
        pile = new Pile("P" + playerNum);
    }

    /**
     * Adds a card to the player's pile.
     *
     * @param card the card to be added
     */
    public void addCard(Card card){
        pile.addCard(card);
    }

    /**
     * Adds a pile of cards to the player's cards, usually used to add the Trick pile.
     *
     * @param pile the pile of cards to be added
     */
    public void addCards(Pile pile) {
        for (Card card : pile.getCards()) {
            addCard(card);
        }
    }

    /**
     * Checks to see if the pile has at least one card in it.
     *
     * @return true if the pile has more than one card
     */
    public boolean hasCard() {
        return pile.hasCard();
    }

    /**
     * Takes the top card off of the pile and flips it up, to be used to compare to the opponent's card.
     *
     * @return the card to be compared
     */
    public Card drawCard() {
        return pile.drawCard(true);
    }

    /**
     * Check if the player is the winner of the game.
     *
     * @return true if the player is the winner
     */
    public boolean isWinner() {
        return winner;
    }

    /** Sets the winner of the game to be this player. */
    public void setWinner() {
        winner = true;
    }

    /**
     * Creates a string to be printed, passing through the pile.toString into this toString.
     *
     * @return the pile.toString output
     */
    @Override
    public String toString() {
        return pile.toString();
    }

}

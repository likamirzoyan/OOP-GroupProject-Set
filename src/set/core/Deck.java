package set.core;

public class Deck {
    // stores 81 cards
    private Card[] cards;
    // counter that keeps track of the next card to be drawn from the deck
    private int currentCard;

    /**
     * Constructs a new full shuffled deck
     */
    public Deck() {
        cards = new Card[81];
        currentCard = 0;

        int index = 0;
        for (Card.Color color : Card.Color.values()) {
            for (Card.Shape shape : Card.Shape.values()) {
                for (Card.Filling filling : Card.Filling.values()) {
                    for (Card.Number number : Card.Number.values()) {
                        cards[index++] = new Card(color, shape, filling, number);
                    }
                }
            }
        }
        shuffle();
    }

    /**
     * Copy constructor
     */
    public Deck(Deck other) {
        this.cards = new Card[81];
        for (int i = 0; i < other.cards.length; i++) {
            if (other.cards[i] != null) {
                this.cards[i] = new Card(other.cards[i]);
            }
        }
        this.currentCard = other.currentCard;
    }

    public int getCurrentCard() {
        return currentCard;
    }

    /**
     * Shuffles the deck randomly
     */
    public void shuffle() {
        for (int i = 0; i < cards.length; i++) {
            int randomIndex = (int) (Math.random() * cards.length);
            Card temp = cards[i];
            cards[i] = cards[randomIndex];
            cards[randomIndex] = temp;
        }
    }

    /**
     * Draws one card to the board
     * @return one card, or null if deck is empty
     */
    public Card drawCard() {
        if (currentCard < cards.length)
            return cards[currentCard++];
        else
            return null;
    }

    /**
     * Checks if n cards can be drawn (must be multiple of 3)
     * @param n the number of cards to draw
     * @return true if n is multiple of 3 and there are cards left in the deck
     */
    public boolean canDraw(int n) {
        return n % 3 == 0 && getRemainingCount() >= n;
    }

    /**
     * Returns the number of cards remaining in the deck that have not been drawn yet
     *
     * @return the number of cards left in the deck
     */
    public int getRemainingCount() {
        return cards.length - currentCard;
    }

    /**
     * Draws and returns n cards from the deck if available and if n is divisible by 3
     * If fewer than n cards are left or n is not multiple of 3, returns an empty array
     *
     * @param n the number of cards to draw
     * @return an array of drawn cards or an empty array if not possible
     */
    public Card[] drawCards(int n) {
        if (n % 3 != 0 || getRemainingCount() < n)
            return new Card[0];

        Card[] cardsToAdd = new Card[n];
        for (int i = 0; i < cardsToAdd.length; i++)
            cardsToAdd[i] = drawCard();
        return cardsToAdd;
    }

    /**
     * Resets the deck so all cards can be drawn again
     */
    public void reset() {
        currentCard = 0;
    }

    /**
     * @return true if deck has no remaining cards
     */
    public boolean isEmpty() {
        return currentCard >= cards.length;
    }
}

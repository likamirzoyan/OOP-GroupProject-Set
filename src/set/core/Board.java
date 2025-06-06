package set.core;

/**
 * Represents the SET game board which holds up to 15 cards
 */
public class Board {
    // the cards on the board
    private Card[] cards;
    // tracks the number of cards currently in play
    private int count;

    /**
     * Creates an empty board
     */
    public Board(){
        this.cards = new Card[15];
        this.count = 0;
    }

    /**
     * Copy constructor
     */
    public Board(Board copy) {
        this.cards = new Card[15];
        for (int i = 0; i < copy.count; i++)
            this.cards[i] = new Card(copy.cards[i]);
        this.count = copy.count;
    }

    public Card[] getCards() {
        Card[] copy = new Card[count];
        for (int i = 0; i < count; i++) {
            if (cards[i] != null)
                copy[i] = new Card(cards[i]);
        }
        return copy;
    }
    public int getCount() {
        return count;
    }

    /**
     * Adds cards to the board, up to 15
     */
    public void addCards(Card...newCards) {
        for(Card card : newCards) {
            if(count < cards.length)
                cards[count++] = card;
            else {
                System.out.println("set.core.Board is full, can not add more cards.");
                break;
            }
        }
    }

    /**
     * Attempts to remove a valid set of 3 cards
     * @param firstCard first card
     * @param secondCard second card
     * @param thirdCard third card
     * @return true if successful
     */
    public synchronized boolean tryRemoveSet(Card firstCard, Card secondCard, Card thirdCard) {
        if (!Card.isSet(firstCard, secondCard, thirdCard)) return false;

        Card[] set = {firstCard, secondCard, thirdCard};

        for (Card cardToRemove : set) {
            boolean found = false;

            for (int i = 0; i < count; i++) {
                if (cards[i] != null && cards[i].equals(cardToRemove)) {
                    cards[i] = cards[count - 1];
                    cards[count - 1] = null;
                    count--;
                    found = true;
                    break;
                }
            }

            if (!found) return false; // card not on board
        }

        return true;
    }

    /**
     * Removes cards if they form a valid set
     * @param firstCard first card
     * @param secondCard second card
     * @param thirdCard third card
     * @throws NotValidSetFoundException if invalid
     */
    public void removeCards(Card firstCard, Card secondCard, Card thirdCard) throws NotValidSetFoundException {
        if (!tryRemoveSet(firstCard, secondCard, thirdCard))
            throw new NotValidSetFoundException("The selected cards do not form a valid set.");

    }

    public void removeCardsByIndex(int i, int j, int k) throws NotValidSetFoundException {
        removeCards(cards[i], cards[j], cards[k]);
    }

    public boolean isValidSet(Card firstCard, Card secondCard, Card thirdCard) {
        return Card.isSet(firstCard, secondCard, thirdCard);
    }

    /**
     * @return true if any valid set is on the board
     */
    public boolean hasValidSet() {
        for (int i = 0; i < count - 2; i++) {
            for (int j = i + 1; j < count - 1; j++) {
                for (int k = j + 1; k < count; k++) {
                    if (isValidSet(cards[i], cards[j], cards[k]))
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines if board is full based on game rules
     */
    public boolean isFull(int cardsLeftInDeck, boolean hasValidSet) {
        if (count < 12)
            return false; // still room to reach 12

        if (count == 12) {
            if (hasValidSet)
                return true; // 12 cards on the board and at least 1 valid set -> the board is full
            else
                return cardsLeftInDeck == 0; // if deck is empty and no valid set -> can't add cards -> the deck is not full
        }
        return count == 15; // maximum cards on the board
    }

    /**
     * Removes all cards from the board
     */
    public void clear() {
        for (int i = 0; i < count; i++)
            cards[i] = null;

        count = 0;
    }

    /**
     * @return text representation of the board
     */
    @Override
    public String toString() {
        String result = "set.core.Board:\n";

        for (int i = 0; i < count; i++)
            result = result + "[" + i + "]" + cards[i] + "\n";

        return result;
    }
}



package set.core;

public class Board {
    // the cards on the board
    private Card[] cards;
    // tracks the number of cards currently in play
    private int count;

    public Board(){
        this.cards = new Card[15];
        this.count = 0;
    }

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

    // TODO: catch the exception in GUI or main
    public void removeCards(Card firstCard, Card secondCard, Card thirdCard) throws NotValidSetFoundException {
        if (!isValidSet(firstCard, secondCard, thirdCard))
            throw new NotValidSetFoundException("The selected cards do not form a valid set.");

        Card[] set = {firstCard, secondCard, thirdCard};

        for (Card card : set) {
            boolean removed = false;

            for (int i = 0; i < count; i++) {
                if (cards[i] != null && cards[i].equals(card)) {
                    cards[i] = cards[count - 1];
                    cards[count - 1] = null;
                    count--;
                    removed = true;
                    break;
                }
            }
            if (removed) {
                throw new NotValidSetFoundException("set.core.Card " + card + " was not found on the board.");
            }
        }
    }

    public void removeCardsByIndex(int i, int j, int k) throws NotValidSetFoundException {
        removeCards(cards[i], cards[j], cards[k]);
    }

    public boolean isValidSet(Card firstCard, Card secondCard, Card thirdCard) {
        return Card.isSet(firstCard, secondCard, thirdCard);
    }

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

    public void clear() {
        for (int i = 0; i < count; i++)
            cards[i] = null;

        count = 0;
    }

    @Override
    public String toString() {
        String result = "set.core.Board:\n";

        for (int i = 0; i < count; i++)
            result = result + "[" + i + "]" + cards[i] + "\n";

        return result;
    }
    //TODO: define getAllValidSets()
}



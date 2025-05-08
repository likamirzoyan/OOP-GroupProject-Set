public class Deck {
    private Card[] cards;
    private int currentCard;

    public Deck() {
        cards = new Card[81];
        currentCard = 0;
    }

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

    public void shuffle() {
        for (int i = 0; i < cards.length; i++) {
            int randomIndex = (int) (Math.random() * cards.length);
            Card temp = cards[i];
            cards[i] = cards[randomIndex];
            cards[randomIndex] = temp;
        }
    }

    public boolean isEmpty() {
        return currentCard >= cards.length;
    }
}

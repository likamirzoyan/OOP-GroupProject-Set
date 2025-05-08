public class Board {
    private Card[] cards;
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
                System.out.println("Board is full, can not add more cards.");
                break;
            }
        }
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
}



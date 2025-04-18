public class Board {
    private Card[] cards;
    private int count;
    private Card[][] validSet;

    public Board(){
        this.cards = new Card[12];
        this.count = 0;
        this.validSet = new Card[20][3];
    }

    public Board(Board copy){
        this.cards = new Card[copy.cards.length];
        for(int i = 0; i < copy.cards.length; i++)
            this.cards[i] = new Card(copy.cards[i]);
        this.count = copy.count;
        this.validSet = new Card[copy.validSet.length][3];
        for(int i = 0; i < copy.validSet.length; i++) {
            for(int j = 0; j < 3; j++)
                this.validSet[i][j] = new Card(copy.validSet[i][j]);
        }
    }

    public Card[] getCards() {
        return cards;
    }
    public int getCount() {
        return count;
    }
    public Card[][] getValidSets() {
        return validSet;
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

    public void setValidSets(Card[][] validSet) {
        this.validSet = validSet;
    }

}



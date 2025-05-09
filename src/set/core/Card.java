package set.core;

public class Card {
    private Color color;
    private Shape shape;
    private Filling filling;
    private Number number;

    public Card(Color color, Shape shape, Filling filling, Number number) {
        this.color = color;
        this.shape = shape;
        this.filling = filling;
        this.number = number;
    }

    public Card(Card copy) {
        this.color = copy.color;
        this.shape = copy.shape;
        this.filling = copy.filling;
        this.number = copy.number;
    }

    public enum Color {
        RED, GREEN, PURPLE
    }
    public enum Shape {
        OVAL, SQUIGGLE, DIAMOND
    }
    public enum Filling {
        SOLID, STRIPED, OUTLINED
    }
    public enum Number {
        ONE, TWO, THREE
    }

    public Color getColor() {
        return color;
    }
    public Shape getShape() {
        return shape;
    }
    public Filling getFilling() {
        return filling;
    }
    public Number getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return color + " " + shape + " " + filling + " " + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Card other = (Card) o;
        return color == other.color &&
                shape == other.shape &&
                filling == other.filling &&
                number == other.number;
    }

    public static boolean isSet(Card firstCard, Card secondCard, Card thirdCard) {
        return allSameOrAllDifferent(firstCard.color, secondCard.color, thirdCard.color) &&
                allSameOrAllDifferent(firstCard.shape, secondCard.shape, thirdCard.shape) &&
                allSameOrAllDifferent(firstCard.filling, secondCard.filling, thirdCard.filling) &&
                allSameOrAllDifferent(firstCard.number, secondCard.number, thirdCard.number);
    }

    private static <T> boolean allSameOrAllDifferent(T a, T b, T c) {
        return (a.equals(b) && b.equals(c)) || (!a.equals(b) && !b.equals(c));
    }

    public String getImagePath() {
        return "images/" + color + "_" + shape + "_" + filling + "_" + number + ".png";
    }

    /* Testing
    public static void main(String[] args) {
        set.core.Card card = new set.core.Card(Color.PURPLE, Shape.OVAL, Filling.STRIPED, Number.ONE);
        System.out.println(card.shape);
    }*/
}

package set.core;

/**
 * Represents a card in the SET game. Each card has four attributes:
 * color, shape, filling and number
 */
public class Card {
    // The color of the card (RED, GREEN, PURPLE)
    private Color color;
    // The shape on the card (OVAL, SQUIGGLE, DIAMOND)
    private Shape shape;
    // The filling of the shape (SOLID, STRIPED, OUTLINED)
    private Filling filling;
    // The number of shapes on the card (ONE, TWO, THREE)
    private Number number;

    /**
     * Constructs a Card with specific attributes
     * @param color color
     * @param shape shape
     * @param filling filling
     * @param number number
     */
    public Card(Color color, Shape shape, Filling filling, Number number) {
        this.color = color;
        this.shape = shape;
        this.filling = filling;
        this.number = number;
    }

    /**
     * Copy constructor
     * @param copy copy of the card
     */
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

    /**
     * @return a string representation of the card
     */
    @Override
    public String toString() {
        return color + " " + shape + " " + filling + " " + number;
    }

    /**
     * Checks if two cards are equal based on their attributes
     */
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

    /**
     * Checks if three cards form a valid SET
     */
    public static boolean isSet(Card firstCard, Card secondCard, Card thirdCard) {
        return allSameOrAllDifferent(firstCard.color, secondCard.color, thirdCard.color) &&
                allSameOrAllDifferent(firstCard.shape, secondCard.shape, thirdCard.shape) &&
                allSameOrAllDifferent(firstCard.filling, secondCard.filling, thirdCard.filling) &&
                allSameOrAllDifferent(firstCard.number, secondCard.number, thirdCard.number);
    }

    private static <T> boolean allSameOrAllDifferent(T a, T b, T c) {
        return (a.equals(b) && b.equals(c)) || (!a.equals(b) && !b.equals(c));
    }

    /**
     * @return the image path based on card attributes
     */
    public String getImagePath() {
        return "images/" + color + "_" + shape + "_" + filling + "_" + number + ".png";
    }

    /* Testing
    public static void main(String[] args) {
        set.core.Card card = new set.core.Card(Color.PURPLE, Shape.OVAL, Filling.STRIPED, Number.ONE);
        System.out.println(card.shape);
    }*/
}

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

    /* Testing
    public static void main(String[] args) {
        Card card = new Card(Color.PURPLE, Shape.OVAL, Filling.STRIPED, Number.ONE);
        System.out.println(card.shape);
    }*/
}

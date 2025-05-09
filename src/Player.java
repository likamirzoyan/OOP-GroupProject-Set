public class Player extends Person {
    private int score;

    public Player(String name) {
        super(name);
        this.score = 0;
    }

    public Player(Player other) {
        super(other.name);
        this.score = other.score;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

    @Override
    public void printInfo() {
        System.out.println("Player: " + name);
        System.out.println("Score: " + score);
    }
}

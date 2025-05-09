package set.core;

public abstract class Player {
    protected String name;
    protected int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public Player(Player other) {
        this.name = other.name;
        this.score = other.score;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

    public abstract void play(Board board);

    public void printInfo() {
        System.out.println("set.core.Player: " + name);
        System.out.println("Score: " + score);
    }
}

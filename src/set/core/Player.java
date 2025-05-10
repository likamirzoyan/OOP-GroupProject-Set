package set.core;

/**
 * Abstract base class representing a player in the game
 * Extended by human and AI players
 */
public abstract class Player {
    // the name of a player
    protected String name;
    // the score of a player
    protected int score;

    /**
     * Constructs a player with a given name
     * @param name the player's name
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    /**
     * Copy constructor
     */
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

    /**
     * Increases the player's score by one
     */
    public void increaseScore() {
        score++;
    }

    /**
     * Abstract method for player action
     * @param board the game board
     */
    public abstract void play(Board board);

    /**
     * Prints player information to the console
     */
    public void printInfo() {
        System.out.println("set.core.Player: " + name);
        System.out.println("Score: " + score);
    }
}

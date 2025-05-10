package set.core;

/**
 * Represents a human player in the game
 * Actions are handled via GUI interaction
 */
public class HumanPlayer extends Player {

    /**
     * Constructs a HumanPlayer with a given name
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * Copy constructor
     */
    public HumanPlayer(HumanPlayer other) {
        super(other.name);
    }

    /**
     * Human does not play automatically; interaction is via GUI
     * @param board the game board
     */
    @Override
    public void play(Board board) {
        // GUI handles this — human will click cards manually
    }

    @Override
    public void printInfo(){
        System.out.println("Human Player: " + name);
        System.out.println("Score: " + score);
    }
}

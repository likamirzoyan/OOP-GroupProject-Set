package set.core;

import set.gui.GameGUI;

/**
 * Represents an AI player that automatically plays SET
 * Uses Runnable to run in a separate thread
 */
public class AIPlayer extends Player implements Runnable {
    // the board the AI is playing on
    private Board board;
    // the GUI to update the board and scores
    private GameGUI gui;
    // whether the AI is active depending on the state of the game
    private boolean active = true;

    /**
     * Constructs an AI player with a name, board, and GUI
     */
    public AIPlayer(String name, Board board, GameGUI gui) {
        super(name);
        this.board = board;
        this.gui = gui;
    }

    /**
     * Copy constructor
     */
    public AIPlayer(AIPlayer other) {
        super(other.name);
        this.board = new Board(other.board);
        this.gui = null; // GUI will be assigned later
    }

    /**
     * The AI's main logic loop
     */
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            tryToPlay();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Assigns a GUI for the AI to update
     */
    public void setGUI(GameGUI gui) {
        this.gui = gui;
    }

    /**
     * Stops the AI from playing
     */
    public void stop() {
        active = false;
    }

    /**
     * The AI attempts to find and play a valid set
     */
    private void tryToPlay() {
        Card[] cards = board.getCards();

        for (int i = 0; i < cards.length - 2; i++) {
            for (int j = i + 1; j < cards.length - 1; j++) {
                for (int k = j + 1; k < cards.length; k++) {
                    if (Card.isSet(cards[i], cards[j], cards[k])) {
                        boolean removed = board.tryRemoveSet(cards[i], cards[j], cards[k]);
                        if (removed) {
                            increaseScore();
                            gui.updateScores();
                            gui.drawBoard();
                            return; // only one move per cycle
                        }
                    }
                }
            }
        }
    }

    @Override
    public void play(Board board){
        tryToPlay();
    }

    @Override
    public void printInfo(){
        System.out.println("AI Player: " + name);
        System.out.println("Score: " + score);
    }
}
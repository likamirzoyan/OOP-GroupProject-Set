package set.core;

/**
 * Manages the overall state of a SET game
 * Includes players, board, and deck
 */
public class Game {
    // the human player
    private HumanPlayer human;
    // the AI player
    private AIPlayer ai;
    // the board where the game is played
    private Board board;
    // the deck of cards
    private Deck deck;
    // whether the game includes an AI
    private boolean withAI;

    /**
     * Starts a new game with or without AI
     * @param withAI true if AI should play
     */
    public Game(boolean withAI) {
        this.withAI = withAI;
        this.human = new HumanPlayer("You");
        this.board = new Board();
        this.deck = new Deck();
        board.addCards(deck.drawCards(12));

        if (withAI)
            this.ai = new AIPlayer("Kesha", board, null);
    }

    /**
     * Copy constructor
     */
    public Game(Game other) {
        this.withAI = other.withAI;
        this.human = new HumanPlayer(other.human);
        this.board = new Board(other.board);
        this.deck = new Deck(other.deck);
        if (other.ai != null)
            this.ai = new AIPlayer(other.ai);
    }

    public HumanPlayer getHuman() {
        return new HumanPlayer(human);
    }

    public AIPlayer getAi() {
        return ai != null ? new AIPlayer(ai) : null;
    }

    public Board getBoard() {
        return new Board(board);
    }

    public Deck getDeck() {
        return new Deck(deck);
    }

    public boolean isWithAI() {
        return withAI;
    }

    /**
     * Sets the GUI for the AI (if present)
     */
    public void isWithAI(set.gui.GameGUI gui) {
        if (ai != null)
            ai.setGUI(gui);
    }

    /**
     * Starts a new round, resetting the game state
     */
    public void startNewGame() {
        board.clear();
        deck.reset();
        human = new HumanPlayer("You");
        if (withAI)
            ai = new AIPlayer("Kesha", board, null);

        board.addCards(deck.drawCards(12));
    }

    /**
     * @return true if game is over (deck empty + no sets)
     */
    public boolean isGameOver() {
        return deck.isEmpty() && !board.hasValidSet();
    }

    /**
     * Assigns a GUI to the AI player
     */
    public void setGUIForAI(set.gui.GameGUI gui) {
        if (ai != null) {
            ai.setGUI(gui);
        }
    }
}

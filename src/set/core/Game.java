package set.core;

public class Game {
    private HumanPlayer human;
    private AIPlayer ai;
    private Board board;
    private Deck deck;
    private boolean withAI;

    public Game(boolean withAI) {
        this.withAI = withAI;
        this.human = new HumanPlayer("You");
        this.board = new Board();
        this.deck = new Deck();
        board.addCards(deck.drawCards(12));

        if (withAI)
            this.ai = new AIPlayer("Kesha", board, null);
    }

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

    public void isWithAI(set.gui.GameGUI gui) {
        if (ai != null)
            ai.setGUI(gui);
    }

    public void startNewGame() {
        board.clear();
        deck.reset();
        human = new HumanPlayer("You");
        if (withAI)
            ai = new AIPlayer("Kesha", board, null);

        board.addCards(deck.drawCards(12));
    }

    public boolean isGameOver() {
        return deck.isEmpty() && !board.hasValidSet();
    }
}

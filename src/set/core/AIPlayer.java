package set.core;

import set.gui.GameGUI;

public class AIPlayer extends Player implements Runnable {
    private Board board;
    private GameGUI gui;
    private boolean active = true;

    public AIPlayer(String name, Board board, GameGUI gui) {
        super(name);
        this.board = board;
        this.gui = gui;
    }

    public AIPlayer(AIPlayer other) {
        super(other.name);
        this.board = new Board(other.board);
        this.gui = null; // GUI will be assigned later
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            tryToPlay();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void setGUI(GameGUI gui) {
        this.gui = gui;
    }

    public void stop() {
        active = false;
    }

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
package set.core;

public class Game {
    private Player[] players;
    private Player player;
    private Board board;
    private Deck deck;
    private int playerCount;

    public Game(Player[] players) {
        this.players = players;
        this.playerCount = players.length;
        this.board = new Board();
        this.deck = new Deck();
    }

}

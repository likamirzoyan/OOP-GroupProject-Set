package set.gui;

import set.core.*;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.URL;

/**
 * The graphical user interface for the SET game
 */
public class GameGUI {
    // the main window frame
    private JFrame frame;
    // panel showing the game board
    private JPanel boardPanel;
    // panel displaying scores and info
    private JPanel infoPanel;
    // label showing the human score
    private JLabel humanLabel;
    // label showing the AI score
    private JLabel aiLabel;

    // the game logic controller
    private Game game;
    private HumanPlayer human;
    private AIPlayer ai;
    private Board board;
    private Deck deck;
    // the thread the AI runs on
    private Thread aiThread;
    // stores selected cards during user input
    private ArrayList<Card> selectedCards = new ArrayList<>();

    /**
     * Initializes GUI and starts the game
     */
    public GameGUI() {
        boolean withAI = askGameMode();
        game = new Game(withAI);
        board = game.getBoard();
        deck = game.getDeck();
        human = game.getHuman();
        ai = game.getAi();

        setupGUI();
        drawBoard();

        if (withAI && ai != null) {
            game.setGUIForAI(this);
            aiThread = new Thread(ai);
            aiThread.start();
            ai.play(board);
        }
    }

    /**
     * Asks user whether to play with AI
     * @return true if yes
     */
    private boolean askGameMode() {
        // Show a simple dialog to the user with 2 buttons
        // The user can choose to play alone or with AI
        Object[] options = {"Play Alone", "Play with AI"};

        int choice = JOptionPane.showConfirmDialog(
                null,
                "Do you want to play with the AI?",
                "Choose Mode",
                JOptionPane.YES_NO_OPTION
        );

        return choice == JOptionPane.YES_OPTION; // true if user clicked 'Yes'

    }

    /**
     * Sets up the GUI components
     */
    private void setupGUI() {
        frame = new JFrame("SET Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        boardPanel = new JPanel(new GridLayout(3, 5, 10, 10));
        infoPanel = new JPanel(new GridLayout(1, 2));

        humanLabel = new JLabel("Your Score: 0");
        aiLabel = new JLabel(game.isWithAI() ? "AI Score: 0" : "");

        infoPanel.add(humanLabel);
        infoPanel.add(aiLabel);

        frame.add(infoPanel, BorderLayout.NORTH);
        frame.add(boardPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    /**
     * Draws the cards and buttons on the board
     */
    public void drawBoard() {
        boardPanel.removeAll();
        Card[] cards = board.getCards();

        for (Card card : cards) {
            String imagePath = card.getImagePath();
            URL imageUrl = getClass().getClassLoader().getResource(imagePath);

            if (imageUrl != null) {
                ImageIcon icon = new ImageIcon(imageUrl);
                Image scaled = icon.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);
                JButton btn = new JButton(new ImageIcon(scaled));

                btn.addActionListener(e -> {
                    selectedCards.add(card);
                    btn.setBackground(Color.YELLOW);

                    if (selectedCards.size() == 3) {
                        try {
                            board.removeCards(
                                    selectedCards.get(0),
                                    selectedCards.get(1),
                                    selectedCards.get(2));
                            human.increaseScore();
                            board.addCards(deck.drawCards(3));
                        } catch (NotValidSetFoundException ex) {
                            JOptionPane.showMessageDialog(frame, ex.getMessage());
                        }
                        selectedCards.clear();
                        drawBoard();
                        updateScores();
                    }
                });
                boardPanel.add(btn);
            } else {
                System.err.println("Image not found: " + imagePath);
            }
        }

        boardPanel.revalidate();
        boardPanel.repaint();
    }

    /**
     * Updates the displayed scores for both players
     */
    public void updateScores() {
        Runnable updateTask = new Runnable() {
            public void run() {
                humanLabel.setText("Your Score: " + human.getScore());
                if (game.isWithAI() && ai != null) {
                    aiLabel.setText("AI Score: " + ai.getScore());
                }
            }
        };
        SwingUtilities.invokeLater(updateTask);
    }

    /**
     * Stops the AI thread and activity
     */
    public void stopAI() {
        if (ai != null) {
            ai.stop();
            aiThread.interrupt();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameGUI::new);
    }
}
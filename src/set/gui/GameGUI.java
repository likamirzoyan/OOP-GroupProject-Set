package set.gui;

import set.core.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameGUI {
    private JFrame frame;
    private JPanel boardPanel, infoPanel;
    private JLabel humanLabel, aiLabel;

    private Game game;
    private HumanPlayer human;
    private AIPlayer ai;
    private Board board;
    private Deck deck;
    private Thread aiThread;
    private ArrayList<Card> selectedCards = new ArrayList<>();

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

    public void drawBoard() {
        boardPanel.removeAll();
        Card[] cards = board.getCards();

        for (Card card : cards) {
            String imagePath = card.getImagePath();
            ImageIcon icon = new ImageIcon(imagePath);
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
        }

        boardPanel.revalidate();
        boardPanel.repaint();
    }

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
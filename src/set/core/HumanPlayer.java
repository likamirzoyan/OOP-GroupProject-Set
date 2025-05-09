package set.core;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

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

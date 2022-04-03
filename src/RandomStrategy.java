import java.util.Random;

public class RandomStrategy implements Strategy{
    @Override
    public int determineMove(int playerMove, int lastUse, int[] moveUsage) {
        Random rand = new Random();
        return rand.nextInt(3);
    }
}
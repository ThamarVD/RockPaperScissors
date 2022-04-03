import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MostStrategy implements Strategy{
    @Override
    public int determineMove(int playerMove, int lastUse, int[] moveUsage) {
        Random rand = new Random();
        int largestVal = moveUsage[0];
        ArrayList<Integer> largeIdx = new ArrayList<Integer>();
        for (int i = 0; i < moveUsage.length; i++) {
            if(moveUsage[i] > largestVal){
                largeIdx = new ArrayList<>();
                largestVal = moveUsage[i];
            }
            if(moveUsage[i] == largestVal){
                largeIdx.add(i);
            }
        }
        return largeIdx.get(rand.nextInt(largeIdx.size()));
    }
}
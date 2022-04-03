import java.util.ArrayList;
import java.util.Random;

public class LeastStrategy implements Strategy{
    @Override
    public int determineMove(int playerMove, int lastUse, int[] moveUsage) {
        Random rand = new Random();
        int smallestVal = moveUsage[0];
        ArrayList<Integer> smallIdx = new ArrayList<Integer>();
        for (int i = 0; i < moveUsage.length; i++) {
            if(moveUsage[i] < smallestVal){
                smallIdx = new ArrayList<>();
                smallestVal = moveUsage[i];
            }
            if(moveUsage[i] == smallestVal){
                smallIdx.add(i);
            }
        }
        return smallIdx.get(rand.nextInt(smallIdx.size()));
    }
}
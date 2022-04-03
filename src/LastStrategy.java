public class LastStrategy implements Strategy{
    @Override
    public int determineMove(int playerMove, int lastUse, int[] moveUsage) {
        return lastUse;
    }
}
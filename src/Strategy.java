public interface Strategy {
    int determineMove(int playerMove, int lastUse, int[] moveUsage);
}

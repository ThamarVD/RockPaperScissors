public class CheatStrategy implements Strategy{
    @Override
    public int determineMove(int playerMove, int lastUse, int[] moveUsage) {
        switch (playerMove){
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 0;
            default:
                return -1;
        }
    }
}
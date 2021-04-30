package utils;

public class DistanceUtil {
    public static int findDistance(int x1, int x2, int y1, int y2) {
        int xDist = Math.max(x1, x2) - Math.min(x1, x2);
        int yDist = Math.max(y1, y2) - Math.min(y1, y2);
        return xDist + yDist;
    }
}

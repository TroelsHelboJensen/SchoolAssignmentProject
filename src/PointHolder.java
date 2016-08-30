/**
 * Created by lordni on 4/21/16.
 */
public class PointHolder {
    private static PointHolder me = null;
    private int points, allPoints;
    private double procentDone;

    public int getPoints() {
        return points;
    }

    public int getAllPoints() {
        return allPoints;
    }

    public double getProcentDone() {
        return procentDone;
    }

    public void setAllDonePoints(int donePoints) {
        points = donePoints;
    }

    public void setAllPoints(int allPoints) {
        this.allPoints = allPoints;
    }

    private PointHolder() {
        points = 0;
        allPoints = 0;
        procentDone = 0;
    }

    public void calculatePointProcent() {
        double top = points;
        double bottom = allPoints;
        procentDone = (int) (top/bottom * 100);
    }

    public static PointHolder getInstance() {
        if(me == null)
            me = new PointHolder();
        return me;
    }
}

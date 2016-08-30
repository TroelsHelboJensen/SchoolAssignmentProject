import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lordni on 4/8/16.
 */
public class MakeProgress {

    public static void main(String[] args) {

        FileHandler fileHandler = new FileHandler();
        List<String> readList = fileHandler.getProgressFile();
        ListHandler lh = new ListHandler();
        List<Uge> progressList = lh.readList(readList);

        calcAllPoints(progressList);

        HTMLMaker index = new HTMLMaker(progressList);
        index.makeIndexFile();

        HTMLMaker htmlMaker = new HTMLMaker(progressList);
        htmlMaker.makeHTMLDoc();

        MakeWeekHTML weekMaker = new MakeWeekHTML();
        weekMaker.makeWeek(progressList);

        // Only for the source java code
        System.out.println("Done");
        // if made to a jar, do remake of done msg
    }

    public static void calcAllPoints(List<Uge> aList) {
        int allPoints = 0;
        int donePoints = 0;
        for(Uge uge: aList) {
            uge.calculatePoints();
            allPoints += uge.getTotalPoints();
            donePoints += uge.getDonePoints();
        }
        PointHolder.getInstance().setAllPoints(allPoints);
        PointHolder.getInstance().setAllDonePoints(donePoints);
        PointHolder.getInstance().calculatePointProcent();
    }
}

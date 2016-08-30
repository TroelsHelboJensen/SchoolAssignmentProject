import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lordni on 4/19/16.
 */
public class MakeWeekHTML {
    private List<String> weeksToTables;

    public void makeWeek(List<Uge> aList) {
        for (Uge week: aList) {
            weeksToTables = new ArrayList<>();
            makeHTMLStart();
            makeTableStartup();
            makeTableHeader(week);
            makeTableData(week);
            endOfHTMLDoc();
            writeToWeekFile("uge" + week.getUgeNr() + ".html");
        }
    }

    private void makeHTMLStart() {
        weeksToTables.add("<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<!-- Latest compiled and minified CSS --><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">\n" +
                "<!-- Optional theme -->\n" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css\" integrity=\"sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r\" crossorigin=\"anonymous\">\n" +
                "<!-- Latest compiled and minified JavaScript --><script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\" integrity=\"sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS\" crossorigin=\"anonymous\">\n" +
                "\t</script>\n" +
                "\t<!-- jQuery v1.12.0 --><script src=\"http://code.jquery.com/jquery-1.12.0.min.js\">\n" +
                "\t</script>\n" +
                "\t<link rel=\"stylesheet\" href=\"style.css\">\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<div class=\"container\">\n");
    }

    private void makeTableStartup() {
        weeksToTables.add("<table style=\"width:100%\">\n");
    }

    private void makeTableHeader (Uge week) {
        weeksToTables.add("\t\t\t<tr>\n" +
                "\t\t\t\t<th class=\"week\" colspan=\"2\">Uge " + week.getUgeNr() + "</th>\n" +
                "\t\t\t\t<th class=\"week\" colspan=\"2\">Points " + week.getDonePoints() + " of " + week.getTotalPoints() + "</th>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<th>" + "Opgave nr</th>\n" +
                "\t\t\t\t<th>" + "Points</th>\n" +
                "\t\t\t\t<th>" + "Done</th>\n" +
                "\t\t\t\t<th>" + "Afleveret</th>\n" +
                "\t\t\t</tr>\n"
        );
    }

    private void makeTableData(Uge thisWeek) {
        if (thisWeek.getExercises().size() > 0) {
            weeksToTables.add("\t\t\t<tr><th colspan=\"4\">Exercise</th></tr>\n");
            for (Opgave opg: thisWeek.getExercises()) {
                weeksToTables.add("\t\t\t<tr>\n" +
                        "\t\t\t\t<td>" + opg.getOpgaveNummer() + "</td>\n" +
                        "\t\t\t\t<td>" + opg.getPoints() + "</td>\n" +
                        "\t\t\t\t<td>" + makeDone(opg.isDone()) + "</td>\n" +
                        "\t\t\t\t<td>" + makeDone(opg.isAfleveret()) + "</td>\n" +
                        "\t\t\t</tr>\n"
                );
            }
        }

        if (thisWeek.getProjects().size() > 0) {
            weeksToTables.add("\t\t\t<tr><th colspan=\"4\">Project</th></tr>\n");
            for (Opgave opg: thisWeek.getProjects()) {
                weeksToTables.add("\t\t\t<tr>\n" +
                        "\t\t\t\t<td>" + opg.getOpgaveNummer() + "</td>\n" +
                        "\t\t\t\t<td>" + opg.getPoints() + "</td>\n" +
                        "\t\t\t\t<td>" + makeDone(opg.isDone()) + "</td>\n" +
                        "\t\t\t\t<td>" + makeDone(opg.isAfleveret()) + "</td>\n" +
                        "\t\t\t</tr>\n"
                );
            }
        }
    }

    private String makeDone(boolean done) {
        String valueReturn = "";
        if(done) {
            valueReturn = "X";
        }
        return valueReturn;
    }

    private void endOfHTMLDoc() {
        weeksToTables.add("</table>\n" +
                "\t</body>\n" +
                "</html>");
    }

    private void writeToWeekFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("HTML/UGER/" + filename)))) {
            Iterator<String> itr = weeksToTables.iterator();
            while (itr.hasNext()) {
                writer.write(itr.next());
            }
            writer.close();
        } catch (FileNotFoundException eFNF) {
            System.out.println("File Not there " + eFNF);
        } catch (IOException eIO) {
            System.out.println("Buffered Exception " + eIO);
        }
    }
}

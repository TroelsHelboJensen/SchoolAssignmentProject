import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lordni on 4/9/16.
 */
public class HTMLMaker {
    private List<Uge> dataList;
    private List<String> htmlList;
    private List<String> cssList;

    // make index.html with a dynamic menu.. :)
    public void makeIndexFile(){
        startOfHTMLIndexDoc();

        htmlList.add("<div class=\"container\">\n" +
        "<nav class=\"navbar navbar-inverse\">\n" +
        "<div class=\"container-fluid\">\n" +
        "<div class=\"navbar-header\">\n" +
        "<div class=\"navbar-brand\"><a href=\"index.html\">Progress\n</a>" +
        "</div>\n" +
        "<ul class=\"nav navbar-nav\" id=\"nav-link\">\n"
        );
        for (Uge uge: dataList){
            makeMenuContentOfIndexFile(uge);
        }

        htmlList.add("</ul>\n" +
        "</div>\n" +
        "</nav>\n" +
        "</div>\n" +
        "<div id=\"content\">\n" +
        "</div>\n" +
        "</body>\n" +
        "</html>\n"
        );

        writeToIndexFile("index.html");
    }

    private void startOfHTMLIndexDoc() {
        htmlList.add("<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<link rel=\"stylesheet\" href=\"style.css\">\n" +
                // new HTML header
                "<meta charset=\"utf-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +

                "<!-- Latest compiled and minified CSS -->" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">\n" +

                "<!-- Optional theme -->\n" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css\" integrity=\"sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r\" crossorigin=\"anonymous\">\n" +

                "<!-- Latest compiled and minified JavaScript -->" +
                "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\" integrity=\"sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS\" crossorigin=\"anonymous\">\n" +
                "</script>\n" +

                "<!-- jQuery v1.12.0 -->" +
                "<script src=\"http://code.jquery.com/jquery-1.12.0.min.js\">\n" +
                "</script>\n" +

                "<!-- Trying jQuery js load -->\n" +
                "<script>\n" +
                "$(document).ready(function(){\n" +
                    "$('#content').load('content.html', function(){\n" +
                        "$(this).show();\n" +
                    "});\n" +
                "});\n" +

                "$(function () {\n" +
                    "$('#nav-link li a').on('click', function (e) {\n" +
                        "e.preventDefault();\n" +
                        "var page = $(this).attr('href');\n" +
                        "$('#content').load(page);\n" +
                    "});\n" +
                "});\n" +
                "</script>\n" +

                "\t</head>\n" +
                "\t<body>\n");
    }

    private void makeMenuContentOfIndexFile(Uge week){
        htmlList.add("<li>" +
        "<a href=\"UGER/uge" + week.getUgeNr() + ".html\">" +
                "Uge" + week.getUgeNr() + "</a></li>"
        );
    }

    public HTMLMaker(List<Uge> list){
        dataList = list;
        htmlList = new ArrayList<>();
        cssList = new ArrayList<>();
        makeCSS();
    }

    private void makeCSS() {
        cssList.add("table {\n" +
                "\tborder: 0px solid black;\n" +
                "}\n" +
                "\n" +
                "tr > th {\n" +
                "\tborder: 1px solid black;\n" +
                "}\n" +
                "th, td {\n" +
                "\ttext-align: center;\n" +
                "}");
        writeToIndexFile("style.css", cssList);
    }

    public void makeHTMLDoc() {
        String fileName = "content.html";
        startOfHTMLDocAndTableHeader();
        //makeMenu();
        htmlList.add("<div class=\"container\">\n" +
                     "\t\t<table style=\"width:100%\">\n" +
                     "\t\t\t<center><b>Points " + PointHolder.getInstance().getPoints() +
                     " of " + PointHolder.getInstance().getAllPoints() +
                     " that is " + PointHolder.getInstance().getProcentDone() + " %" +
                     "</b></center>"
        );
        for (Uge uge: dataList) {
            //uge.calculatePoints();
            tableDataHeader(uge);
            generateTableDataFromWeek(uge);
        }
        htmlList.add("\t\t</table>\n" +
                "</div>");
        endOfHTMLDoc();

        writeToIndexFile(fileName);
    }

    private void startOfHTMLDocAndTableHeader() {
        htmlList.add("<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +

                // new HTML header
                "<meta charset=\"utf-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +

                "<!-- Latest compiled and minified CSS -->" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">\n" +

                "<!-- Optional theme -->\n" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css\" integrity=\"sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r\" crossorigin=\"anonymous\">\n" +

                "<!-- Latest compiled and minified JavaScript -->" +
                "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\" integrity=\"sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS\" crossorigin=\"anonymous\">\n" +
                "</script>\n" +

                "<!-- jQuery v1.12.0 -->" +
                "<script src=\"http://code.jquery.com/jquery-1.12.0.min.js\">\n" +
                "</script>\n" +

                "\t\t<link rel=\"stylesheet\" href=\"style.css\">\n" +
                "\t</head>\n" +
                "\t<body>\n");
    }

    private void tableDataHeader(Uge week) {
        htmlList.add("\t\t\t<tr>\n" +
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

    private void generateTableDataFromWeek(Uge thisWeek) {
        if (thisWeek.getExercises().size() > 0) {
            htmlList.add("\t\t\t<tr><th colspan=\"4\">Exercise</th></tr>\n");
            for (Opgave opg: thisWeek.getExercises()) {
                htmlList.add("\t\t\t<tr>\n" +
                             "\t\t\t\t<td>" + opg.getOpgaveNummer() + "</td>\n" +
                             "\t\t\t\t<td>" + opg.getPoints() + "</td>\n" +
                             "\t\t\t\t<td>" + makeDone(opg.isDone()) + "</td>\n" +
                             "\t\t\t\t<td>" + makeDone(opg.isAfleveret()) + "</td>\n" +
                             "\t\t\t</tr>\n"
                );
            }
        }

        if (thisWeek.getProjects().size() > 0) {
            htmlList.add("\t\t\t<tr><th colspan=\"4\">Project</th></tr>\n");
            for (Opgave opg: thisWeek.getProjects()) {
                htmlList.add("\t\t\t<tr>\n" +
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
        htmlList.add("\t</body>\n" +
                     "</html>");
    }

    private void writeToIndexFile(String filename) {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("HTML/" + filename)))){
            Iterator<String> itr = htmlList.iterator();
            while(itr.hasNext()){
                writer.write(itr.next());
            }
            writer.close();
        } catch(FileNotFoundException eFNF) {
            System.out.println("File Not there " + eFNF);
        } catch(IOException eIO) {
            System.out.println("Buffered Exception " + eIO);
        }
    }

    private void writeToIndexFile(String filename, List<String> aList) {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("HTML/" + filename)))){
            Iterator<String> itr = aList.iterator();
            while(itr.hasNext()){
                writer.write(itr.next());
            }
            writer.close();
        } catch(FileNotFoundException eFNF) {
            System.out.println("File Not there " + eFNF);
        } catch(IOException eIO) {
            System.out.println("Buffered Exception " + eIO);
        }
    }
}
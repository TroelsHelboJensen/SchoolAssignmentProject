import java.util.ArrayList;
import java.util.List;

public class ListHandler {
    /*
     * Split op i uger med opgaver
     */

    public List<Uge> readList(List<String> theList){
        List<Uge> listReturn = new ArrayList<>();
        String exerciseProject = "";

        for (String str: theList){
            if (!str.equalsIgnoreCase("")) {
                if (str.contains("Uge")) {
                    int ugeNummer = Integer.parseInt("" + str.charAt(4) + str.charAt(5));
                    exerciseProject = "";
                    listReturn.add(new Uge(ugeNummer));
                } else if (str.equalsIgnoreCase("exercise:")) {
                    exerciseProject = "exercise";
                } else if (str.equalsIgnoreCase("project:")) {
                    exerciseProject = "project";
                }
                if (exerciseProject.equalsIgnoreCase("exercise") && !str.equalsIgnoreCase("exercise:")) {
                    listReturn.get(listReturn.size() - 1).addExercise(laveNyOpgaveAfString(str));
                } else if (exerciseProject.equalsIgnoreCase("project") && !str.equalsIgnoreCase("project:")) {
                    listReturn.get(listReturn.size() - 1).addProjects(laveNyOpgaveAfString(str));
                } else {
                    // Do nothing if str equals("Uge")
                }
            } else {
                // Do nothing
            }
        }
        return listReturn;
    }

    //TODO: Her er der arbejde. Ved ikke om String[] har størrelse 1,2,3 eller 4(max)
    //DONE?
    private Opgave laveNyOpgaveAfString(String str){
        String[] strArray = str.split("-");
        Opgave opgReturn = null;

        // Trim strArray
        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = strArray[i].trim();
        }

        switch(strArray.length){
            case 1:
                opgReturn = new Opgave(strArray[0]);
                break;
            case 2:
                opgReturn = new Opgave(strArray[0], toInt(strArray[1]));
                break;
            case 3:
                opgReturn = new Opgave(strArray[0], toInt(strArray[1]), strArray[2]);
                break;
            case 4:
                opgReturn = new Opgave(strArray[0], toInt(strArray[1]), strArray[2], strArray[3]);
                break;
            default:
                System.out.println("Default nothing happens");
                break;
        }
        return opgReturn;
    }

    private int toInt(String integer) {
        int point = 0;
        try {
            String strPoint = integer.charAt(1) + "";
            point = Integer.parseInt(strPoint);

        } catch (NumberFormatException eNF){
            System.out.println("Error here");
        }
        return point;
    }
}

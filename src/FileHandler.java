
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lordni on 4/8/16.
 */
public class FileHandler {
    public List<String> getProgressFile(){
        List<String> listReturn = new ArrayList<>();
        /*
         * Make Progress.txt to a list and return it
         * Ny liste for hver uge. skal indholde en String for hver Linje
         * i rækkefølge.
         */

        File filePath = new File("Progress.txt");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))){
        String line = "";
            while ((line = reader.readLine()) != null){
                listReturn.add(line);
            }
            reader.close();
        }catch (FileNotFoundException eFNF){
            System.out.println("FileNotFound " + eFNF);
        }catch(IOException eIO){
            System.out.println("InputOutput " + eIO);
        }

        return listReturn;
    }
}

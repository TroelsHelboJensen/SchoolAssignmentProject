import java.util.ArrayList;
import java.util.List;

/**
 * Created by lordni on 4/9/16.
 */
public class Uge {
    private int ugeNr;
    private int pointsDone = 0, pointsTotal = 0;
    private List<Opgave> exercises, projects;

    public Uge(int ugeNr) {
        this.ugeNr = ugeNr;
        exercises = new ArrayList<>();
        projects = new ArrayList<>();
    }

    // Constructor for opgaver uden for bogen
    public Uge(String ugeNr) {
        this.ugeNr = Integer.parseInt(ugeNr);
        projects = new ArrayList<>();
    }

    // Getters
    public List<Opgave> getExercises() {
        return exercises;
    }
    public List<Opgave> getProjects() {
        return projects;
    }
    public int getUgeNr() {
        return ugeNr;
    }

    // Opgave Adders
    public void addExercise(Opgave opg) {
        exercises.add(opg);
    }
    public void addProjects(Opgave opg) {
        projects.add(opg);
    }

    public void printDetails(){
        System.out.println("Uge Nr.: " + ugeNr);
        if(exercises.size() > 0) {
            System.out.println("Exercises");
            System.out.println("Opgave Nr\tpoints\tDone\tAfleveret");
            for (Opgave opg : exercises) {
                System.out.println(opg.toString());
            }
        }
        if(projects.size() > 0) {
            System.out.println("Projects");
            System.out.println("Opgave Nr\tpoints\tDone\tAfleveret");
            for (Opgave opg : projects) {
                System.out.println(opg.toString());
            }
        }
    }

    public int getDonePoints() { return pointsDone; }
    public int getTotalPoints() { return pointsTotal; }

    public void calculatePoints(){
        for (Opgave opg: exercises){
            if (opg.isDone()) {
                pointsDone += opg.getPoints();
            }
            pointsTotal += opg.getPoints();
        }

        for (Opgave opg: projects){
            if (opg.isDone()) {
                pointsDone += opg.getPoints();
            }
            pointsTotal += opg.getPoints();
        }
    }
}

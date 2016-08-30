/**
 * Created by lordni on 4/9/16.
 */
public class Opgave {
    private String opgaveNummer;
    private int points;
    private boolean done, afleveret;

    // Har kun opgave nummer
    public Opgave(String opgaveNummer) {
        this.opgaveNummer = opgaveNummer;
        points = 0;
        done = false;
        afleveret = false;
    }

    // Mangler done og afleveret
    public Opgave(String opgaveNummer, int points) {
        this.opgaveNummer = opgaveNummer;
        this.points = points;
    }

    // Mangler afleveret
    public Opgave(String opgaveNummer, int points, String done) {
        this.opgaveNummer = opgaveNummer;
        this.points = points;
        this.done = isBoolean(done);
    }
    // everything Constructor
    public Opgave(String opgaveNummer, int points, String done, String afleveret) {
        this.opgaveNummer = opgaveNummer;
        this.points = points;
        this.done = isBoolean(done);
        this.afleveret = isBoolean(afleveret);
    }

    public String getOpgaveNummer() {
        return opgaveNummer;
    }

    public int getPoints() {
        return points;
    }

    private boolean isBoolean(String str) {
        return str.equalsIgnoreCase("done") || str.equalsIgnoreCase("afleveret");
    }

    public boolean isDone() {
        return done;
    }

    public boolean isAfleveret() {
        return afleveret;
    }

    @Override
    public String toString(){
        return opgaveNummer + "\t\t" + points + "\t" + done + "\t" + afleveret;
    }
}

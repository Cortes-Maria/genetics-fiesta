import java.util.*;

public class Zone {
    private int x1, y1;
    private ArrayList<Sample> samples;
    private double probability;
    private ArrayList<SubZone> subZones;

    public Zone(int x1, int y1) {
        setX1(x1);
        setY1(y1);
        setProbability(1.00);
        setSamples(new ArrayList<Sample>());
        setSubZones(new ArrayList<SubZone>());
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public ArrayList<Sample> getSamples() {
        return samples;
    }

    public void setSamples(ArrayList<Sample> samples) {
        this.samples = samples;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public void setSubZones(ArrayList<SubZone> subZones) {
        this.subZones = subZones;
    }

    public ArrayList<SubZone> getSubZones() {
        return subZones;
    }


};




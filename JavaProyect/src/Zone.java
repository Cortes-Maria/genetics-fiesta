import java.awt.*;
import java.util.*;
import java.io.*;

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

    public void setSubZones(ArrayList<SubZone> pSubZones){ this.subZones = pSubZones; }

    public ArrayList<SubZone> getSubZones() {
        return subZones;
    }

    /*
    This is the function responsible for making groups of samples.
    It needs the distance between colors in R^3
     */
    public void groupSamples(int pDistance){
        for (int sample = 0; sample < this.getSamples().size(); sample++){
            if(this.getSamples().get(sample).getR() != 255 && this.getSamples().get(sample).getG()!= 255 &&
                    this.getSamples().get(sample).getB() != 255) {
                boolean check = false;
                if (this.getSubZones().size() == 0) {
                    this.getSubZones().add(new SubZone(this.getSamples().get(sample).getX(), this.getSamples().get(sample).getX(),
                            this.getSamples().get(sample).getY(), this.getSamples().get(sample).getY(),
                            this.getSamples().get(sample).getR(), this.getSamples().get(sample).getG(), this.getSamples().get(sample).getB()));
                    check = true;
                }
                for (int actual = 0; actual < this.getSubZones().size(); actual++) {
                    double distance = 0;
                    //((r2 - r1)2 + (g2 - g1)2 + (b2 - b1)2)1/2
                    double r = Math.pow((this.getSubZones().get(actual).getrSub()-this.getSamples().get(sample).getR()),2);
                    double g = Math.pow((this.getSubZones().get(actual).getgSub()-this.getSamples().get(sample).getG()),2);
                    double b = Math.pow((this.getSubZones().get(actual).getbSub()-this.getSamples().get(sample).getB()),2);
                    distance =  Math.pow((r+g+b),0.5);

                    //System.out.println("Distance between colors is: " + distance);
                    if (distance <= pDistance) {
                        //plus one in the actual zone
                        this.getSubZones().get(actual).counterPlusOne();
                        //check the top/bottom x
                        if (this.getSamples().get(sample).getX() < this.getSubZones().get(actual).getxSuperior()) {
                            this.getSubZones().get(actual).setxSuperior(this.getSamples().get(sample).getX());
                        } else if (this.getSamples().get(sample).getX() > this.getSubZones().get(actual).getxInferior()) {
                            this.getSubZones().get(actual).setxInferior(this.getSamples().get(sample).getX());
                        }
                        //check the sides y
                        if (this.getSamples().get(sample).getY() < this.getSubZones().get(actual).getyIzquierda()) {
                            this.getSubZones().get(actual).setyIzquierda(this.getSamples().get(sample).getY());
                        } else if (this.getSamples().get(sample).getY() > this.getSubZones().get(actual).getyDerecha()) {
                            this.getSubZones().get(actual).setyDerecha(this.getSamples().get(sample).getY());
                        }
                        check = true;
                    }
                }
                if (check == false) {
                    this.getSubZones().add(new SubZone(this.getSamples().get(sample).getX(), this.getSamples().get(sample).getX(),
                            this.getSamples().get(sample).getY(), this.getSamples().get(sample).getY(),
                            this.getSamples().get(sample).getR(), this.getSamples().get(sample).getG(),
                            this.getSamples().get(sample).getB()));
                }
            }
        }
    }
};




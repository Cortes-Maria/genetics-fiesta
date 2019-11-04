import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Image {
    private BufferedImage bufImage = null;
    private int imageData[] = {};
    private ArrayList<Zone> zones;


    public Image(String pPath, int pZoneCant){
        loadImage(pPath);
        this.zones = new ArrayList<Zone>();
        creatingZones(pZoneCant);
    }

    public void creatingZones(int pZoneCantXY){
        for(int actualY = 0; actualY < pZoneCantXY; actualY++){
            for(int actualX = 0; actualX < pZoneCantXY;  actualX++){
                Zone newZone = new Zone(actualX*256,actualY*256);
                this.zones.add(newZone);
            }
        }
    }

    private void loadImage (String pPath){
        try
        {
            bufImage = ImageIO.read(new File(pPath));
            System.out.println("Image correctly charged! ");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param pPercent percent of the zone to be analyzed: must be multiple of 2
     */
    public void sampling(int pPercent){
        int times = pPercent / 2;
        int cantSamples = ((256*256)*2)/100;
        for(int perCent = 0; perCent<times; perCent++){ // for each percent
            for(int zoneAct = 0; zoneAct<this.zones.size(); zoneAct++){ // for each zone in the array
                boolean color = false;
                if (Math.random() < this.zones.get(zoneAct).getProbability()){
                    for(int count = 0; count < cantSamples; count++){
                        Sample newSample = Sample.getMuestra(this.zones.get(zoneAct).getX1() + (int)(Math.random()*255)
                                ,this.zones.get(zoneAct).getY1() + (int)(Math.random()*255),this.bufImage);
                        this.zones.get(zoneAct).getSamples().add(newSample);
                        if (newSample.r != 255 || newSample.g != 255 || newSample.b != 255)
                            color = true;
                    }
                }
                if(color == false){
                    this.zones.get(zoneAct).setProbability(this.zones.get(zoneAct).getProbability()-0.05);
                }
            }
        }
    }

    public void makeTarget(int pDistance){
        for(int zoneA = 0; zoneA < this.getZones().size(); zoneA ++){
            this.getZones().get(zoneA).groupSamples(pDistance); // this makes groups of samples and take the distance between them

            //get the total of samples to measure
            int total = 0;
            for(int subZoneAct = 0; subZoneAct < this.getZones().get(zoneA).getSubZones().size(); subZoneAct++){
                total += this.getZones().get(zoneA).getSubZones().get(subZoneAct).getcounter();
            }
            for(int subZoneAct = 0; subZoneAct < this.getZones().get(zoneA).getSubZones().size(); subZoneAct++){
                this.getZones().get(zoneA).getSubZones().get(subZoneAct).setPercent(
                        (this.getZones().get(zoneA).getSubZones().get(subZoneAct).getcounter()*100.00)/total);
            }
            System.out.println("Evaluando zona: " + zoneA);
            for(int subZoneAct = 0; subZoneAct < this.getZones().get(zoneA).getSubZones().size(); subZoneAct++){
                System.out.println("Porcentaje of " + subZoneAct + " is: " + this.getZones().get(zoneA).getSubZones().get(subZoneAct).getPercent());
            }

        }
    }

    public BufferedImage getBufImage() {
        return bufImage;
    }

    public void setBufImage(BufferedImage pNewImg) {
        this.bufImage =pNewImg;
    }

    public void setImageData(int[] imageData) {
        this.imageData = imageData;
    }

    public ArrayList<Zone> getZones() {
        return zones;
    }

    public void setZones(ArrayList<Zone> zones) {
        this.zones = zones;
    }




}
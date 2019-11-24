import java.util.ArrayList;
import java.util.HashMap;

public class Genetic {
    private ArrayList<Zone> zones;
    private ArrayList<HashMap<SubZone, Double>> targetTable;
    //private ArrayList<HashMap<ValueRange, SubRegion>> chromosomeTable; no sé qué es esto, es de los cromosomas (no sé qué es ValueRange)

    public Genetic(ArrayList<Zone> pZones){
        zones = pZones;

    }

    public ArrayList<Zone> getZones() {
        return zones;
    }

    public void setZones(ArrayList<Zone> zones) {
        this.zones = zones;
    }

    public ArrayList<HashMap<SubZone, Double>> getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(ArrayList<HashMap<SubZone, Double>> targetTable) {
        this.targetTable = targetTable;
    }

    public void fillTargetTable(){
        targetTable = new ArrayList<HashMap<SubZone, Double>>();
        for(Zone actualZone: zones){
            HashMap<SubZone,Double> percentageTable = new HashMap<>();
            for(SubZone actualSubZone: actualZone.getSubZones()){
                double subzonePercentage = actualSubZone.getPercent();
                percentageTable.put(actualSubZone,subzonePercentage);
            }
            targetTable.add(percentageTable);
        }
    }

}

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.HashMap;

public class Genetic {
    private ArrayList<Zone> zones;
    private ArrayList<HashMap<SubZone, Double>> targetTable;
    private ArrayList<HashMap<ValueRange, SubZone>> chromosomeTable; //ValueRange es un rango de valores
                                                                        // se utiliza para saber donde empiezan y donde terminan
                                                                        // el rango de cromosomas aptos

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

    private void generateChromosomaticRep(){
        chromosomeTable = new ArrayList<HashMap<ValueRange, SubZone>>();
        for(int actualZone = 0; actualZone < zones.size(); actualZone++) { // para cada objeto en las zonas actuales
            long lastChromosomeValue = 0;
            HashMap<SubZone, Double> targetDistribution = targetTable.get(actualZone); //se saca el target de la zona
            HashMap<ValueRange, SubZone> subRegionChromosomes = new HashMap<>(); //se crea el target Cromosomatico {a lo que se tienen que parecer todos}
            for (SubZone actualSubZone : targetDistribution.keySet()) { // para cada subZona de la zona actual
                double score = targetDistribution.get(actualSubZone); // saca el porcentage
                double chromosome = 4294967295L * score;
                long actualChromosome = lastChromosomeValue + (long) chromosome; // principio del conjunto
                subRegionChromosomes.put(ValueRange.of(lastChromosomeValue, actualChromosome), actualSubZone); // pone el rango como parte del target cromosomatico de la subZona
                lastChromosomeValue = lastChromosomeValue + 1; // este hace el final del conjunto
            }
            chromosomeTable.add(subRegionChromosomes); // agrega el target dde la zona al target general de cromosomas
        }
    }



}

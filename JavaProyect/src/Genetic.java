import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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

    private ArrayList<ArrayList<Long>> generateIndividuals(int pCuantity){  //Generador de poblacion//
        ArrayList<ArrayList<Long>> populations = new ArrayList<>();     //Un arraylist de arraylist de individuos
        for(Zone actualZone : zones){                                   //Para cada zona
            ArrayList<Long> populationInZone = new ArrayList<>();       //Se genera un arraylist de individuos
            for(int number = 0; number < pCuantity; number++){          //Para la cantidad de individuos de cada zona
                Long individual = (long)(Math.random()*4294967295L);    //Se genera un individuo
                populationInZone.add(individual);                       //Se agrega al array de individuos de la zona
            }
            populations.add(populationInZone);                      //Se agrega la zona al array de zonas
        }
        return populations;
    }

    private ValueRange getIndividualRange(long pIndivual, int region){
        for(ValueRange range : chromosomeTable.get(region).keySet()){
            if(range.isValidValue(pIndivual)){
                return range;
            }
        }
        return null;
    }

    private double getTargetPercentage(long individual, int region){                 //No la he tocado
        ValueRange range = getIndividualRange(individual, region);
        SubZone subRegion = chromosomeTable.get(region).get(range);
        return targetTable.get(region).get(subRegion);
    }

    private ArrayList<Long> getSimilarIndividuals(Long individual, int region, ArrayList<Long> population){ //No la he tocado
        ValueRange range = getIndividualRange(individual, region);
        ArrayList<Long> similarIndividuals = new ArrayList<>();
        for(Long currentIndividual : population){
            if(range.isValidValue(currentIndividual) && !currentIndividual.equals(individual)){
                similarIndividuals.add(currentIndividual);
            }
        }
        return similarIndividuals;
    }

    private boolean fitness(Long individual, int region, ArrayList<Long> population){    //No la he tocado
        ArrayList<Long> similarIndividuals = getSimilarIndividuals(individual, region, population);
        double currentPercentage = (double) similarIndividuals.size() / population.size();
        double targetPercentage = getTargetPercentage(individual, region);
        return currentPercentage < targetPercentage * 0.95;//IConstants.ACCEPTABILITY_PERCENTAGE;
    }

    private Long intersect(Long firstParent, Long secondParent) {//No la he tocado, esta claro que hace la interseccion, pero no entiendo bien como
        Random randomX = new Random();
        int intersectionPoint = randomX.nextInt(32);//IConstants.CHROMOSOMES_BITS); //
        long bitMask = 4294967295L;
        long child;
        long highBits;
        bitMask >>>= 32 - intersectionPoint;
        child = firstParent & bitMask;
        highBits = secondParent | bitMask;
        highBits >>>= 32 - intersectionPoint;
        highBits <<= 32 - intersectionPoint;
        child |= highBits;
        return child;
    }

    private Long mutate(Long individual){//No la he tocado , se que hace la mutación pero no entiendo bien como la hace
        Random random = new Random();
        if(random.nextDouble() <= 0.05){//IConstants.MUTATION_PERCENTAGE){
            long bitMask = 0x1; //It was IConstants.BIT_MASK
            int shiftAmount = random.nextInt(31);
            bitMask <<= shiftAmount;
            long bitValue = individual & bitMask;
            bitValue >>= shiftAmount;
            if(bitValue == 1) {
                bitMask = 4294967295L; //It was IConstants.MAX_LENGHT_VALUE // no se como llegaron a esa cifra
                bitMask -= Math.pow(2, shiftAmount);
                individual &= bitMask;
            }else{
                bitMask = 0x1; //It was IConstants.BIT_MASK
                bitMask <<= shiftAmount;
                individual |= bitMask;
            }
        }
        return individual;
    }

    public void processByGenerations(int pGenerations, int pInicialPopulation) {
        ArrayList<ArrayList<Long>> firstPopulation = generateIndividuals(pInicialPopulation);
        ArrayList<Long> fitIndividuals = new ArrayList<>();
        for(int epoch =  0; epoch < pGenerations; epoch++){                                     //For each generation
            for(int region = 0; region < firstPopulation.size(); region++){                     //For each region
                ArrayList<Long> regionPopulation = new ArrayList<>(firstPopulation.get(region));//Se obtinen la poblacion para no estar haciendo get's
                for(Long indivual : regionPopulation){                                          //Por cada individuo en la poblacion
                    if(fitness(indivual, region, firstPopulation.get(region))){                 //Se evalúa si es fit
                        fitIndividuals.add(indivual);                                           //SI => se annade a los fit
                    }
                    firstPopulation.get(region).remove(indivual);                               //SIEMPRE se elimina de la poblacion
                }
                int populationSize = 2 * regionPopulation.size();                               //Tamanno de poblacion = poblacion x2
                firstPopulation.get(region).clear();                                            //Limpia la memoria de la region
                Random randomN = new Random();
                for(int currentPopulationSize = 0; currentPopulationSize < populationSize; currentPopulationSize++){ //Esto hace que se duplique por cada vez que pasa una epoca
                    int firstParentIndex = randomN.nextInt(fitIndividuals.size());
                    int secondParentIndex = randomN.nextInt(fitIndividuals.size());     //Extrae dos parientes al azar
                    Long firstParent = fitIndividuals.get(firstParentIndex);
                    Long secondParent = fitIndividuals.get(secondParentIndex);          //Se extrae su rep.cromosomatica
                    Long child = intersect(firstParent, secondParent);                  //Se genera un hijo con los padres
                    firstPopulation.get(region).add(mutate(child));                     //Con base al hijo se anaden varios parecidos
                }
                fitIndividuals.clear();                      //Limpia los individuos fit preparandose para la prox. zona
            }
        }
    }

}

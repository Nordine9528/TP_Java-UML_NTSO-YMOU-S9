import Classes.Aeroport;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
import Classes.World;
import Classes.Interface;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //Aeroport CDG = new Aeroport("CDG", "Charles de Gaulle", "FR", 49.0097, 2.5479);
    //System.out.println(CDG);

    //World m = new World("src/data/airport-codes_no_comma.csv");
    //System.out.println("Found " + m.getList().size() + " airports.");

    World w1 = new World("src/data/airport-codes_no_comma.csv");
    System.out.println("Found " + w1.getList().size() + " airports.");
    Aeroport paris = w1.findNearestAirport(2.316, 48.866);
    Aeroport cdg = w1.findByCode("CDG");
    double distance = w1.distance(2.316, 48.866, paris.getLongitude(), paris.getLatitude());
    System.out.println(paris);
    System.out.println(distance);
    double distanceCDG = w1.distance(2.316, 48.866, cdg.getLongitude(), cdg.getLatitude());
    System.out.println(cdg);
    System.out.println(distanceCDG);

}

package Classes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class World {

    private ArrayList<Aeroport> list = new ArrayList<>();

    public World(String fileName) {
        try {
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();

            while (s != null) {
                s = s.replaceAll("\"", "");
                //Enleve les guillemets qui s´eparent les champs de donn´ees GPS.
                String fields[] = s.split(",");
                // Une bonne idée : placer un point d'arr^et ici pour du debuggage.

                if (fields[1].equals("large_airport")) {
                    String iata = fields[9];
                    String name = fields[2];
                    String country = fields[5];
                    double longitude = Double.parseDouble(fields[11]);
                    double latitude = Double.parseDouble(fields[12]);
                    list.add(new Aeroport(iata, name, country, latitude, longitude));
                }
                s = buf.readLine();
            }
        }
        catch (Exception e) {
            System.out.println("Maybe the file isn't there ?");
            System.out.println(list.get(list.size()-1));
            e.printStackTrace();
        }
    }

    public ArrayList<Aeroport> getList() {
        return list;
    }

    public double distance(double lon1, double lat1, double lon2, double lat2) {
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        double cos = Math.cos(Math.toRadians((lat2 + lat1) / 2));
        return dLat * dLat + (dLon * cos) * (dLon * cos);
    }

    public Aeroport findNearestAirport(double longitude, double latitude) {
        Aeroport nearest = list.get(0);
        double min = distance(longitude, latitude, nearest.getLongitude(), nearest.getLatitude());
        for (Aeroport a : list) {
            double d = distance(longitude, latitude, a.getLongitude(), a.getLatitude());
            if (d < min) {
                min = d;
                nearest = a;
            }
        }
        return nearest;
    }

    public Aeroport findByCode(String code) {
        for (Aeroport a : list) {
            if (a.getIATA().equals(code)) {
                return a;
            }
        }
        return null;
    }

}

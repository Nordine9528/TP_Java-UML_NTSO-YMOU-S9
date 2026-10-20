package Classes;

public class Aeroport {

    private String IATA;
    private String name;
    private String country;
    private double latitude;
    private double longitude;

    public Aeroport(String IATA, String name, String country, double latitude, double longitude){
        this.IATA = IATA;
        this.name =  name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getIATA() {
        return IATA;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString(){
        return "Aeroport [IATA=" + IATA + ", name=" + name + ", country=" + country + ", lat=" + latitude + ", lon=" + longitude + "]";
    }

    public double calculDistance(Aeroport a) {
        double dLat = a.latitude - this.latitude;
        double dLon = a.longitude - this.longitude;
        double cos = Math.cos(Math.toRadians((a.latitude + this.latitude) / 2));
        return dLat * dLat + (dLon * cos) * (dLon * cos);
    }

}

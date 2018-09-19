/**
 * Created by Matt on 14.09.2018 at 19:24.
 */
public enum Locations {
    KATOWICE("Katowice"),
    WISLA("Wisła"),
    CIESZYN("Cieszyn"),
    BIELSKO("Bielsko"),
    ZYWIEC("Żywiec");

    private String name;
    private float temperature;

    Locations(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }

    // Można enumerować po elementach przez stream:
   /* public static Stream<Locations> stream() {
        return Stream.of(Locations.values());
    }*/


}

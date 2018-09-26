package meteo.locations;

/**
 * Created by Matt on 14.09.2018 at 19:24.
 */
public enum Locations {
    ISTEBNA("Istebna", "496281"),
    WISLA("Wisła", "525053"),
    ZWARDON("Zwardoń", "529320"),
    BIELSKO("Bielsko", "486317"),
    ZYWIEC("Żywiec", "529462"),
    SZCZYRK("Szczyrk", "521739");

    private String name;
    private String yahooWoeid;

    Locations(String name) {
        this.name = name;
    }

    Locations(String name, String yahooWoeid) {
        this.name = name;
        this.yahooWoeid = yahooWoeid;
    }

    public String getName() {
        return name;
    }

    public String getYahooWoeid() {
        return yahooWoeid;
    }

    // Można enumerować po elementach przez stream:
   /* public static Stream<meteo.locations.Locations> stream() {
        return Stream.of(meteo.locations.Locations.values());
    }*/


}

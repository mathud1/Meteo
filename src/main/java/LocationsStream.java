/* To jest klasa testująca enumerację po elementach klasy Locations.
    Nie jest potrzebna dla funkcjonowania całości
    aplikacji */

/**
 * Created by Matt on 19.09.2018 at 17:57.
 */
public class LocationsStream {

    public static void main(String[] args) {

        //Można enumerować po elementach przez stream:
        /*
        Locations.stream()
                .forEach(System.out::println);
        */


        // Testowa enumeracja przez for each
        for (Locations location : Locations.values()) {
            System.out.println(location.getTemperature());
        }
    }
}

package agh.ics.oop;
import static agh.ics.oop.OptionParser.parse;

// 10.
// * rozwiazanie prostsze - wystarczy zaimplementowac metode ktora dla danej listy zwierzat
// sprawdza czy ktores z pozostalych przypadkiem nie stoi na miejscu na ktore
// chcemy sie ruszyc
// ** szybsze rozwiazanie - stworzyc HashMape (klucz, wartosc) -> (pozycja, zwierze) i sprawdzac czy dany klucz istnieje
// (pozycja na ktora chcemy sie ruszyc)

public class World {
    public static void main(String[] args) {

        var animal = new Animal();
        var directions = parse(args);
        System.out.println("Initial position: " + animal);
        animal.move(directions);
        System.out.println("\nFinal position: " + animal);
    }
}
package UNO;

// ArrayList zum Speichern der gespielten Karten
import java.util.ArrayList;

public class GespielteKarten {

    // Liste, die alle gespielten Karten enthält
    // Die letzte Karte ist die oberste Karte
    private ArrayList<Karte> stapel;

    // Konstruktor: erstellt einen leeren Ablagestapel
    public GespielteKarten() {
        stapel = new ArrayList<>();
    }

    // Fügt eine Karte zum Ablagestapel hinzu
    // (entspricht dem Ablegen einer Karte im Spiel)
    public void karteHinzufügen(Karte karte) {
        stapel.add(karte);
    }

    // Gibt die oberste Karte des Ablagestapels zurück
    public Karte obersteKarte() {

        // Prüfen, ob bereits Karten gespielt wurden
        if (!stapel.isEmpty()) {

            // Letzte Karte der Liste ist die oberste
            return stapel.get(stapel.size() - 1);
        }

        // Falls noch keine Karte gespielt wurde
        return null;
    }

}
package UNO;

// ArrayList = dynamische Liste für Karten
import java.util.ArrayList;

// Collections = Hilfsklasse, z.B. zum Mischen der Karten
import java.util.Collections;

public class Kartendeck {

    // Liste, die alle Karten im Deck speichert
    private ArrayList<Karte> karten;

    // Konstruktor: wird automatisch aufgerufen,
    // wenn ein neues Kartendeck erzeugt wird
    public Kartendeck() {
        // Leere Kartenliste erstellen
        karten = new ArrayList<>();

        // Alle UNO-Karten zum Deck hinzufügen
        erstelleDeck();
    }

    // Erstellt ein UNO-Kartendeck
    // → Jede Karte kommt nur EINMAL vor
    // → Joker kommen VIERMAL vor
    private void erstelleDeck() {

        // Farben der normalen UNO-Karten
        String[] farben = {"rot", "blau", "grün", "gelb"};

        // Zahlenkarten von 0 bis 9
        String[] zahlen = {"0","1","2","3","4","5","6","7","8","9"};

        // Spezialeffekte (ohne Joker)
        String[] spezial = {"+2", "aussetzen", "inverser"};

        // ----------------------------
        // Zahlenkarten erzeugen
        // (jede nur einmal pro Farbe)
        // ----------------------------
        for (String farbe : farben) {
            for (String zahl : zahlen) {

                // Jede Zahlenkarte genau einmal hinzufügen
                karten.add(new Karte(farbe, zahl));
            }
        }

        // ----------------------------
        // Spezielle Karten erzeugen
        // (jede nur einmal pro Farbe)
        // ----------------------------
        for (String farbe : farben) {
            for (String effekt : spezial) {

                // Jede Spezialkarte genau einmal hinzufügen
                karten.add(new SpezielleKarte(farbe, effekt));
            }
        }

        // ----------------------------
        // Joker-Karten erzeugen
        // (viermal, wie im echten UNO)
        // ----------------------------
        for (int i = 0; i < 4; i++) {

            // Joker haben die Farbe "joker"
            karten.add(new SpezielleKarte("joker", "joker"));
        }
    }

    // Mischt das Kartendeck zufällig
    public void mischen() {
        Collections.shuffle(karten);
    }

    // Zieht die oberste Karte vom Deck
    public Karte kartenEntfernen() {

        // Prüfen, ob noch Karten im Deck sind
        if (!karten.isEmpty()) {

            // Letzte Karte entfernen und zurückgeben
            return karten.remove(karten.size() - 1);
        }

        // Falls keine Karten mehr vorhanden sind
        return null;
    }

}
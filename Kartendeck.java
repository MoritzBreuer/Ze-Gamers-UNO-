package UNO;

import java.util.ArrayList;
import java.util.Collections;

public class Kartendeck {

    // Liste, die alle Karten im Deck speichert
    private ArrayList<UNOKarte> karten;

    // Konstruktor
    public Kartendeck() {
        karten = new ArrayList<>();
        erstelleDeck();
    }

    // Erstellt ein UNO-Kartendeck
    private void erstelleDeck() {

        String[] farben = {"rot", "blau", "grün", "gelb"};
        String[] zahlen = {"0","1","2","3","4","5","6","7","8","9"};
        String[] spezial = {"+2", "aussetzen", "inverser"};

        // Zahlenkarten
        for (String farbe : farben) {
            for (String zahl : zahlen) {
                karten.add(new UNOKarte(farbe, zahl));
            }
        }

        // Spezielle Karten
        for (String farbe : farben) {
            for (String effekt : spezial) {
                karten.add(new SpezielleKarte(farbe, effekt));
            }
        }

        // Joker (4 Stück)
        for (int i = 0; i < 4; i++) {
            karten.add(new SpezielleKarte("schwarz", "joker"));
        }
    }

    // Mischt das Kartendeck
    public void mischen() {
        Collections.shuffle(karten);
    }

    // Zieht die oberste Karte
    public UNOKarte kartenEntfernen() {

        if (!karten.isEmpty()) {
            return karten.remove(karten.size() - 1);
        }

        return null;
    }
}
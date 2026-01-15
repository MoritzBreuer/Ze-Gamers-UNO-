package UNO;

import java.util.ArrayList;

public class GespielteKarten {

    // Ablagestapel
    private ArrayList<UNOKarte> stapel;

    // Konstruktor
    public GespielteKarten() {
        stapel = new ArrayList<>();
    }

    // Karte ablegen
    public void karteHinzufügen(UNOKarte karte) {
        stapel.add(karte);
    }

    // Oberste Karte zurückgeben
    public UNOKarte obersteKarte() {

        if (!stapel.isEmpty()) {
            return stapel.get(stapel.size() - 1);
        }

        return null;
    }
}
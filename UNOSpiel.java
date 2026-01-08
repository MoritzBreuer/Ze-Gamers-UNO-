package UNO;

import java.util.ArrayList;

public class UNOSpiel {

    private Kartendeck kartendeck;
    private GespielteKarten gespielteKarten;

    private ArrayList<UNOSpieler> spieler;
    private int aktuellerSpielerIndex;
    private int richtung; // 1 = vorwärts, -1 = rückwärts

    // aktuell gültige Farbe (wichtig für Joker)
    private String aktuelleFarbe;

    // ===============================
    // Konstruktor
    // ===============================
    public UNOSpiel() {
        kartendeck = new Kartendeck();
        gespielteKarten = new GespielteKarten();
        spieler = new ArrayList<>();

        aktuellerSpielerIndex = 0;
        richtung = 1;

        kartendeck.mischen();
    }

    // ===============================
    // Getter
    // ===============================
    public Kartendeck getKartendeck() {
        return kartendeck;
    }

    public GespielteKarten getGespielteKarten() {
        return gespielteKarten;
    }

    // ===============================
    // Spieler hinzufügen
    // ===============================
    public void spielerHinzufuegen(UNOSpieler s) {
        spieler.add(s);
    }

    // ===============================
    // Karten verteilen + Startkarte
    // ===============================
    public void kartenVerteilen() {

        for (UNOSpieler s : spieler) {
            for (int i = 0; i < 7; i++) {
                s.getKartenAufHand()
                 .karteHinzufuegen(kartendeck.kartenEntfernen());
            }
        }

        Karte start = kartendeck.kartenEntfernen();
       _attachStartkarte(start);
    }

    private void _attachStartkarte(Karte start) {
        gespielteKarten.karteHinzufügen(start);
        aktuelleFarbe = start.getFarbe();
    }

    // ===============================
    // Aktueller Spieler
    // ===============================
    public UNOSpieler getAktuellerSpieler() {
        return spieler.get(aktuellerSpielerIndex);
    }

    // ===============================
    // Prüfen, ob Karte spielbar ist
    // ===============================
    public boolean kartenKontrollieren(Karte karte) {

        Karte oben = gespielteKarten.obersteKarte();

        if (oben == null) {
            return true;
        }

        // Joker immer spielbar
        if (karte.getFarbe().equals("joker")) {
            return true;
        }

        return karte.getFarbe().equals(aktuelleFarbe)
                || karte.getWert().equals(oben.getWert());
    }

    // ===============================
    // Karte spielen
    // ===============================
    public void karteSpielen(Karte karte) {

        if (!kartenKontrollieren(karte)) {
            System.out.println("Diese Karte kann nicht gespielt werden!");
            return;
        }

        UNOSpieler spieler = getAktuellerSpieler();

        // Karte ablegen
        gespielteKarten.karteHinzufügen(karte);
        spieler.getKartenAufHand().karteEntfernen(karte);

        System.out.println(spieler.getName() + " spielt: " + karte);

        // ===============================
        // Joker → Farbe wählen
        // ===============================
        if (karte instanceof SpezielleKarte
                && karte.getWert().equals("joker")) {

            String neueFarbe = spieler.farbeAuswaehlen();
            aktuelleFarbe = neueFarbe;
            System.out.println("Neue Farbe ist: " + neueFarbe);

        } else {
            aktuelleFarbe = karte.getFarbe();
        }

        // ===============================
        // Spezialeffekte ausführen
        // ===============================
        if (karte instanceof SpezielleKarte) {
            ((SpezielleKarte) karte).effektAnwenden(this);
        }

        spieler.unoSagen();
        naechsterSpieler();
    }

    // ===============================
    // Spielerwechsel
    // ===============================
    public void naechsterSpieler() {
        aktuellerSpielerIndex =
                (aktuellerSpielerIndex + richtung + spieler.size())
                        % spieler.size();
    }

    // ===============================
    // Spezialeffekte
    // ===============================
    public void richtungUmdrehen() {
        richtung *= -1;
        System.out.println("🔄 Spielrichtung wurde geändert!");
    }

    public void spielerUeberspringen() {
        naechsterSpieler();
        System.out.println("⏭ Ein Spieler wurde übersprungen!");
    }

    public void getNaechsterSpielerzieheKarte(int anzahl) {

        int naechsterIndex =
                (aktuellerSpielerIndex + richtung + spieler.size())
                        % spieler.size();

        UNOSpieler naechster = spieler.get(naechsterIndex);

        System.out.println(naechster.getName()
                + " muss " + anzahl + " Karten ziehen!");

        for (int i = 0; i < anzahl; i++) {
            Karte k = kartendeck.kartenEntfernen();
            if (k != null) {
                naechster.getKartenAufHand().karteHinzufuegen(k);
            }
        }

        // Zug des betroffenen Spielers überspringen
        aktuellerSpielerIndex = naechsterIndex;
        naechsterSpieler();
    }

    // ===============================
    // Spielende
    // ===============================
    public boolean spielBeendet() {
        for (UNOSpieler s : spieler) {
            if (s.getKartenAufHand().anzahlKarten() == 0) {
                System.out.println("Gewinner: " + s.getName());
                return true;
            }
        }
        return false;
    }
}

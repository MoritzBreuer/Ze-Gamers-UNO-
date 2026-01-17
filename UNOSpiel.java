package UNO;

import java.util.ArrayList;

public class UNOSpiel {

    private Kartendeck kartendeck;
    private GespielteKarten gespielteKarten;

    private ArrayList<UNOSpieler> spielerListe;
    private int aktuellerSpielerIndex;
    private int richtung; // 1 = vorwärts, -1 = rückwärts

    private String aktuelleFarbe;

    // ===============================
    // Konstruktor
    // ===============================
    public UNOSpiel() {
        kartendeck = new Kartendeck();
        gespielteKarten = new GespielteKarten();
        spielerListe = new ArrayList<>();
        aktuellerSpielerIndex = 0;
        richtung = 1;

        kartendeck.mischen();
    }

    // ===============================
    // Getter
    // ===============================
    public String getAktuelleFarbe() {
        return aktuelleFarbe;
    }

    public Kartendeck getKartendeck() {
        return kartendeck;
    }

    public GespielteKarten getGespielteKarten() {
        return gespielteKarten;
    }

    public UNOSpieler getAktuellerSpieler() {
        return spielerListe.get(aktuellerSpielerIndex);
    }

    // ===============================
    // Spieler hinzufügen
    // ===============================
    public void spielerHinzufuegen(UNOSpieler neuerSpieler) {
        spielerListe.add(neuerSpieler);
    }

    // ===============================
    // Karten verteilen + Startkarte
    // ===============================
    public void kartenVerteilen() {

        for (UNOSpieler spieler : spielerListe) {
            for (int i = 0; i < 7; i++) {
                spieler.getKartenAufHand()
                        .karteHinzufuegen(kartendeck.kartenEntfernen());
            }
        }

        UNOKarte startKarte = kartendeck.kartenEntfernen();
        gespielteKarten.karteHinzufügen(startKarte);
        aktuelleFarbe = startKarte.getFarbe();
    }

    // ===============================
    // Karte spielen
    // ===============================
    public void karteSpielen(UNOKarte gespielteKarte) {

        UNOSpieler aktuellerSpieler = getAktuellerSpieler();

        // Karte ablegen
        gespielteKarten.karteHinzufügen(gespielteKarte);
        aktuellerSpieler.getKartenAufHand()
                .karteEntfernen(gespielteKarte);

        System.out.println(aktuellerSpieler.getName()
                + " spielt: " + gespielteKarte);

        // ===============================
        // Farbe festlegen
        // ===============================
        if (gespielteKarte instanceof SpezielleKarte
                && gespielteKarte.getWert().equals("joker")) {

            String neueFarbe = aktuellerSpieler.farbeAuswaehlen();
            aktuelleFarbe = neueFarbe;
            System.out.println("Neue Farbe ist: " + neueFarbe);

        } else {
            aktuelleFarbe = gespielteKarte.getFarbe();
        }

        // ===============================
        // Spezialeffekte anwenden
        // ===============================
        if (gespielteKarte instanceof SpezielleKarte) {
            ((SpezielleKarte) gespielteKarte).effektAnwenden(this);
        }

        aktuellerSpieler.unoSagen();
        naechsterSpieler();
    }

    // ===============================
    // Spielersteuerung
    // ===============================
    public void naechsterSpieler() {
        aktuellerSpielerIndex = (aktuellerSpielerIndex + richtung + spielerListe.size())
                % spielerListe.size();
    }

    public void richtungUmdrehen() {
        richtung *= -1;
        System.out.println("Spielrichtung wurde geändert!");
    }

    public void spielerUeberspringen() {
        naechsterSpieler();
        System.out.println("Ein Spieler wurde übersprungen!");
    }

    public void getNaechsterSpielerzieheKarte(int anzahl) {

        int indexNaechsterSpieler = (aktuellerSpielerIndex + richtung + spielerListe.size())
                % spielerListe.size();

        UNOSpieler naechsterSpieler = spielerListe.get(indexNaechsterSpieler);

        for (int i = 0; i < anzahl; i++) {
            naechsterSpieler.getKartenAufHand()
                    .karteHinzufuegen(kartendeck.kartenEntfernen());
        }

        System.out.println(naechsterSpieler.getName()
                + " zieht " + anzahl + " Karten!");
    }

        public void spielerZiehtKarte() {

        UNOSpieler aktuellerSpieler = getAktuellerSpieler();
        UNOKarte gezogen = kartendeck.kartenEntfernen();

        aktuellerSpieler.getKartenAufHand()
                .karteHinzufuegen(gezogen);

        System.out.println(aktuellerSpieler.getName()
                + " zieht die Karte: " + gezogen);

        naechsterSpieler();
    }

    // ===============================
    // Spielende
    // ===============================
    public boolean spielBeendet() {
        for (UNOSpieler spieler : spielerListe) {
            if (spieler.getKartenAufHand().anzahlKarten() == 0) {
                System.out.println("Gewinner: " + spieler.getName());
                return true;
            }
        }
        return false;
    }
}


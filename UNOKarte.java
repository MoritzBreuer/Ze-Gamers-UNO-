package UNO;

public class UNOKarte {

    // Farbe der Karte (rot, blau, grün, gelb)
    private String farbe;

    // Wert der Karte (0–9, +2, aussetzen, richtungswechsel, etc.)
    private String wert;

    // Konstruktor: erzeugt eine Karte mit Farbe und Wert
    public UNOKarte(String farbe, String wert) {
        this.farbe = farbe;
        this.wert = wert;
    }

    // Gibt die Farbe der Karte zurück
    public String getFarbe() {
        return farbe;
    }

    // Gibt den Wert der Karte zurück
    public String getWert() {
        return wert;
    }

    // Prüft, ob diese Karte auf die oberste Karte gelegt werden darf
    public boolean kannGespieltWerden(UNOKarte obersteKarte) {

        // gleiche Farbe → spielbar
        // gleicher Wert → spielbar
        // Joker → immer spielbar
        return this.farbe.equals(obersteKarte.farbe)
                || this.wert.equals(obersteKarte.wert)
                || this.farbe.equals("joker");
    }

    // Bestimmt, wie die Karte als Text angezeigt wird
    @Override
    public String toString() {
        return farbe + " " + wert;
    }
}



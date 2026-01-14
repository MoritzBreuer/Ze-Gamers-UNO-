package UNO;

public class SpezielleKarte extends UNOKarte {

    // Art des Effekts: "+2", "inverser", "aussetzen", "joker"
    private String effektTyp;

    // Konstruktor für spezielle Karten
    public SpezielleKarte(String farbe, String effektTyp) {
        // Übergibt Farbe und Effekt als Wert an die Oberklasse Karte
        super(farbe, effektTyp);
        this.effektTyp = effektTyp;
    }

    // Führt den Effekt der speziellen Karte aus
    public void effektAnwenden(UNOSpiel spiel) {

        switch (effektTyp) {

            case "+2":
                // Nächster Spieler zieht 2 Karten
               spiel.getNaechsterSpielerzieheKarte(2);
                break;

            case "inverser":
                // Spielrichtung wird umgedreht
                spiel.richtungUmdrehen();
                break;

            case "aussetzen":
                // Nächster Spieler wird übersprungen
                spiel.spielerUeberspringen();
                break;

            case "joker":
                // Farbe darf gewählt werden (hier nur Ausgabe)
                System.out.println("Neue Farbe wählen!");
                break;

            default:
                // Falls ein unbekannter Effekt auftritt
                System.out.println("Unbekannter Spezialeffekt!");
        }
    }
}




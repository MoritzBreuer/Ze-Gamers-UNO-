package UNO;

import java.util.Scanner;

public class UNOSpieler {

    private String name;
    private Handkarten kartenAufHand;

    public UNOSpieler(String name) {
        this.name = name;
        this.kartenAufHand = new Handkarten();
    }

    public String getName() {
        return name;
    }

    public Handkarten getKartenAufHand() {
        return kartenAufHand;
    }

    // ===============================
    // Spieler sagt UNO
    // ===============================
    public void unoSagen() {
        if (kartenAufHand.anzahlKarten() == 1) {
            System.out.println(name + " sagt: UNO!");
        }
    }

    // ===============================
    // 🔥 NEU: Farbe für Joker auswählen
    // ===============================
    public String farbeAuswaehlen() {

        Scanner scanner = new Scanner(System.in);
        String farbe;

        while (true) {
            System.out.print(name +
                ", wähle eine Farbe (rot/blau/grün/gelb): ");
            farbe = scanner.nextLine().toLowerCase();

            if (farbe.equals("rot")
                    || farbe.equals("blau")
                    || farbe.equals("grün")
                    || farbe.equals("gelb")) {
                return farbe;
            }

            System.out.println("Ungültige Farbe!");
        }
    }
}

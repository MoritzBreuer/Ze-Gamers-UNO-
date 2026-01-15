package UNO;

import java.util.Scanner;

public class UNOSpieler {

    private String name;
    private HandvonSpieler kartenAufHand;

    public UNOSpieler(String name) {
        this.name = name;
        this.kartenAufHand = new HandvonSpieler();
    }

    public String getName() {
        return name;
    }

    public HandvonSpieler getKartenAufHand() {
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
    // Farbe für Joker auswählen
    // ===============================
    public String farbeAuswaehlen() {

        Scanner scanner = new Scanner(System.in);
        String farbe;

        while (true) {
            System.out.print(name +
                ", wähle eine Farbe (rot/blau/gruen/gelb): ");
            farbe = scanner.nextLine().toLowerCase();

            if (farbe.equals("rot")
                    || farbe.equals("blau")
                    || farbe.equals("gruen")
                    || farbe.equals("gelb")) {
                return farbe;
            }

            System.out.println("Ungültige Farbe!");
        }
    }
}

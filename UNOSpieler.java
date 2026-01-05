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

    // Spieler wählt eine Farbe (z.B. bei Farbwechselkarte)
    public Farbe farbeAuswaehlen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + ", wähle eine Farbe (ROT, GELB, GRUEN, BLAU):");

        while (true) {
            try {
                return Farbe.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Ungültige Farbe, bitte erneut eingeben:");
            }
        }
    }

    // Spieler sagt UNO
    public void unoSagen() {
        if (kartenAufHand.anzahlKarten() == 1) {
            System.out.println(name + " sagt: UNO!");
        }
    }
}

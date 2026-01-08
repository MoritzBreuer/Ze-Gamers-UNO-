package UNO;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UNOSpiel spiel = new UNOSpiel();

        // Spieler erstellen
        System.out.print("Name Spieler 1: ");
        UNOSpieler s1 = new UNOSpieler(scanner.nextLine());

        System.out.print("Name Spieler 2: ");
        UNOSpieler s2 = new UNOSpieler(scanner.nextLine());

        spiel.spielerHinzufuegen(s1);
        spiel.spielerHinzufuegen(s2);

        spiel.kartenVerteilen();

        System.out.println("UNO-Spiel gestartet!");
        System.out.println("-----------------------");

        // Haupt-Spielschleife
        while (!spiel.spielBeendet()) {

            UNOSpieler spieler = spiel.getAktuellerSpieler();

            System.out.println("\n======================");
            System.out.println("Am Zug: " + spieler.getName());

            // Oberste Karte anzeigen
            Karte oben = spiel.getGespielteKarten().obersteKarte();
            System.out.println("Oberste Karte: " + oben);

            // Handkarten anzeigen
            System.out.println("\nDeine Karten:");
            for (int i = 0; i < spieler.getKartenAufHand().anzahlKarten(); i++) {
                System.out.println(i + ": "
                        + spieler.getKartenAufHand().getKarte(i));
            }

            System.out.println("\nWähle Karte (Nummer) oder -1 zum Ziehen:");
            int wahl = scanner.nextInt();

            if (wahl == -1) {
                Karte gezogen = spiel.getKartendeck().kartenEntfernen();
                spieler.getKartenAufHand().karteHinzufuegen(gezogen);
                System.out.println("Du ziehst: " + gezogen);
                spiel.naechsterSpieler();
                continue;
            }

            if (wahl < 0 || wahl >= spieler.getKartenAufHand().anzahlKarten()) {
                System.out.println("Ungültige Eingabe!");
                continue;
            }

            Karte karte = spieler.getKartenAufHand().getKarte(wahl);

            if (spiel.kartenKontrollieren(karte)) {
                spiel.karteSpielen(karte);
            } else {
                System.out.println("Diese Karte kann nicht gespielt werden!");
            }
        }

        scanner.close();
    }
}

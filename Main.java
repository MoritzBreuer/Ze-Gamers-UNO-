package UNO;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UNOSpiel spiel = new UNOSpiel();

        // Anzahl der Spieler abfragen (2–6 erlaubt)
        // int anzahlSpieler;

        /*
         * do {
         * System.out.print("Wie viele Spieler? (2 - 4): ");
         * anzahlSpieler = scanner.nextInt();
         * scanner.nextLine();
         * } while (anzahlSpieler < 2 || anzahlSpieler > 4);
         * 
         * // Spieler dynamisch erstellen
         * for (int i = 1; i <= anzahlSpieler; i++) {
         * System.out.print("Name Spieler " + i + ": ");
         * UNOSpieler spieler = new UNOSpieler(scanner.nextLine());
         * spiel.spielerHinzufuegen(spieler);
         * }
         */

        System.out.print("Name Spieler 1: ");
        UNOSpieler spieler1 = new UNOSpieler(scanner.nextLine());
        System.out.print("Name Spieler 2: ");
        UNOSpieler spieler2 = new UNOSpieler(scanner.nextLine());
        spiel.spielerHinzufuegen(spieler1);
        spiel.spielerHinzufuegen(spieler2);

        spiel.kartenVerteilen();

        System.out.println("UNO-Spiel gestartet!");
        System.out.println("-----------------------");

        // Haupt-Spielschleife
        while (!spiel.spielBeendet()) {

            UNOSpieler spieler = spiel.getAktuellerSpieler();

            System.out.println("\n======================");
            System.out.println("Am Zug: " + spieler.getName());

            // Oberste Karte anzeigen
            UNOKarte oben = spiel.getGespielteKarten().obersteKarte();
            System.out.println("Oberste Karte: " + oben);

            // HandvonSpieler anzeigen
            System.out.println("\nDeine Karten:");
            for (int i = 0; i < spieler.getKartenAufHand().anzahlKarten(); i++) {
                System.out.println(i + ": "
                        + spieler.getKartenAufHand().getKarte(i));
            }

            System.out.println("\nWähle Karte (Nummer) oder -1 zum Ziehen:");
            int wahl = scanner.nextInt();

            if (wahl == -1) {
                spiel.spielerZiehtKarte();
                continue;
            }

            if (wahl < 0 || wahl >= spieler.getKartenAufHand().anzahlKarten()) {
                System.out.println("Ungültige Eingabe!");
                continue;
            }

            UNOKarte karte = spieler.getKartenAufHand().getKarte(wahl);

            if (karte.kannGespieltWerden(
                    spiel.getGespielteKarten().obersteKarte(),
                    spiel.getAktuelleFarbe())) {

                spiel.karteSpielen(karte);
            } else {
                System.out.println("Diese Karte kann nicht gespielt werden!");
            }
        }

        scanner.close();
    }
}


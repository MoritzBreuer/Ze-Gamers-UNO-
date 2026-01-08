package UNO;

import java.util.ArrayList;

public class HandvonSpieler {

    private ArrayList<UNOKarte> stapel;

    public HandvonSpieler() {
        this.stapel = new ArrayList<>();
    }

    public void karteHinzufuegen(UNOKarte karte) {
        stapel.add(karte);
    }

    public void karteEntfernen(UNOKarte karte) {
        stapel.remove(karte);
    }

    public int anzahlKarten() {
        return stapel.size();
    }

    public UNOKarte getKarte(int index) {
        if (index >= 0 && index < stapel.size()) {
            return stapel.get(index);
        }
        return null;
    }
}

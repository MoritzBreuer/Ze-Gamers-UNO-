import java.util.ArrayList;

public class Handkarten {

    private ArrayList<UNOKarte> karten;

    public Handkarten() {
        this.karten = new ArrayList<>();
    }

    public void karteHinzufuegen(UNOKarte karte) {
        karten.add(karte);
    }

    public void karteEntfernen(UNOKarte karte) {
        karten.remove(karte);
    }

    public int anzahlKarten() {
        return karten.size();
    }

    public UNOKarte getKarte(int index) {
        if (index >= 0 && index < karten.size()) {
            return karten.get(index);
        }
        return null;
    }
}

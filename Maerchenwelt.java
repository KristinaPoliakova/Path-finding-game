import java.util.ArrayList;
import java.util.Random;

public class Maerchenwelt {
    private int x;
    private int y;
    private VerwunschenerWald[][] karte;
    private Oma oma;
    private Rotkaeppchen rotkaeppchen;
    private Wolf wolf;
    private Gefahr gefahr;

    //Konstruktor, erstellt das Waldstuech und platziert 1 Rotkaepchen, 1 Oma, 1 Wolf, die uebergebene Zahl an Bauemen
    // und die uebergebene Zahl an Gefahren an zufaelligen Stellen auf der Karte

    public Maerchenwelt(int x, int y, int gefahrenAnzahl, int baumAnzahl)
        throws IllegalArgumentException {
        //die Karte soll nicht kleiner als 10x10 sein
        if (x < 10 || y < 10) {
            throw new IllegalArgumentException("Vergroessern Sie den verwunschenen Wald.");
        }
        else {
            this.x = x;
            this.y = y;
            karte = new VerwunschenerWald[x][y];
            Position RotkaepchenAnfang = new Position(0, 0);
            rotkaeppchen = new Rotkaeppchen(RotkaepchenAnfang);
            karte[0][0] = rotkaeppchen;
            int tatsaechlicheZahlVonOmas = 0;
            while (tatsaechlicheZahlVonOmas < 1) {
                Random randomOma = new Random();
                int randomOmaX = x - 8 + randomOma.nextInt((x - 1) - (x - 8) + 1);
                int randomOmaY = y - 8 + randomOma.nextInt((y - 1) - (y - 8) + 1);
                if (karte[randomOmaX][randomOmaY] == null) {
                    Position OmaPosition = new Position(randomOmaX, randomOmaY);
                    oma = new Oma(OmaPosition);
                    karte[randomOmaX][randomOmaY] = oma;
                    tatsaechlicheZahlVonOmas++;
                }
            }
            int tatsaechlicheZahlDerWoelfe = 0;
            while (tatsaechlicheZahlDerWoelfe < 1) {
                Random randomWolf = new Random();
                int randomWolfX = randomWolf.nextInt(x - 1);
                int randomWolfY = randomWolf.nextInt(y - 1);
                if (karte[randomWolfX][randomWolfY] == null) {
                    Position erzeugtesWolf = new Position(randomWolfX, randomWolfY);
                    wolf = new Wolf(erzeugtesWolf);
                    karte[randomWolfX][randomWolfY] = wolf;
                    tatsaechlicheZahlDerWoelfe++;
                }
            }
            if ((gefahrenAnzahl + baumAnzahl) > ((x * y) - 3)) {
                // die Zahl an Bauemen + Gefahren soll kleiner sein als (breitex x Höhey) - 3
                throw new IllegalArgumentException("Reduzieren Sie die Anzahl der Baueme und Gefahren.");
            }
            else {
                int tatsaechlicheZahlDerGefahren = 0;
                while (tatsaechlicheZahlDerGefahren < gefahrenAnzahl) {
                    Random randomGefahr = new Random();
                    int randomGefahrX = randomGefahr.nextInt(x - 1);
                    int randomGefahrY = randomGefahr.nextInt(y - 1);
                    if (karte[randomGefahrX][randomGefahrY] == null) {
                        Position erzeugtesGefahr = new Position(randomGefahrX, randomGefahrY);
                        gefahr = new Gefahr(erzeugtesGefahr);
                        karte[randomGefahrX][randomGefahrY] = gefahr;
                        tatsaechlicheZahlDerGefahren++;
                    }
                }
            }
            erzeugeBaueme(gefahrenAnzahl, baumAnzahl);
        }
    }

    public VerwunschenerWald[][] getKarte() {
        return karte;
    }

    public Oma getOma() {
        return oma;
    }

    public Rotkaeppchen getRotkaeppchen() {
        return rotkaeppchen;
    }

    //diese Methode erzeugt die uebergebene Zahl an Baeumen an zufaelligen Stellen auf der Karte;
    public void erzeugeBaueme(int gefahrenAnzahl, int baumAnzahl)
            throws IllegalArgumentException {
        // die Zahl an Bauemen + Gefahren soll kleiner sein als (breitex x Höhey) - 3
        if (gefahrenAnzahl + baumAnzahl > ((x * y) - 3)) {
            throw new IllegalArgumentException("Reduzieren Sie die Anzahl der Baueme und Gefahren.");
        } else {
            int tatsaechlicheZahlDerBaueme = 0;
            while (tatsaechlicheZahlDerBaueme < baumAnzahl) {
                Random randomBaum = new Random();
                int randomBaumX = randomBaum.nextInt(x - 1);
                int randomBaumY = randomBaum.nextInt(y - 1);
                if (karte[randomBaumX][randomBaumY] == null) {
                    Position erzeugtesBaum = new Position(randomBaumX, randomBaumY);
                    Baum baum = new Baum(erzeugtesBaum);
                    karte[randomBaumX][randomBaumY] = baum;
                    tatsaechlicheZahlDerBaueme++;
                }
            }
        }
    }
    // Diese Methode generiert zufaellige Schritte ausgehend von der Startposition von Rotkaepchen zu der
    // uebergebenen Zielposition (Oma oder zurueck zu der Startposition). Die Zahl der Schritte soll nicht
    // 500 Schritte ueberschreiten.
    public ArrayList<Position> wegFinden(Position ziel) {
        ArrayList<Position> listeAllerPositionen = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            if (!(rotkaeppchen.getPosition().equals(ziel))) {
                Random randomeBewegung = new Random();
                int RichtungDerBewegung = randomeBewegung.nextInt(4);

                // die zufaellieg Schritte werden nach folgender Schema definiert:
                // es wird zufaelliege Zahl im Bereich von 0 bis 3 inklusiv generiert
                // Falls die Zahl 0 ist -> gehe hoch;
                // Falls die Zahl 1 ist -> gehe runter;
                // Falls die Zahl 2 ist -> gehe links;
                // Falls die Zahl 3 ist -> gehe rechts;
                // dabei werden die Randbedingungen beruecksichtigt.

                switch (RichtungDerBewegung) {
                    case 0:
                        if (rotkaeppchen.getPosition().getY() > 0) {
                            if (karte[rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY() - 1] == null || !(karte[rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY() - 1].getName().equals("B"))) {
                                Position altePositionDerRotkaeppchen = new Position(rotkaeppchen.getPosition().getX(), rotkaeppchen.getPosition().getY());
                                listeAllerPositionen.add(altePositionDerRotkaeppchen);
                                rotkaeppchen.geheHoch();
                                if (rotkaeppchen.getPosition().equals(wolf.getPosition())) {
                                    rotkaeppchen.gesundheitVerringern(wolf.schaden);
                                    if (!(rotkaeppchen.istNochLebendig())) {
                                        return listeAllerPositionen;
                                    }
                                }
                                else if (karte[rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY()] != null && karte[rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY()].getName().equals("G")) {
                                    rotkaeppchen.gesundheitVerringern(gefahr.schaden);
                                    if (!(rotkaeppchen.istNochLebendig())) {
                                        return listeAllerPositionen;
                                    }
                                else {
                                    break;
                                    }
                                }
                            }
                        }


                    case 1:
                        if (rotkaeppchen.getPosition().getY() != y - 1) {
                            if (karte[rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY() + 1] == null || !(karte[rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY() + 1].getName().equals("B"))) {
                                Position altePositionDerRotkaeppchen = new Position(rotkaeppchen.getPosition().getX(), rotkaeppchen.getPosition().getY());
                                listeAllerPositionen.add(altePositionDerRotkaeppchen);
                                rotkaeppchen.geheRunter();
                                if (rotkaeppchen.getPosition().equals(wolf.getPosition())) {
                                    rotkaeppchen.gesundheitVerringern(wolf.schaden);
                                    if (!rotkaeppchen.istNochLebendig()) {
                                        return listeAllerPositionen;
                                    }
                                } else if (karte[rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY()] != null && karte[rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY()].getName().equals("G")) {
                                    rotkaeppchen.gesundheitVerringern(gefahr.schaden);
                                    if (!rotkaeppchen.istNochLebendig()) {
                                        return listeAllerPositionen;
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }

                    case 2:
                        if (rotkaeppchen.getPosition().getX() > 0) {
                            if (karte[rotkaeppchen.getPosition().getX() - 1][rotkaeppchen.getPosition().getY()] == null || !(karte[rotkaeppchen.getPosition().getX() - 1][rotkaeppchen.getPosition().getY()].getName().equals("B"))) {
                                Position altePositionDerRotkaeppchen = new Position(rotkaeppchen.getPosition().getX(), rotkaeppchen.getPosition().getY());
                                listeAllerPositionen.add(altePositionDerRotkaeppchen);
                                rotkaeppchen.geheLinks();
                                if (rotkaeppchen.getPosition().equals(wolf.getPosition())) {
                                    rotkaeppchen.gesundheitVerringern(wolf.schaden);
                                    if (!rotkaeppchen.istNochLebendig()) {
                                        return listeAllerPositionen;
                                    }
                                } else if (karte[rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY()] != null && karte[rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY()].getName().equals("G")) {
                                    rotkaeppchen.gesundheitVerringern(gefahr.schaden);
                                    if (!rotkaeppchen.istNochLebendig()) {
                                        return listeAllerPositionen;
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                    case 3:
                        if (rotkaeppchen.getPosition().getX() != x - 1) {
                            if (karte[rotkaeppchen.getPosition().getX() + 1][rotkaeppchen.getPosition().getY()] == null || !karte[rotkaeppchen.getPosition().getX() + 1][rotkaeppchen.getPosition().getY()].getName().equals("B")) {
                                Position altePositionDerRotkaeppchen = new Position(rotkaeppchen.getPosition().getX(), rotkaeppchen.getPosition().getY());
                                listeAllerPositionen.add(altePositionDerRotkaeppchen);
                                rotkaeppchen.geheRechts();
                                if (rotkaeppchen.getPosition().equals(wolf.getPosition())) {
                                    rotkaeppchen.gesundheitVerringern(wolf.schaden);
                                    if (!(rotkaeppchen.istNochLebendig())) {
                                        return listeAllerPositionen;
                                    }
                                } else if (karte[rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY()] != null && karte[rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY()].getName().equals("G")) {
                                    rotkaeppchen.gesundheitVerringern(gefahr.schaden);
                                    if (!(rotkaeppchen.istNochLebendig())) {
                                        return listeAllerPositionen;
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                }
            }
            else {
                Position letztePositionDerRotkaeppchen = new Position(rotkaeppchen.getPosition().getX(), rotkaeppchen.getPosition().getY());
                listeAllerPositionen.add(letztePositionDerRotkaeppchen);
                return listeAllerPositionen;
            }
        }
        // falls sich die Rotkaeppchen am Anfang nicht bewegen kann wegen der Objekte um sich herum, wird nur die erste Position
        // zu der Liste hinzugefuegt und die Liste wird dann zurueckgegeben.
        Position ppositionDerRotkaeppchen = new Position(rotkaeppchen.getPosition().getX(), rotkaeppchen.getPosition().getY());
        listeAllerPositionen.add(ppositionDerRotkaeppchen);
        return listeAllerPositionen;
        }

    // diese Methode printet das Waldstück.
    public void printWald () {
        // Rahmen: linke obere Ecke System.out.print("+");
        System.out.print("+");
        // Rahmen: erste Zeile
        for (int i = 0; i < x; i++) {
            System.out.print("-");
        }
        // Rahmen: rechte obere Ecke
        System.out.println("+");
        for (int j = 0; j < y; j++) {
            // Rahmen: linker Rand
            System.out.print("|");
            // Die eigentliche Karte
            for (int i = 0; i < x; i++) {
                if (karte[i][j] != null) {
                    System.out.print(karte[i][j].getName());
                } else {
                    System.out.print(" ");
                }
            }
            // Rahmen: rechter Rand
            System.out.println("|");
        }
        // Rahmen: linke untere Ecke
        System.out.print("+");
        // Rahmen: letzte Zeile
        for (int i = 0; i < x; i++) {
            System.out.print("-");
        }
        // Rahmen: rechte untere Ecke
        System.out.println("+");
    }

    // Diese Methode fuehrt den Weg zu der Oma und zurueck aus. Basierend auf der Rueckgabe von der wegFinden Methode
    // wird es entschieden, ob Rotkaeppchen das jeweilge Ziel erreicht hat. Dabei wird ihre letzte Position und ihr Gesundheitsstand
    // beruecksichtigt.
    public void start () {
        printWald();
        ArrayList<Position> steps = wegFinden(oma.getPosition());
        Position letztePosition = steps.get(steps.size() - 1);
        if (!(rotkaeppchen.istNochLebendig())) {
            System.out.println("Rotkaeppchen ist nicht bei der Oma angekommen.");
        }
        else if (letztePosition.equals(oma.getPosition())) {
            System.out.println("Rotkaeppchen ist bei Oma angekommen.");
            rotkaeppchen.sprechen(oma, 1);
            rotkaeppchen.setGesundheit(100);
            Position rotkaeppchenZuhause = new Position(0,0);
            ArrayList<Position> stepsZuruek = wegFinden(rotkaeppchenZuhause);
            Position letztePosition2 = stepsZuruek.get(stepsZuruek.size() - 1);
            if (letztePosition2.equals(rotkaeppchenZuhause)) {
                System.out.println("Rotkaeppchen ist wieder zu Hause angekommen.");
            }
            else if (!letztePosition2.equals(rotkaeppchenZuhause) && rotkaeppchen.istNochLebendig()) {
                System.out.println("Rotkaeppchen hat sich auf dem Heimweg verlaufen.");
            }
            else if (!letztePosition2.equals(rotkaeppchenZuhause) && !rotkaeppchen.istNochLebendig()) {
                System.out.println("Rotkaeppchen ist nicht wieder zu Hause angekommen.");
            }
        }
        else {
            System.out.println("Rotkaeppchen hat sich auf dem Weg zur Oma verlaufen.");
        }
    }
}

public class Rotkaeppchen extends VerwunschenerWald implements Person{
    private int gesundheit = 100;
    public Rotkaeppchen (Position position) {
        super(position);
    }
    public void geheHoch() {

        position.setY(position.getY() - 1);
    }
    public void geheRunter() {

        position.setY(position.getY() + 1);
    }
    public void geheLinks() {

        position.setX(position.getX() - 1);
    }
    public void geheRechts() {

        position.setX(position.getX() + 1);
    }

    public void gesundheitVerringern(int wert) {
        if (wert <= gesundheit) {
            gesundheit = gesundheit - wert;
        }
    }

    public boolean istNochLebendig() {
        if (gesundheit>0) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getName() {
        return "R";
    }

    public void setGesundheit (int gesundheit) {
        this.gesundheit = gesundheit;
    }

    //Diese Methode wird aufgerufen wenn Rotkaeppchen Oma auf der Karte findet und mit ihr reden will
    public void sprechen (Person konversationspartner, int zaehler) {
        switch (zaehler) {
            case 1:
            System.out.println("Hallo, Oma");
            zaehler++;
            konversationspartner.sprechen(this,zaehler);
            break;

            case 3:
            System.out.println("Tschuess, Oma");
            zaehler++;
            konversationspartner.sprechen(this,zaehler);
            break;

        }

    }
}

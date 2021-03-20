public class Oma extends VerwunschenerWald implements Person {

    public Oma (Position position) {
        super (position);
    }

    public String getName() {
        return "O";
    }


    //Diese Methode wird aufgerufen, wenn das Rotkaeppchen das Gecpraech mit Oma startet falls Rotkaeppchen Oma auf der Karte findet
    public void sprechen (Person konversationspartner, int zaehler) {
        switch (zaehler) {
            case 2:
                System.out.println("Hallo, Rotkaeppchen");
                zaehler++;
                konversationspartner.sprechen(this, zaehler);
                break;
        }
    }
}

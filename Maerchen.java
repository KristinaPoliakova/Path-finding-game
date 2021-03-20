public class Maerchen {
    //hier wird ein Objekt der Klasse Maerchenwelt mit bestimmten Parametern erzeugt und der Weg gestartet.
    public static void main (String [] args) {
        int x = 40;
        int y = 10;
        Maerchenwelt maerchenwelt = new Maerchenwelt (x,y,0,20);
        maerchenwelt.start();
    }
}

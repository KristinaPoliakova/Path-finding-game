public class Gefahr extends VerwunschenerWald {
    public Gefahr(Position position) {
        super (position);
        schaden = 2;
    }
    public String getName() {
        return "G";
    }
}

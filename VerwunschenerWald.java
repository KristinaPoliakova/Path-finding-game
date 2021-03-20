abstract class VerwunschenerWald {
    protected Position position;
    protected int schaden = 0;

    public VerwunschenerWald(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public int getSchaden() {
        return schaden;
    }

    public abstract String getName();

}

abstract class Peixe {
    protected int x, y;
    protected int ciclosVida;

    public Peixe(int x, int y) {
        this.x = x;
        this.y = y;
        this.ciclosVida = 0;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    abstract void atualizar(Aquario aquario);
    abstract char getSimbolo();
}
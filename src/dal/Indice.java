package dal;

public class Indice {
    private int indice;

    public Indice() {
        this.indice = 1;
    }

    public Indice(int indice) {
        this.indice = indice;
    }
    public int restarIndice() { //TODO analizar
        return this.indice-1;
    }
    public int getIndice() {
        return indice;
    }
}

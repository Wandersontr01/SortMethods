package sarrussys.main.arvoreABB;

public class NoABB {
    private Item item;
    private NoABB esq, dir;

    public NoABB(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
    protected void setItem (Item novo) {
        this.item = novo;
    }
    public NoABB getEsq() {
        return esq;
    }
    public void setEsq(NoABB esq) {
        this.esq = esq;
    }
    public NoABB getDir() {
        return dir;
    }
    public void setDir(NoABB dir) {
        this.dir = dir;
    }
}

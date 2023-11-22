package sarrussys.main.arvoreAVL;

import sarrussys.main.models.Item;

public class NoAVL {
    private Item item;
    private NoAVL esq, dir;
    private int fatorBalanceamento;

    public NoAVL (Item item) {
        this.item = item;
        this.fatorBalanceamento = 0;
    }

    public Item getItem() {
        return item;
    }

    public NoAVL getEsq() {
        return esq;
    }

    public NoAVL getDir() {
        return dir;
    }

    public int getFatorBalanceamento() {
        return fatorBalanceamento;
    }

    public void setFatorBalanceamento(int fatorBalanceamento) {
        this.fatorBalanceamento = fatorBalanceamento;
    }

    public void setEsq(NoAVL esq) {
        this.esq = esq;
    }

    public void setDir(NoAVL dir) {
        this.dir = dir;
    }
}

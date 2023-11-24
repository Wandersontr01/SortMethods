package sarrussys.main.arvoreABB;

import sarrussys.main.models.Item;

public class VetorItem {
    private Item [] vetorItem;
    private int quant;

    public VetorItem(int tamanho){
        this.vetorItem = new Item[tamanho];
        this.quant = 0;
    }


    public void inserir(Item item) {
        this.vetorItem[quant] = item;
        this.quant++;
    }

    public Item [] getVetorItem(){
        return this.vetorItem;
    }
    public Item get(int meio) {
        return this.vetorItem[meio];
    }
}

package sarrussys.main.arvoreABB;

import sarrussys.main.models.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import static java.lang.Long.parseLong;

public class ArvoreABB {
    private NoABB raiz;
    private int quant;
    public ArvoreABB() {
        this.quant = 0;
        this.raiz = null;
    }

    //getters


    public NoABB getRaiz() {
        return raiz;
    }

    public int getQuant() {
        return quant;
    }

    public boolean inserir (Item item) {
        this.raiz = inserir (item, this.raiz);
        return true;
    }

    private NoABB inserir (Item item, NoABB no) {
        if (no==null) {
            no = new NoABB(item);
            this.quant++;
        } else if ((item.getChave().compareTo(no.getItem().getChave()) > 0)) {
            no.setDir(inserir (item, no.getDir()));
        } else {
            no.setEsq(inserir (item, no.getEsq()));
        }
        return no;
    }


    public void remover (String chave) {
        this.remover(chave, this.raiz);
    }
    private NoABB remover (String chave, NoABB no) {
        if (no == null) {
            return null;
        } else if ((chave.compareTo(no.getItem().getChave()) > 0)) {
            no.setDir(remover(chave, no.getDir()));
        } else if ((chave.compareTo(no.getItem().getChave()) < 0)) {
            no.setEsq(remover(chave, no.getEsq()));
        } else if (no.getEsq() == null) {
            return no.getDir();
        } else if (no.getDir() == null) {
            return no.getEsq();
        } else {
            no.setEsq(arruma (no, no.getEsq()));
        }
        return no;
    }

    private NoABB arruma (NoABB no, NoABB maior) {
        if (maior.getDir() != null) {
            maior.setDir(arruma(no, maior.getDir()));
        } else {
            no.setItem(maior.getItem());
            maior = maior.getEsq();
        }
        return maior;
    }

    /*
     * caminhamento central
     * balanceamento
     */

    public NoABB pesquisar (String chave) {
        return pesquisar (chave, this.raiz);
    }
    private NoABB pesquisar (String chave, NoABB no) {
        if (no == null) {
            return null;
        } else if (Objects.equals(chave, no.getItem().getChave())) {
            return no;
        } else if ((chave.compareTo(no.getItem().getChave()) > 0)) {
            return pesquisar(chave, no.getDir());
        } else {
            return pesquisar(chave, no.getEsq());
        }
    }


    public VetorItem CamCentral () {
        VetorItem vetor = new VetorItem(this.quant);
        return (fazCamCentral(this.raiz, vetor));
    }

    private VetorItem fazCamCentral (NoABB no, VetorItem vetor) {
        if (no != null) {
            vetor = this.fazCamCentral(no.getEsq(), vetor);
            vetor.inserir(no.getItem());
            vetor = this.fazCamCentral(no.getDir(), vetor);
        }
        return vetor;
    }

    public ArvoreABB balancear () {
        ArvoreABB arv = new ArvoreABB();
        VetorItem vetor = CamCentral();
        balancear (vetor, arv, 0, quant-1);
        return arv;
    }

    private void balancear (VetorItem vetor, ArvoreABB arv, int inicio, int fim) {
        int meio;
        if (inicio <= fim) {
            meio = (inicio+fim)/2;
            Item item = vetor.get(meio);
            if (item != null) {
                arv.inserir(item);
            }
            // balancear a parte esquerda do vetor
            balancear (vetor, arv, inicio, meio-1);
            // balancear a parte direita do vetor
            balancear(vetor, arv, meio+1, fim);
        }
    }

    public NoABB[] percorrerEmOrdem() {
        NoABB[] vetorNos = new NoABB[contarNos(raiz)]; // Tamanho do vetor é o número total de nós
        int[] indice = {0}; // Usado para rastrear a posição atual no vetor
        percorrerEmOrdemRecursivo(raiz, vetorNos, indice);
        return vetorNos;
    }

    private void percorrerEmOrdemRecursivo(NoABB no, NoABB[] vetorNos, int[] indice) {
        if (no != null) {
            percorrerEmOrdemRecursivo(no.getEsq(), vetorNos, indice);
            vetorNos[indice[0]++] = no;
            percorrerEmOrdemRecursivo(no.getDir(), vetorNos, indice);
        }
    }

    // Método para contar o número total de nós na árvore
    public int contarNos(NoABB no) {
        if (no == null) {
            return 0;
        }
        return 1 + contarNos(no.getEsq()) + contarNos(no.getDir());
    }



}

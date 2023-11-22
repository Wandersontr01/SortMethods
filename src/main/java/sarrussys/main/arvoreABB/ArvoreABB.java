package sarrussys.main.arvoreABB;


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
        NoABB aux = pesquisar (item.getChave());
        if (aux != null) {
            return false;
        } else {
            this.raiz = inserir (item, this.raiz);
            return true;
        }
    }

    private NoABB inserir (Item item, NoABB no) {
        if (no==null) {
            no = new NoABB(item);
            this.quant++;
        } else if (item.getChave()>no.getItem().getChave()) {
            no.setDir(inserir (item, no.getDir()));
        } else {
            no.setEsq(inserir (item, no.getEsq()));
        }
        return no;
    }


    public void remover (int chave) {
        this.remover(chave, this.raiz);
    }
    private NoABB remover (int chave, NoABB no) {
        if (no == null) {
            return null;
        } else if (chave > no.getItem().getChave()) {
            no.setDir(remover(chave, no.getDir()));
        } else if (chave < no.getItem().getChave()) {
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

    public NoABB pesquisar (Long chave) {
        return pesquisar (chave, this.raiz);
    }
    private NoABB pesquisar (Long chave, NoABB no) {
        if (no == null) {
            return null;
        } else if (Objects.equals(chave, no.getItem().getChave())) {
            return no;
        } else if (chave > no.getItem().getChave()) {
            return pesquisar(chave, no.getDir());
        } else {
            return pesquisar(chave, no.getEsq());
        }
    }


    public VetorItem CamCentral () {
        VetorItem vetor = new VetorItem(InitABB.tamanho);
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
            arv.inserir(vetor.get(meio));
            // balancear a parte esquerda do vetor
            balancear (vetor, arv, inicio, meio-1);
            // balancear a parte direita do vetor
            balancear(vetor, arv, meio+1, fim);
        }
    }

    /*
     TESTE
     */

    private double pesquisarCpfRec(NoABB no, String cpf, FileWriter resultadoFile) throws IOException {
        if (no == null) {
            resultadoFile.write("CPF " + cpf + ":\nINEXISTENTE\n\n");
            return 0.0;  // Retorna 0.0 se o CPF não for encontrado
        }

        double saldoTotal = 0.0;

        if (parseLong(cpf) < no.getItem().getChave()) {
            saldoTotal += pesquisarCpfRec(no.getEsq(), cpf, resultadoFile);
        } else if (parseLong(cpf) > no.getItem().getChave()) {
            saldoTotal += pesquisarCpfRec(no.getDir(), cpf, resultadoFile);
        } else {
            // CPF encontrado
            resultadoFile.write("CPF " + cpf + ":\n");
            saldoTotal += escreverInformacoes(no, resultadoFile);
            resultadoFile.write("Saldo Total: " + saldoTotal + "\n\n");
        }

        return saldoTotal;
    }

    public void pesquisarCpf(String cpf, FileWriter resultadoFile) throws IOException {
        pesquisarCpfRec(raiz, cpf, resultadoFile);
    }

    private double escreverInformacoes(NoABB no, FileWriter resultadoFile) throws IOException {
        double saldoTotal = no.getItem().getSaldo();

        resultadoFile.write("Agencia: " + no.getItem().getAgencia()+
                                " Conta: " + no.getItem().getNumero()+
                                " Saldo: " + no.getItem().getSaldo() + "\n");

        if (no.getEsq() != null) {
            saldoTotal += escreverInformacoes(no.getEsq(), resultadoFile);
        }
        if (no.getDir() != null) {
            saldoTotal += escreverInformacoes(no.getDir(), resultadoFile);
        }

        return saldoTotal;
    }

}

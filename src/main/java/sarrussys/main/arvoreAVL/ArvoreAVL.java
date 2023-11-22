package sarrussys.main.arvoreAVL;

import sarrussys.main.models.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import static java.lang.Long.parseLong;

public class ArvoreAVL {
    private NoAVL raiz;
    private boolean h;
    private int quant;

    public ArvoreAVL() {
        this.raiz = null;
        this.quant = 0;
    }
    /*
        INSERIR
     */
    public void inserir (Item item){
        this.raiz = this.inserir (item, this.raiz);
    }

    private NoAVL inserir (Item item, NoAVL no) {
        if (no == null) {
            NoAVL novo = new NoAVL(item);
            this.h = true;
            return novo;
        } else {
            if (item.getChave() < no.getItem().getChave()) {
                // Insere à esquerda e verifica se precisa
                // balancear à direita
                no.setEsq(this.inserir(item, no.getEsq()));
                no = this.balancearDir(no);
                return no;
            } else {
                // Insere à direita e verifica se precisa
                // balancear à esquerda
                no.setDir(this.inserir(item, no.getDir()));
                no = this.balancearEsq(no);
                return no;
            }
        }
    }
    /*
        PESQUISAR
     */

    public NoAVL pesquisar (Long chave) {
        return pesquisar (chave, this.raiz);
    }
    private NoAVL pesquisar (Long chave, NoAVL no) {
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

    /*
      ÉTODO PARA VERIFICAR SE É
      NECESSÁRIO O BALANCEAMENTO PARA DIREITA DO NÓ
     */
    private NoAVL balancearDir (NoAVL no){
        if (this.h) {
            switch (no.getFatorBalanceamento()){
                case 1 : no.setFatorBalanceamento(0);
                    this.h = false;
                    break;
                case 0 : no.setFatorBalanceamento(-1); break;
                case -1: no = this.rotacaoDireita(no);
            }
        }
        return no;
    }
    /*
      ÉTODO PARA VERIFICAR SE É
      NECESSÁRIO O BALANCEAMENTO PARA ESQUERDA DO NÓ
     */

    private NoAVL balancearEsq (NoAVL no){
        if (this.h) {
            switch (no.getFatorBalanceamento()){
                case -1: no.setFatorBalanceamento(0);
                    this.h = false;
                    break;
                case 0: no.setFatorBalanceamento(1); break;
                case 1: no = this.rotacaoEsquerda(no);
            }
        }
        return no;
    }
    /*
        MÉTODO PARA REALIZAR ROTAÇÃO À DIREITA (RD)
        OU ROTAÇÃO DUPLA À DIREITA (RDD)
     */
    private NoAVL rotacaoDireita(NoAVL no){
        NoAVL temp1, temp2;
        temp1 = no.getEsq();
        if (temp1.getFatorBalanceamento() == -1){// Faz RD
            no.setEsq(temp1.getDir());
            temp1.setDir(no);
            no.setFatorBalanceamento(0);
            no = temp1;
        } else { // Faz RDD
            temp2 = temp1.getDir();
            temp1.setDir(temp2.getEsq());
            temp2.setEsq(temp1);
            no.setEsq(temp2.getDir());
            temp2.setDir(no);
            // Recalcula o FB do nó à direita na nova árvore
            if (temp2.getFatorBalanceamento() == -1)
                no.setFatorBalanceamento(1);
            else
                no.setFatorBalanceamento(0);
                // Recalcula o FB do nó à esquerda na nova árvore
            if (temp2.getFatorBalanceamento() == 1)
                temp1.setFatorBalanceamento(-1);
            else
                temp1.setFatorBalanceamento(0);
            no = temp2;
        }
        no.setFatorBalanceamento(0);
        this.h = false;
        return no;
    }
    /*
    .2.6 MÉTODO PARA REALIZAR ROTAÇÃO À ESQUERDA (RE)
    OU ROTAÇÃO DUPLA À ESQUERDA (RDE
     */
    private NoAVL rotacaoEsquerda(NoAVL no){
        NoAVL temp1, temp2;
        temp1 = no.getDir();
        if (temp1.getFatorBalanceamento() == 1){
            no.setDir (temp1.getEsq());
            temp1.setEsq(no);
            no.setFatorBalanceamento(0);
            no = temp1;
        } else {
            temp2 = temp1.getEsq();
            temp1.setEsq(temp2.getDir());
            temp2.setDir(temp1);
            no.setDir(temp2.getEsq());
            temp2.setEsq(no);
            if (temp2.getFatorBalanceamento() == 1)
                no.setFatorBalanceamento(-1);
            else
                no. setFatorBalanceamento(0);
            if (temp2. getFatorBalanceamento() == -1)
                temp1. setFatorBalanceamento(1);
            else
                temp1. setFatorBalanceamento(0);
            no = temp2;
        }
        no.setFatorBalanceamento(0);
        this.h = false;
        return no;
    }

    private double pesquisarCpfRec(NoAVL no, String cpf, FileWriter resultadoFile) throws IOException {
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

    private double escreverInformacoes(NoAVL no, FileWriter resultadoFile) throws IOException {
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

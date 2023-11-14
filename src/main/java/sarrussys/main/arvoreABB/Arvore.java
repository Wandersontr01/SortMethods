package sarrussys.main.arvoreABB;

import java.io.FileWriter;

class Arvore {
    No raiz;

    public Arvore() {
        this.raiz = null;
    }

    public void inserir(String cpf, int agencia, int numero, double saldo) {
        raiz = inserirRec(raiz, cpf, agencia, numero, saldo);
    }

    private No inserirRec(No no, String cpf, int agencia, int numero, double saldo) {
        if (no == null) {
            return new No(cpf, agencia, numero, saldo);
        }

        if (cpf.compareTo(no.cpf) < 0) {
            no.esquerda = inserirRec(no.esquerda, cpf, agencia, numero, saldo);
        } else {
            no.direita = inserirRec(no.direita, cpf, agencia, numero, saldo);
        }

        return no;
    }

    public void balancear() {
        int n = contarNos(raiz);
        criarListaComNodos(raiz, n);

        transformarListaParaArvore();
    }

    private int contarNos(No no) {
        if (no == null) {
            return 0;
        }

        return 1 + contarNos(no.esquerda) + contarNos(no.direita);
    }

    private No[] listaNodos;
    private int contadorLista;

    private void criarListaComNodos(No no, int n) {
        listaNodos = new No[n];
        contadorLista = 0;
        criarListaComNodosRecursivo(no);
    }

    private void criarListaComNodosRecursivo(No no) {
        if (no != null) {
            criarListaComNodosRecursivo(no.esquerda);
            listaNodos[contadorLista++] = no;
            criarListaComNodosRecursivo(no.direita);
        }
    }

    private void transformarListaParaArvore() {
        int m = (int) Math.pow(2, Math.floor(Math.log(listaNodos.length + 1) / Math.log(2))) - 1;
        rotacionarDireita(m);

        for (int n = listaNodos.length; n > 1; n = n / 2 - 1) {
            rotacionarEsquerda(n);
        }

        raiz = listaNodos[0];
    }

    private void rotacionarDireita(int n) {
        No temp = null;
        for (int i = 0; i < n; i++) {
            temp = listaNodos[i];
            listaNodos[i] = temp.direita;
            temp.direita = listaNodos[i + 1];
            listaNodos[i + 1] = temp;
        }
    }

    private void rotacionarEsquerda(int n) {
        No temp = null;
        for (int i = 0; i < n; i += 2) {
            temp = listaNodos[i];
            listaNodos[i] = temp.direita;
            temp.direita = listaNodos[i + 1];
            listaNodos[i + 1] = temp;
        }
    }

    public void pesquisaECriaArquivo(String[] cpfs) {
        try {
            FileWriter arquivoResultado = new FileWriter("resultados.txt");

            for (String cpf : cpfs) {
                No resultado = pesquisar(raiz, cpf);

                if (resultado != null) {
                    double saldoTotal = imprimirResultado(arquivoResultado, resultado);
                    arquivoResultado.write("Saldo total: " + saldoTotal + "\n");
                } else {
                    arquivoResultado.write("CPF " + cpf + ": INEXISTENTE\n");
                }
            }

            arquivoResultado.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private No pesquisar(No no, String cpf) {
        if (no == null || cpf.equals(no.cpf)) {
            return no;
        }

        if (cpf.compareTo(no.cpf) < 0) {
            return pesquisar(no.esquerda, cpf);
        }

        return pesquisar(no.direita, cpf);
    }

    private double imprimirResultado(FileWriter arquivoResultado, No resultado) throws Exception {
        double saldoTotal = 0;

        while (resultado != null && resultado.cpf != null && resultado.cpf.equals(resultado.cpf)) {
            arquivoResultado.write("CPF " + resultado.cpf + ":\n");
            arquivoResultado.write("Agência " + resultado.agencia + " Conta " + resultado.numero +
                    " Saldo: " + resultado.saldo + "\n");

            saldoTotal += resultado.saldo;

            resultado = resultado.direita;
        }

        return saldoTotal;
    }
}

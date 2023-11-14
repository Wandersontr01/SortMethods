package sarrussys.main.virus;

import java.io.FileWriter;

class ABB {
    private NoABB raiz;

    public ABB() {
        this.raiz = null;
    }

    public void inserir(String cpf, int agencia, int numero, double saldo) {
        raiz = inserirIterativo(raiz, cpf, agencia, numero, saldo);
    }

    private NoABB inserirIterativo(NoABB raiz, String cpf, int agencia, int numero, double saldo) {
        NoABB novoNo = new NoABB(cpf, agencia, numero, saldo);
        if (raiz == null) {
            return novoNo;
        }

        NoABB atual = raiz;
        NoABB anterior = null;

        while (atual != null) {
            anterior = atual;

            int comparacao = cpf.compareTo(atual.getCpf());

            if (comparacao < 0) {
                atual = atual.getEsquerda();
            } else if (comparacao > 0) {
                atual = atual.getDireita();
            } else {
                // Ignorar inserção se o CPF já existir (sem duplicatas)
                return raiz;
            }
        }

        int comparacao = cpf.compareTo(anterior.getCpf());

        if (comparacao < 0) {
            anterior.setEsquerda(novoNo);
        } else {
            anterior.setDireita(novoNo);
        }

        return raiz;
    }

    public void balancear() {
        int n = contarNos(raiz);
        criarListaComNodos(raiz, n);

        transformarListaParaArvore();
    }

    private int contarNos(NoABB no) {
        if (no == null) {
            return 0;
        }

        return 1 + contarNos(no.getEsquerda()) + contarNos(no.getDireita());
    }

    private NoABB[] listaNodos;
    private int contadorLista;

    private void criarListaComNodos(NoABB no, int n) {
        listaNodos = new NoABB[n];
        contadorLista = 0;
        criarListaComNodosRecursivo(no);
    }

    private void criarListaComNodosRecursivo(NoABB no) {
        if (no != null) {
            criarListaComNodosRecursivo(no.getEsquerda());
            listaNodos[contadorLista++] = no;
            criarListaComNodosRecursivo(no.getDireita());
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
        NoABB temp = null;
        for (int i = 0; i < n; i++) {
            temp = listaNodos[i];
            listaNodos[i] = temp.getDireita();
            temp.setDireita(listaNodos[i + 1]);
            listaNodos[i + 1] = temp;
        }
    }

    private void rotacionarEsquerda(int n) {
        NoABB temp = null;
        for (int i = 0; i < n; i += 2) {
            temp = listaNodos[i];
            if(temp != null) {
                listaNodos[i] = temp.getDireita();
                temp.setDireita(listaNodos[i + 1]);
                listaNodos[i + 1] = temp;
            }
        }
    }

    public void pesquisaECriaArquivos(String[] cpfsPesquisa) {
        for (String cpf : cpfsPesquisa) {
            pesquisaECriaArquivo(cpf);
        }
    }

    private void pesquisaECriaArquivo(String cpf) {
        try {
            FileWriter arquivoResultado = new FileWriter("resultado_" + cpf + ".txt");

            NoABB resultado = pesquisar(raiz, cpf);

            if (resultado != null) {
                double saldoTotal = imprimirResultado(arquivoResultado, resultado);
                arquivoResultado.write("Saldo total: " + saldoTotal + "\n");
            } else {
                arquivoResultado.write("CPF " + cpf + ": INEXISTENTE\n");
            }

            arquivoResultado.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private NoABB pesquisar(NoABB no, String cpf) {
        if (no == null || cpf.equals(no.getCpf())) {
            return no;
        }

        if (cpf.compareTo(no.getCpf()) < 0) {
            return pesquisar(no.getEsquerda(), cpf);
        }

        return pesquisar(no.getDireita(), cpf);
    }

    private double imprimirResultado(FileWriter arquivoResultado, NoABB resultado) throws Exception {
        double saldoTotal = 0;

        while (resultado != null && resultado.getCpf() != null && resultado.getCpf().equals(resultado.getCpf())) {
            arquivoResultado.write("CPF " + resultado.getCpf() + ":\n");
            arquivoResultado.write("Agência " + resultado.getAgencia() + " Conta " + resultado.getNumero() +
                    " Saldo: " + resultado.getSaldo() + "\n");

            saldoTotal += resultado.getSaldo();

            resultado = resultado.getDireita();
        }

        return saldoTotal;
    }
}

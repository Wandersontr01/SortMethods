package sarrussys.main.shellSortCPF;

import sarrussys.main.FilePath;
import sarrussys.main.models.ItemParaOrdenacao;

import java.io.*;

public class ShellSort {
    private final String nomeArquivo;
    public ShellSort(FilePath filePath, int tamanho, String nomeArquivo) throws Exception {
        this.nomeArquivo = nomeArquivo;
        lerArquivoCPFs(filePath.getFilePath(), tamanho);
    }

    private void lerArquivoCPFs(String arquivosCPFs, int tamanho) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivosCPFs));
        String linha;
        int posicaoParaInserir = 0;
        ItemParaOrdenacao[] vetorCpfs = new ItemParaOrdenacao[tamanho];

        while((linha = reader.readLine()) != null){
            String[] dados = linha.split(";");
            if (dados.length == 4) {
                String cpf = dados[3];
                String agencia = dados[0];
                String numero = dados[1];
                String saldo = dados[2];

                ItemParaOrdenacao item = new ItemParaOrdenacao(cpf, agencia, numero, saldo);
                vetorCpfs[posicaoParaInserir] = item;
                posicaoParaInserir++;
            }

        }
        shellsort(vetorCpfs, tamanho);
    }

    public void shellsort(ItemParaOrdenacao [] vetor, int quant) throws IOException {
        int i, j, h;
        ItemParaOrdenacao temp;
        h = 1;

        do{
            h = 3*h+1;
        }while (h < quant);
        do{
            h = h/3;
            for (i=h; i < quant; i++){
                temp = vetor[i];
                j = i;
                while (vetor[j-h].getChave().compareTo( temp.getChave()) > 0){
                    vetor[j] = vetor[j-h];
                    j -= h;
                    if (j < h) {
                        break;
                    }
                }
                vetor [j] = temp;
            }
        }while (h != 1);
        gravarArquivoOrdenado(vetor);
    }

    private void gravarArquivoOrdenado(ItemParaOrdenacao [] vetorCpfs) {
        try {

            String caminhoResultado = "src/main/resources/RESULTADOS_SHELLSORT/" + this.nomeArquivo + ".txt";
            FileWriter arquivoResultado = new FileWriter(caminhoResultado);

            vetorCpfs = ordenaPorAgencia(vetorCpfs);

            for(ItemParaOrdenacao item: vetorCpfs){
                String agencia = item.getAgencia();
                String conta = item.getNumero();
                String saldo = item.getSaldo();
                String cpf = item.getChave();
                String toString = agencia+";"+conta+";"+saldo+";"+cpf+"\n";
                arquivoResultado.write(toString);
            }
            arquivoResultado.close();
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    private static ItemParaOrdenacao[] ordenaPorAgencia(ItemParaOrdenacao[] vetorCpfs) {

        // Ordena o vetor pelo CPF, mantendo a ordem original dos cpfs iguais
        for (int i = vetorCpfs.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (vetorCpfs[j].getChave().equals(vetorCpfs[j + 1].getChave())) {
                    if (vetorCpfs[j].getAgencia().compareTo(vetorCpfs[j + 1].getAgencia()) > 0) {
                        ItemParaOrdenacao itemTemporario = vetorCpfs[j];
                        vetorCpfs[j] = vetorCpfs[j + 1];
                        vetorCpfs[j + 1] = itemTemporario;
                    }
                }
            }
        }

        return vetorCpfs;
    }
}
package sarrussys.main.quickSortCPF;

import sarrussys.main.FilePath;
import sarrussys.main.models.ItemParaOrdenacao;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.Integer.parseInt;

public class QuickSort {
    private final String nomeArquivo;
    public QuickSort(FilePath filePath, int tamanho, String nomeArquivo) throws Exception {
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
        quicksort(vetorCpfs, tamanho-1);
    }

    public void quicksort (ItemParaOrdenacao [] vetor, int tamanho){
        ordena (vetor,0, tamanho-1);
    }

    private void ordena (ItemParaOrdenacao [] vetor, int esq, int dir){
        int i = esq, j = dir;
        ItemParaOrdenacao temp;

        String pivo = vetor[(i + j) / 2].getChave(); // Utilizando String como tipo do pivo

        do {
            while (vetor[i].getChave().compareTo(pivo) < 0)
                i++;
            while (vetor[j].getChave().compareTo(pivo) > 0)
                j--;
            if (i <= j) {
                temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);
        if (esq < j)
            ordena(vetor, esq, j);

        if (dir > i)
            ordena(vetor, i, dir);

        gravarArquivoOrdenado(vetor);
    }

    private void gravarArquivoOrdenado(ItemParaOrdenacao[] vetorCpfs) {
        try {

            String caminhoResultado = "src/main/resources/RESULTADOS_QUICKSORT/" + this.nomeArquivo + ".txt";
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

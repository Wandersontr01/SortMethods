package sarrussys.main.arvoreABB;

import sarrussys.main.FilePath;;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InitABB {
    public static int tamanho = 0;
    public InitABB(FilePath flp){
        try{
            // Carregar dados para a ABB
            ArvoreABB arvoreABB = new ArvoreABB();
            String filePath = flp.getFilePath();
            String cpfFilePath = FilePath.filecpfs.getFilePath();

            carregarDados(filePath, arvoreABB);
            arvoreABB.balancear();

            // Criar arquivo único para resultados dentro da pasta RESULTADOS_ARVOREABB
            String nomeArquivo = flp.getNome();
            String caminhoResultado = "src/main/resources/RESULTADOS_ARVOREABB/" + nomeArquivo + ".txt";

            FileWriter resultadoFile = new FileWriter(caminhoResultado);
            pesquisarCPFs(cpfFilePath, arvoreABB, resultadoFile);

            resultadoFile.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private static void pesquisarCPFs(String arquivoCPFs, ArvoreABB abb, FileWriter resultadoFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivoCPFs));
        String linha;

        while ((linha = reader.readLine()) != null) {
            abb.pesquisarCpf(linha.trim(), resultadoFile);
        }

        // Fechar o leitor de arquivo de CPFs
        reader.close();
    }

    private static void carregarDados(String arquivo, ArvoreABB arvoreABB) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));

        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(";");

            if (dados.length == 4) {
                tamanho++;
                String cpf = dados[3];
                String agencia = dados[0];
                String numero = dados[1];
                String saldo = dados[2];


                Item item = new Item(cpf,agencia,numero,saldo);


                arvoreABB.inserir(item);
            }
        }
        reader.close();
    }

}

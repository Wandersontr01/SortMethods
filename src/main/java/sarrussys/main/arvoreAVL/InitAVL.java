package sarrussys.main.arvoreAVL;

import sarrussys.main.FilePath;
import sarrussys.main.models.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InitAVL {
    public InitAVL(FilePath flp){
        try{
            // Carregar dados para a AVL
            ArvoreAVL arvoreAVL = new ArvoreAVL();
            String filePath = flp.getFilePath();
            String cpfFilePath = FilePath.filecpfs.getFilePath();

            carregarDados(filePath, arvoreAVL);

            // Criar arquivo único para resultados dentro da pasta RESULTADOS_ARVOREAVL
            String nomeArquivo = flp.getNome();
            String caminhoResultado = "src/main/resources/RESULTADOS_ARVOREAVL/" + nomeArquivo + ".txt";

            FileWriter resultadoFile = new FileWriter(caminhoResultado);
            pesquisarCPFs(cpfFilePath, arvoreAVL, resultadoFile);

            resultadoFile.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private static void pesquisarCPFs(String arquivoCPFs, ArvoreAVL avl, FileWriter resultadoFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivoCPFs));
        String linha;

        while ((linha = reader.readLine()) != null) {
            avl.pesquisarCpf(linha.trim(), resultadoFile);
        }

        // Fechar o leitor de arquivo de CPFs
        reader.close();
    }

    private static void carregarDados(String arquivo, ArvoreAVL arvoreAVL) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(";");

            if (dados.length == 4) {
                String cpf = dados[3];
                String agencia = dados[0];
                String numero = dados[1];
                String saldo = dados[2];

                Item item = new Item(cpf,agencia,numero,saldo);

                arvoreAVL.inserir(item);
            }
        }
        reader.close();
    }
}

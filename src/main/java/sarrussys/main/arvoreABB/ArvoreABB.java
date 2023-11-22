package sarrussys.main.arvoreABB;

import sarrussys.main.FilePath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArvoreABB {

    public ArvoreABB(FilePath flp) {
        try {
            // Carregar dados para a ABB
            Arvore abb = new Arvore();
            String filePath = flp.getFilePath();
            String cpfFilePath = FilePath.filecpfs.getFilePath();

            carregarDados(filePath, abb);
            abb.balancear();

            // Criar arquivo único para resultados dentro da pasta RESULTADOS_ARVOREABB
            String nomeArquivo = flp.getNome();
            String caminhoResultado = "src/main/resources/RESULTADOS_ARVOREABB/" + nomeArquivo + ".txt";

            FileWriter resultadoFile = new FileWriter(caminhoResultado);

            // Pesquisar CPFs e gravar resultados
            pesquisarCPFs(cpfFilePath, abb, resultadoFile);

            // Fechar o arquivo de resultados
            resultadoFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void carregarDados(String arquivo, Arvore abb) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(";");
            if (dados.length == 4) {
                abb.inserir(dados[3], dados[0], dados[1], Double.parseDouble(dados[2]));
            }
        }
        reader.close();
    }

    private static void pesquisarCPFs(String arquivoCPFs, Arvore abb, FileWriter resultadoFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivoCPFs));
        String linha;

        while ((linha = reader.readLine()) != null) {
            abb.pesquisarCpf(linha.trim(), resultadoFile);
        }

        // Fechar o leitor de arquivo de CPFs
        reader.close();
    }
}

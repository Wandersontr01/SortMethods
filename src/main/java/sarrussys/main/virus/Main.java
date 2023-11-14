package sarrussys.main.virus;

import sarrussys.main.FilePath;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        ABB abb = new ABB();

        String filePath = FilePath.file50000.getFilePath();

        carregarRegistros(abb, filePath);
        abb.balancear();

        String[] cpfsPesquisa = lerCPFsDeArquivo(filePath);

        abb.pesquisaECriaArquivos(cpfsPesquisa);
    }

    private static String[] lerCPFsDeArquivo(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            return br.lines().toArray(String[]::new);
        } catch (Exception e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    private static void carregarRegistros(ABB abb, String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 4) {
                    String cpf = dados[3].trim();
                    int agencia = Integer.parseInt(dados[0].trim());
                    int numero = Integer.parseInt(dados[1].trim());
                    double saldo = Double.parseDouble(dados[2].trim());

                    abb.inserir(cpf, agencia, numero, saldo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
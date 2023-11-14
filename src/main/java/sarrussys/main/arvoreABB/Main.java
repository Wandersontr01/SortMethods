package sarrussys.main.arvoreABB;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        Arvore abb = new Arvore();

        // Carregar registros dos arquivos
        carregarRegistros(abb, "arquivo_500.txt");
        abb.balancear();
        abb.pesquisaECriaArquivo(new String[]{"11122233345", "44422233345"});
    }

    private static void carregarRegistros(Arvore abb, String nomeArquivo) {
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

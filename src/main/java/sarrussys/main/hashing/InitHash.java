package sarrussys.main.hashing;

import sarrussys.main.FilePath;

import java.io.*;

public class InitHash {

    public InitHash(FilePath flp) {
        try {
            Tabela tabela = new Tabela();
            // Criar arquivo único para resultados dentro da pasta RESULTADOS_ARVOREABB
            String nomeArquivo = flp.getNome();
            String caminhoResultado = "src/main/resources/RESULTADO_HASH/" + nomeArquivo + ".txt";
            String cpfFilePath = FilePath.filecpfs.getFilePath();

            FileWriter resultadoFile = new FileWriter(caminhoResultado);


            // Carregar registros do arquivo
            carregarRegistros(tabela, flp.getFilePath());




            // Realizar pesquisas com base nos CPFs fornecidos
            pesquisarEGravarResultados(tabela, cpfFilePath, resultadoFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void carregarRegistros(Tabela tabelaHash, String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                String cpf = partes[3];
                String agencia = partes[0];
                String conta = partes[1];
                double saldo = Double.parseDouble(partes[2]);
                tabelaHash.inserir(cpf, agencia, conta, saldo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void pesquisarEGravarResultados(Tabela tabelaHash, String nomeArquivoCpf, FileWriter resultadoFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivoCpf));
             FileWriter fw = resultadoFile) {

            String linha;
            while ((linha = br.readLine()) != null) {
                String cpfParaPesquisar = linha.trim();
                NoHash resultado = tabelaHash.buscar(cpfParaPesquisar);

                fw.write("CPF " + cpfParaPesquisar + ":\n");
                if (resultado != null) {
                    double saldoTotal = 0;
                    while (resultado != null && resultado.cpf.equals(cpfParaPesquisar)) {
                        fw.write("Agencia: " + resultado.agencia + " Conta: " + resultado.conta + " Saldo: " + resultado.saldo+"\n");
                        saldoTotal += resultado.saldo;
                        resultado = resultado.proximo;
                    }
                    fw.write("Saldo Total: " + saldoTotal + "\n\n");
                } else {
                    fw.write("INEXISTENTE\n\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

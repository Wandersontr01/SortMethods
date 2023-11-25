package sarrussys.main.hashing;

import sarrussys.main.FilePath;
import sarrussys.main.arvoreABB.ArvoreABB;
import sarrussys.main.models.DadosBancarios;
import sarrussys.main.models.Item;
import sarrussys.main.models.LerArquivoCPFs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class InitHash {

    public InitHash(FilePath flp) {
        try {
            Tabela tabela = new Tabela();
            LerArquivoCPFs lerArquivoCPFs = new LerArquivoCPFs();

            String cpfFilePath = FilePath.filecpfs.getFilePath();

            // Carregar registros do arquivo
            carregarDados(tabela, flp.getFilePath());

            //LER ARQUIVO DE CPFS PARA PESQUISAR
            String [] cpfsParaPesquisar = lerArquivoCPFs.lerArquivoCPFs(cpfFilePath);

            // Criar arquivo único para resultados dentro da pasta RESULTADOS_ARVOREABB
            String nomeArquivo = flp.getNome();
            String caminhoResultado = "src/main/resources/RESULTADO_HASH/" + nomeArquivo + ".txt";
            FileWriter resultadoFile = new FileWriter(caminhoResultado);
            gerarArquivoDeSaida(cpfsParaPesquisar, resultadoFile, tabela);

            resultadoFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void carregarDados(Tabela tabelaHash, String arquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(";");

            if (dados.length == 4) {
                String cpf = dados[3];
                String agencia = dados[0];
                String numero = dados[1];
                String saldo = dados[2];

                //INSTANCIANDO O NOVO ITEM
                Item itemNovo = new Item(cpf);
                //INSTANCIANDO O DADO BANCARIO DO NOVO CPF
                DadosBancarios dadosBancarios = new DadosBancarios(agencia, numero, saldo);
                //PESQUISANDO PARA SABER SE O NOVO CPF JÁ EXISTE
                Item itemExistente = tabelaHash.pesquisarPorCPF(cpf);

                if( itemExistente != null){
                    //se o cpf a ser inserido já existe
                    //insere dados bancarios na lista de contas do item
                    itemExistente.setContas(dadosBancarios);
                }else {
                    //se o cpf nao existe
                    //insere os dados bancarios na lista de contas do item
                    //adiciona o item na tabela
                    itemNovo.setContas(dadosBancarios);
                    tabelaHash.inserirItem(itemNovo);
                }
            }
        }
        reader.close();
    }

    private static void gerarArquivoDeSaida(String[] cpfsParaPesquisar, FileWriter resultadoFile, Tabela tabela) {

        try {
            List<String> cpfsJaPesquisado = new ArrayList<>();
            for (String cpfParaPesquisar : cpfsParaPesquisar) {
                Item resultadoPesquisaNaTabela = tabela.pesquisarPorCPF(cpfParaPesquisar);

                if(resultadoPesquisaNaTabela == null){
                    resultadoFile.write("CPF " + cpfParaPesquisar + ":\n");
                    //SE O CPF NAO EXISTIR NA TABELA PRINTA INEXISTENTE
                    resultadoFile.write("INEXISTENTE\n\n");
                }else{
                    // Realizar a pesquisa
                    //se o cpf existe na tabela
                    //CRIA UMA LISTA PARA PUXAR TODOS OS DADOS BANCARIOS DESSE CPF
                    //PERCORRE A LISTA PRINTANDO TODOS OS DADOS BANCARIOS
                    double saldoTotal = 0.0;
                    boolean cpfJaFoiPesquisado = cpfsJaPesquisado.contains(resultadoPesquisaNaTabela.getChave());

                    if(!cpfJaFoiPesquisado){
                        resultadoFile.write("CPF " + cpfParaPesquisar + ":\n");
                        List<DadosBancarios> dadosBancariosList = resultadoPesquisaNaTabela.getContas();

                        for (DadosBancarios dado : dadosBancariosList) {
                            resultadoFile.write(
                                    "Agencia: " + dado.getAgencia() +
                                            " Conta: " + dado.getNumero() +
                                            " Saldo: " + dado.getSaldo() + "\n");
                            saldoTotal += parseDouble(dado.getSaldo());
                        }
                        resultadoFile.write("Saldo Total: " + saldoTotal + "\n\n");
                        cpfsJaPesquisado.add(resultadoPesquisaNaTabela.getChave());
                    }
                }
            }
            resultadoFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package sarrussys.main.arvoreAVL;

import sarrussys.main.FilePath;
import sarrussys.main.models.DadosBancarios;
import sarrussys.main.models.Item;
import sarrussys.main.models.LerArquivoCPFs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

public class InitAVL {
    public InitAVL(FilePath flp){
        try{
            ArvoreAVL arvoreAVL = new ArvoreAVL();
            LerArquivoCPFs lerArquivoCPFs = new LerArquivoCPFs();

            String filePath = flp.getFilePath();
            String cpfFilePath = FilePath.filecpfs.getFilePath();

            // Carregar dados para a AVL
            carregarDados(filePath, arvoreAVL);

            //LER ARQUIVO DE CPFS PARA PESQUISAR OK
            String [] cpfsParaPesquisar = lerArquivoCPFs.lerArquivoCPFs(cpfFilePath);

            // Criar arquivo único para resultados dentro da pasta RESULTADOS_ARVOREAVL
            String nomeArquivo = flp.getNome();
            String caminhoResultado = "src/main/resources/RESULTADOS_ARVOREAVL/" + nomeArquivo + ".txt";
            FileWriter resultadoFile = new FileWriter(caminhoResultado);
            gerarArquivoDeSaida(cpfsParaPesquisar, resultadoFile, arvoreAVL);

            resultadoFile.close();

        }catch (Exception e){
            e.printStackTrace();
        }
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

                //INSTANCIANDO O NOVO ITEM
                Item itemNovo = new Item(cpf);
                //INSTANCIANDO O DADO BANCARIO DO NOVO CPF
                DadosBancarios dadosBancarios = new DadosBancarios(agencia, numero, saldo);
                //PESQUISANDO PARA SABER SE O NOVO CPF JÁ EXISTE
                NoAVL itemExistente = arvoreAVL.pesquisar(cpf);

                if( itemExistente != null){
                    //se o cpf a ser inserido já existe
                    //insere dados bancarios na lista de contas do item
                    itemExistente.getItem().setContas(dadosBancarios);
                }else {
                    //se o cpf nao existe na arvore
                    //insere os dados bancarios na lista de contas do item
                    //adiciona o item na arvore
                    itemNovo.setContas(dadosBancarios);
                    arvoreAVL.inserir(itemNovo);
                }
            }
        }
        reader.close();
    }

    private static void gerarArquivoDeSaida(String[] cpfsParaPesquisar, FileWriter resultadoFile, ArvoreAVL arvoreAVL) {

        try {
            List<String> cpfsJaPesquisado = new ArrayList<>();
            for (String cpfParaPesquisar : cpfsParaPesquisar) {
                NoAVL resultadoDaPesquisaNaArvore = arvoreAVL.pesquisar(cpfParaPesquisar);

                if(resultadoDaPesquisaNaArvore == null){
                    resultadoFile.write("CPF " + cpfParaPesquisar + ":\n");
                    //SE O CPF NAO EXISTIR NA ARVORE PRINTA INEXISTENTE
                    resultadoFile.write("INEXISTENTE\n\n");
                }else{
                    // Realizar a pesquisa
                    //se o cpf existe na arvore
                    //CRIA UMA LISTA PARA PUXAR TODOS OS DADOS BANCARIOS DESSE CPF
                    //PERCORRE A LISTA PRINTANDO TODOS OS DADOS BANCARIOS
                    double saldoTotal = 0.0;
                    boolean cpfJaFoiPesquisado = cpfsJaPesquisado.contains(resultadoDaPesquisaNaArvore.getItem().getChave());

                    if (!cpfJaFoiPesquisado) {
                        resultadoFile.write("CPF " + cpfParaPesquisar + ":\n");
                        List<DadosBancarios> dadosBancariosList = resultadoDaPesquisaNaArvore.getItem().getContas();

                        for (DadosBancarios dado : dadosBancariosList) {
                            resultadoFile.write(
                                    "Agencia: " + dado.getAgencia() +
                                            " Conta: " + dado.getNumero() +
                                            " Saldo: " + dado.getSaldo() + "\n");
                            saldoTotal += parseDouble(dado.getSaldo());
                        }
                        resultadoFile.write("Saldo Total: " + saldoTotal + "\n\n");
                        cpfsJaPesquisado.add(resultadoDaPesquisaNaArvore.getItem().getChave());
                    }
                }
            }
            resultadoFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

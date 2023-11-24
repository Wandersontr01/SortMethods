package sarrussys.main.arvoreAVL;

import sarrussys.main.FilePath;
import sarrussys.main.hashing.NoHash;
import sarrussys.main.hashing.Tabela;
import sarrussys.main.models.Item;

import java.io.*;
import java.util.Objects;

import static java.lang.Long.parseLong;

public class InitAVL {
    public InitAVL(FilePath flp){
        try{
            ArvoreAVL arvoreAVL = new ArvoreAVL();
            String filePath = flp.getFilePath();
            String cpfFilePath = FilePath.filecpfs.getFilePath();

            // Carregar dados para a AVL
            carregarDados(filePath, arvoreAVL);

            //LER ARQUIVO DE CPFS PARA PESQUISAR OK
            String [] cpfsParaPesquisar = lerArquivoCPFs(cpfFilePath);



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

                Item item = new Item(cpf,agencia,numero,saldo);

                arvoreAVL.inserir(item);
            }
        }
        reader.close();
    }

    private static String[] lerArquivoCPFs(String arquivosCPFs) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivosCPFs));
        String linha;
        String [] vetorCpfsParaPesquisar = new String[400];
        int posicao = 0;

        while((linha = reader.readLine()) != null){
            vetorCpfsParaPesquisar[posicao] = linha.trim();
            posicao++;
        }
        return vetorCpfsParaPesquisar;
    }


    private static void gerarArquivoDeSaida(String[] cpfsParaPesquisar, FileWriter resultadoFile, ArvoreAVL arvoreAVL) {
        NoAVL [] todosOsRegistros;
        todosOsRegistros = arvoreAVL.percorrerEmOrdem();

        try {
            for (String cpfParaPesquisar : cpfsParaPesquisar) {
                resultadoFile.write("CPF " + cpfParaPesquisar + ":\n");
                // Realizar a pesquisa
                boolean existe = false;
                double saldoTotal = 0.0;
                for (int i = 0; i < todosOsRegistros.length; i++) {

                    if (cpfParaPesquisar.compareTo(todosOsRegistros[i].getItem().getChave()) == 0){
                        System.out.println("l89 iniAVL"+cpfParaPesquisar + " e "+ todosOsRegistros[i].getItem().getChave());
                        resultadoFile.write("Agencia: " + todosOsRegistros[i].getItem().getAgencia() +
                                " Conta: " + todosOsRegistros[i].getItem().getNumero() +
                                " Saldo: " + todosOsRegistros[i].getItem().getSaldo() + "\n");
                        existe = true;
                        saldoTotal += todosOsRegistros[i].getItem().getSaldo();
                    }
                }
                if(existe){
                    resultadoFile.write("Saldo Total: "+ saldoTotal+"\n\n");
                }else{
                    resultadoFile.write("INEXISTENTE\n\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}

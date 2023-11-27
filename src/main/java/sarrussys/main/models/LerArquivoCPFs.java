package sarrussys.main.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LerArquivoCPFs {

    public LerArquivoCPFs(){
    }


    public String[] lerArquivoCPFs(String arquivosCPFs) throws IOException {
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
}

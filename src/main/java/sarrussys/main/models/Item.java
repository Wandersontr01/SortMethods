package sarrussys.main.models;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Item {
    private String cpf;
    private List<DadosBancarios> contas;

    public Item(String cpf){
        this.cpf = cpf;
        this.contas = new ArrayList<>();
    }

    public String getChave() {
        return cpf;
    }

    public List<DadosBancarios> getContas(){
        return this.contas;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setContas(DadosBancarios dadosBancarios) {
        this.contas.add(dadosBancarios);
    }


}

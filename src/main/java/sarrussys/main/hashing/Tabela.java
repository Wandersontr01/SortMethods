package sarrussys.main.hashing;

import sarrussys.main.models.Item;

import java.util.LinkedList;

public class Tabela {
    private static final int TAMANHO_TABELA = 1000; // Tamanho arbitrário da tabela

    private LinkedList<Item>[] tabela;

    public Tabela() {
        tabela = new LinkedList[TAMANHO_TABELA];
        for (int i = 0; i < TAMANHO_TABELA; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    private int calcularHash(String chave) {
        // Implementação simples de função de hash
        return Math.abs(chave.hashCode() % TAMANHO_TABELA);
    }

    public void inserirItem(Item item) {
        int indice = calcularHash(item.getChave());
        tabela[indice].add(item);
    }

    public Item pesquisarPorCPF(String cpf) {
        int indice = calcularHash(cpf);
        LinkedList<Item> lista = tabela[indice];

        for (Item item : lista) {
            if (item.getChave().equals(cpf)) {
                return item;
            }
        }
        return null; // Não encontrado
    }
}

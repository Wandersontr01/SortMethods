package sarrussys.main.arvoreABB;

public class VetorItem {
    private Item[] vetor;
    private int tamanho;

    public VetorItem(int capacidade) {
        vetor = new Item[capacidade];
        tamanho = 0;
    }

    public void inserir(Item item) {
        if (tamanho < vetor.length) {
            vetor[tamanho] = item;
            tamanho++;
        } else {
            // Se o vetor estiver cheio, você pode considerar redimensioná-lo ou lançar uma exceção.
            System.out.println("O vetor está cheio. Não é possível adicionar mais itens.");
        }
    }

    public Item get(int indice) {
        if (indice >= 0 && indice < tamanho) {
            return vetor[indice];
        } else {
            // Você pode lançar uma exceção ou retornar um valor padrão, dependendo do seu caso de uso.
            System.out.println("Índice inválido. Retornando null.");
            return null;
        }
    }

    public int tamanho() {
        return tamanho;
    }
}


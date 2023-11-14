package sarrussys.main.quickSortCPF;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static void quickSort(List<Conta> contas, int low, int high) {
        if (low < high) {
            int pi = partition(contas, low, high);
            quickSort(contas, low, pi - 1);
            quickSort(contas, pi + 1, high);
        }
    }

    static int partition(List<Conta> contas, int low, int high) {
        Conta pivot = contas.get(high);
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (contas.get(j).compareTo(pivot) < 0) {
                i++;
                Collections.swap(contas, i, j);
            }
        }
        Collections.swap(contas, i + 1, high);
        return (i + 1);
    }

    public static void main(String[] args) {
        List<Conta> contas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                contas.add(new Conta(parts[0], parts[1], parts[3], Double.parseDouble(parts[2])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        quickSort(contas, 0, contas.size() - 1);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            for (Conta conta : contas) {
                bw.write(conta.agencia + ";" + conta.numero + ";" + conta.saldo + ";" + conta.cpf);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
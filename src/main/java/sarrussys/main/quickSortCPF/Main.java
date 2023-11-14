package sarrussys.main.quickSortCPF;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {

        String fileName = "C:\\Users\\Detemann\\Documents\\Projetos\\ASAQV\\src\\main\\java\\sarrussys\\main\\quickSortCPF\\conta50000.txt";

        List<Conta> contas = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            lines.map(line -> line.split(";"))
                    .forEach(parts -> contas.add(new Conta(parts[0], parts[1], parts[3], Double.parseDouble(parts[2]))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        quickSort(contas, 0, contas.size() - 1);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("saida.txt"))) {
            for (Conta conta : contas) {
                bw.write(conta.agencia + ";" + conta.numero + ";" + conta.saldo + ";" + conta.cpf);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void quickSort(List<Conta> contas, int low, int high) {
        if (low < high) {
            int pivo = pivo(contas, low, high);
            quickSort(contas, low, pivo - 1);
            quickSort(contas, pivo + 1, high);
        }
    }

    static int pivo(List<Conta> contas, int low, int high) {
        Conta pivo = contas.get(high);
        AtomicInteger i = new AtomicInteger(low - 1);
        IntStream.range(low, high).forEach(j -> {
            if (contas.get(j).compareTo(pivo) < 0) {
                int val = i.incrementAndGet();
                Collections.swap(contas, val, j);
            }
        });
        Collections.swap(contas, i.get() + 1, high);
        return i.get() + 1;
    }
}
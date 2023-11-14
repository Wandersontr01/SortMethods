package sarrussys.main.shellSortCPF;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\Detemann\\Documents\\Projetos\\ASAQV\\src\\main\\java\\sarrussys\\main\\shellSortCPF\\conta500.txt";

        List<Conta> contas = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            lines.map(line -> line.split(";"))
                    .forEach(parts -> contas.add(new Conta(parts[0], parts[1], parts[3], Double.parseDouble(parts[2]))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        shellSort(contas);

        try {
            Files.write(Paths.get("saida.txt"),
                    contas.stream()
                            .map(conta -> conta.agencia + ";" + conta.numero + ";" + conta.saldo + ";" + conta.cpf)
                            .collect(Collectors.toList())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void shellSort(List<Conta> contas) {
        int n = contas.size();
        for (int dif = n/2; dif > 0; dif /= 2) {
            for (int i = dif; i < n; i += 1) {
                Conta temp = contas.get(i);
                int j;
                for (j = i; j >= dif && contas.get(j - dif).compareTo(temp) > 0; j -= dif) {
                    contas.set(j, contas.get(j - dif));
                }
                contas.set(j, temp);
            }
        }
    }
}

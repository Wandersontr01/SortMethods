package sarrussys.main.shellSortCPF;

import sarrussys.main.FilePath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ShellSort {

    public ShellSort(FilePath filePath) throws Exception {
        sortFile(filePath.getFilePath());
    }

    public static void sortFile(String fileName) throws Exception {
        Path inputPath = Paths.get(fileName);
        Path outputPath = inputPath.resolveSibling("RESULTADOS_SHELLSORT").resolve(inputPath.getFileName().toString());

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath.toString()))) {

            String line;
            String[] records = new String[50000];
            int count = 0;

            while ((line = reader.readLine()) != null) {
                records[count++] = line;
            }

            reader.close();

            shellsort(records, count);

            for (int i = 0; i < count; i++) {
                writer.write(records[i]);
                writer.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shellsort(String[] records, int count) {
        int h = 1;

        while (h < count / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < count; i++) {
                for (int j = i; j >= h && compareCPFs(records[j - h], records[j]) > 0; j -= h) {
                    swap(records, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void swap(String[] records, int i, int j) {
        String temp = records[i];
        records[i] = records[j];
        records[j] = temp;
    }

    public static int compareCPFs(String record1, String record2) {
        String[] fields1 = record1.split(";");
        String[] fields2 = record2.split(";");

        String cpf1 = fields1[3];
        String cpf2 = fields2[3];

        int cpfComparison = cpf1.compareTo(cpf2);

        // Se os CPFs são iguais, compare pela agência e número da conta
        if (cpfComparison == 0) {
            String account1 = fields1[1] + fields1[2]; // Agência + Número da conta
            String account2 = fields2[1] + fields2[2];
            return account1.compareTo(account2);
        }

        return cpfComparison;
    }
}
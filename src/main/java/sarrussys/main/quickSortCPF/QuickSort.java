package sarrussys.main.quickSortCPF;

import sarrussys.main.FilePath;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QuickSort {
    private FilePath filePath;

    public QuickSort(FilePath filePath) throws Exception {
        sortFile(filePath.getFilePath());
    }

    public static void sortFile(String fileName) throws Exception {
        Path inputPath = Paths.get(fileName);
        Path outputPath = inputPath.resolveSibling("RESULTADOS").resolve(inputPath.getFileName().toString());

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath.toString()))) {

            String line;
            String[] records = new String[50000];
            int count = 0;

            while ((line = reader.readLine()) != null) {
                records[count++] = line;
            }

            reader.close();

            quicksort(records, 0, count - 1);

            for (int i = 0; i < count; i++) {
                writer.write(records[i]);
                writer.newLine();
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void quicksort(String[] records, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(records, low, high);
            quicksort(records, low, pivotIndex - 1);
            quicksort(records, pivotIndex + 1, high);
        }
    }

    public static int partition(String[] records, int low, int high) {
        String pivot = records[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            int cmp = compareCPFs(records[j], pivot);
            if (cmp <= 0) {
                i++;
                swap(records, i, j);
            }
        }

        swap(records, i + 1, high);

        return i + 1;
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

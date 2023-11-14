package sarrussys.main.shellSortCPF;

import sarrussys.main.FilePath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        sortFile(FilePath.file500.getFilePath());
        sortFile(FilePath.file1000.getFilePath());
        sortFile(FilePath.file5000.getFilePath());
        sortFile(FilePath.file10000.getFilePath());
        sortFile(FilePath.file50000.getFilePath());
    }

    public static void sortFile(String fileName) throws Exception {
        // Ler o arquivo
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        String[] records = new String[50000];
        int count = 0;

        while ((line = reader.readLine()) != null) {
            records[count++] = line;
        }

        reader.close();

        shellsort(records, count);

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName+"_OK"));

        for (int i = 0; i < count; i++) {
            writer.write(records[i]);
            writer.newLine();
        }

        writer.close();
    }

    public static void shellsort(String[] records, int n) {
        int h = 1;

        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && compare(records[j], records[j - h]) < 0; j -= h) {
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

    public static int compare(String record1, String record2) {
        String cpf1 = record1.split(";")[3];
        String cpf2 = record2.split(";")[3];

        return cpf1.compareTo(cpf2);
    }
}
package sarrussys.main.quickSortCPF;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        sortFile("C:\\Users\\Detemann\\Documents\\Projetos\\ASAQV\\src\\main\\java\\sarrussys\\main\\quickSortCPF\\conta500.txt");
        sortFile("C:\\Users\\Detemann\\Documents\\Projetos\\ASAQV\\src\\main\\java\\sarrussys\\main\\quickSortCPF\\conta1000.txt");
        sortFile("C:\\Users\\Detemann\\Documents\\Projetos\\ASAQV\\src\\main\\java\\sarrussys\\main\\quickSortCPF\\conta5000.txt");
        sortFile("C:\\Users\\Detemann\\Documents\\Projetos\\ASAQV\\src\\main\\java\\sarrussys\\main\\quickSortCPF\\conta10000.txt");
        sortFile("C:\\Users\\Detemann\\Documents\\Projetos\\ASAQV\\src\\main\\java\\sarrussys\\main\\quickSortCPF\\conta50000.txt");
    }

    public static void sortFile(String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        String[] records = new String[50000];
        int count = 0;

        while ((line = reader.readLine()) != null) {
            records[count++] = line;
        }

        reader.close();

        quicksort(records, 0, count - 1);

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName+"_OK"));

        for (int i = 0; i < count; i++) {
            writer.write(records[i]);
            writer.newLine();
        }

        writer.close();
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
            if (compare(records[j], pivot) <= 0) {
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

    public static int compare(String record1, String record2) {
        String cpf1 = record1.split(";")[3];
        String cpf2 = record2.split(";")[3];

        return cpf1.compareTo(cpf2);
    }
}
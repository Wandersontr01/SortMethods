package sarrussys.main;

import sarrussys.main.quickSortCPF.QuickSort;
import sarrussys.main.shellSortCPF.ShellSort;
import sarrussys.main.arvoreABB.InitABB;

public class Menu {

    public static void main(String[] args) throws Exception {
        FilePath conta500 = FilePath.file500;
        FilePath conta1k = FilePath.file1000;
        FilePath conta5k = FilePath.file5000;
        FilePath conta10k = FilePath.file10000;
        FilePath conta50k = FilePath.file50000;

        new QuickSort(conta500);
        new ShellSort(conta500);

        new InitABB(conta1k);


    }
}

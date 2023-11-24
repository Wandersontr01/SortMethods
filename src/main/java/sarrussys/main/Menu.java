package sarrussys.main;

import sarrussys.main.arvoreAVL.InitAVL;
import sarrussys.main.hashing.InitHash;
import sarrussys.main.quickSortCPF.QuickSort;
import sarrussys.main.shellSortCPF.ShellSort;
import sarrussys.main.arvoreABB.InitABB;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        FilePath conta500 = FilePath.file500;
        FilePath conta1k = FilePath.file1000;
        FilePath conta5k = FilePath.file5000;
        FilePath conta10k = FilePath.file10000;
        FilePath conta50k = FilePath.file50000;

        int op;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("[ 1 ] Rodar QuickSort");
            System.out.println("[ 2 ] Rodar ShellSort");
            System.out.println("[ 3 ] Rodar Árvore ABB");
            System.out.println("[ 4 ] Rodar Árvore AVL");
            System.out.println("[ 5 ] Rodar Hashing");
            System.out.println("[ 0 ] Sair");

            System.out.print("Digite o número da opção desejada: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Você escolheu Rodar QuickSort");
                    new QuickSort(conta500);
                    break;
                case 2:
                    System.out.println("Você escolheu Rodar ShellSort");
                    new ShellSort(conta500);
                    break;
                case 3:
                    System.out.println("Você escolheu Rodar Árvore ABB");
                    new InitABB(conta1k);
                    break;
                case 4:
                    System.out.println("Você escolheu Rodar Árvore AVL");
                    new InitAVL(conta500);
                    break;
                case 5:
                    System.out.println("Você escolheu Rodar Hashing");
                    //new InitHash(conta500);
                    break;
                case 0:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (op != 0);

        scanner.close();
    }
}

package sarrussys.main;

import sarrussys.main.arvoreAVL.InitAVL;
import sarrussys.main.hashing.InitHash;
import sarrussys.main.quickSortCPF.QuickSort;
import sarrussys.main.shellSortCPF.ShellSort;
import sarrussys.main.arvoreABB.InitABB;

import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int op;
        FilePath conta500 = FilePath.file500;
        FilePath conta1k = FilePath.file1000;
        FilePath conta5k = FilePath.file5000;
        FilePath conta10k = FilePath.file10000;
        FilePath conta50k = FilePath.file50000;

        do {
            System.out.println("\n\nEscolha uma opção:");
            System.out.println("[ 1 ] Rodar QuickSort");
            System.out.println("[ 2 ] Rodar ShellSort");
            System.out.println("[ 3 ] Rodar Árvore ABB");
            System.out.println("[ 4 ] Rodar Árvore AVL");
            System.out.println("[ 5 ] Rodar Hashing");
            System.out.println("[ 6 ] Rodar Todos com Todos os arquivos - CUIDADO");
            System.out.println("[ 0 ] Sair");

            System.out.print("Digite o número da opção desejada: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    runQuickSortMenu();
                    break;
                case 2:
                    runShellSortMenu();
                    break;
                case 3:
                    runABBMenu();
                    break;
                case 4:
                    runAVLMenu();
                    break;
                case 5:
                    runHashingMenu();
                    break;
                case 6:
                    new QuickSort(conta500 , 500, "conta500");
                    new QuickSort(conta1k , 1000, "conta1000");
                    new QuickSort(conta5k, 5000, "conta5000");
                    new QuickSort(conta10k , 10000, "conta10000");
                    new QuickSort(conta50k, 50000, "conta50000");

                    new ShellSort(conta500 , 500, "conta500");
                    new ShellSort(conta1k , 1000, "conta1000");
                    new ShellSort(conta5k , 5000, "conta5000");
                    new ShellSort(conta10k , 10000, "conta10000");
                    new ShellSort(conta50k , 50000, "conta50000");

                    new InitABB(conta500);
                    new InitABB(conta1k);
                    new InitABB(conta5k);
                    new InitABB(conta10k);
                    new InitABB(conta50k);

                    new InitAVL(conta500);
                    new InitAVL(conta1k);
                    new InitAVL(conta5k);
                    new InitAVL(conta10k);
                    new InitAVL(conta50k);

                    new InitHash(conta500);
                    new InitHash(conta1k);
                    new InitHash(conta5k);
                    new InitHash(conta10k);
                    new InitHash(conta50k);
                    break;
                case 0:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (op != 0);
        sc.close();
    }

    private static void runQuickSortMenu() throws Exception {
        int op;
        FilePath conta500 = FilePath.file500;
        FilePath conta1k = FilePath.file1000;
        FilePath conta5k = FilePath.file5000;
        FilePath conta10k = FilePath.file10000;
        FilePath conta50k = FilePath.file50000;

        do {
            printOp("QuickSort");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Você escolheu Rodar QuickSort - conta500");
                    new QuickSort(conta500, 500, "conta500");
                    break;
                case 2:
                    System.out.println("Você escolheu Rodar QuickSort - conta1k");
                    new QuickSort(conta1k, 1000, "conta1000");
                    break;
                case 3:
                    System.out.println("Você escolheu Rodar QuickSort - conta5k");
                    new QuickSort(conta5k, 5000, "conta5000");
                    break;
                case 4:
                    System.out.println("Você escolheu Rodar QuickSort - conta10k");
                    new QuickSort(conta10k, 10000, "conta10000");
                    break;
                case 5:
                    System.out.println("Você escolheu Rodar QuickSort - conta50k");
                    new QuickSort(conta50k, 50000, "conta50000");
                    break;
                case 6:
                    System.out.println("Você escolheu Rodar QuickSort - TODOS");
                    new QuickSort(conta500 , 500, "conta500");
                    new QuickSort(conta1k , 1000, "conta1000");
                    new QuickSort(conta5k, 5000, "conta5000");
                    new QuickSort(conta10k , 10000, "conta10000");
                    new QuickSort(conta50k, 50000, "conta50000");
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (op != 0);

    }

    private static void runShellSortMenu() throws Exception{

        int op;
        FilePath conta500 = FilePath.file500;
        FilePath conta1k = FilePath.file1000;
        FilePath conta5k = FilePath.file5000;
        FilePath conta10k = FilePath.file10000;
        FilePath conta50k = FilePath.file50000;

        do {
            printOp("ShellSort");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Você escolheu Rodar QuickSort - conta500");
                    new ShellSort(conta500, 500, "conta500");
                    break;
                case 2:
                    System.out.println("Você escolheu Rodar QuickSort - conta1k");
                    new ShellSort(conta1k, 1000, "conta1000");
                    break;
                case 3:
                    System.out.println("Você escolheu Rodar QuickSort - conta5k");
                    new ShellSort(conta5k, 5000, "conta5000");
                    break;
                case 4:
                    System.out.println("Você escolheu Rodar QuickSort - conta10k");
                    new ShellSort(conta10k, 10000, "conta10000");
                    break;
                case 5:
                    System.out.println("Você escolheu Rodar QuickSort - conta50k");
                    new ShellSort(conta50k, 50000, "conta50000");
                    break;
                case 6:
                    System.out.println("Você escolheu Rodar QuickSort - TODOS");
                    new ShellSort(conta500 , 500, "conta500");
                    new ShellSort(conta1k , 1000, "conta1000");
                    new ShellSort(conta5k , 5000, "conta5000");
                    new ShellSort(conta10k , 10000, "conta10000");
                    new ShellSort(conta50k , 50000, "conta50000");
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (op != 0);


    }

    private static void runABBMenu() {
        int op;
        FilePath conta500 = FilePath.file500;
        FilePath conta1k = FilePath.file1000;
        FilePath conta5k = FilePath.file5000;
        FilePath conta10k = FilePath.file10000;
        FilePath conta50k = FilePath.file50000;

        do {
            printOp("Arvore ABB");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Você escolheu Rodar Arvore ABB - conta500");
                    new InitABB(conta500);
                    break;
                case 2:
                    System.out.println("Você escolheu Rodar Arvore ABB - conta1k");
                    new InitABB(conta1k);
                    break;
                case 3:
                    System.out.println("Você escolheu Rodar Arvore ABB - conta5k");
                    new InitABB(conta5k);
                    break;
                case 4:
                    System.out.println("Você escolheu Rodar Arvore ABB - conta10k");
                    new InitABB(conta10k);
                    break;
                case 5:
                    System.out.println("Você escolheu Rodar Arvore ABB - conta50k");
                    new InitABB(conta50k);
                    break;
                case 6:
                    System.out.println("Você escolheu Rodar Arvore ABB - TODOS");
                    new InitABB(conta500);
                    new InitABB(conta1k);
                    new InitABB(conta5k);
                    new InitABB(conta10k);
                    new InitABB(conta50k);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (op != 0);


    }

    private static void runAVLMenu() {
        int op;
        FilePath conta500 = FilePath.file500;
        FilePath conta1k = FilePath.file1000;
        FilePath conta5k = FilePath.file5000;
        FilePath conta10k = FilePath.file10000;
        FilePath conta50k = FilePath.file50000;

        do {
            printOp("Arvore AVL");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Você escolheu Rodar Arvore AVL - conta500");
                    new InitAVL(conta500);
                    break;
                case 2:
                    System.out.println("Você escolheu Rodar Arvore AVL - conta1k");
                    new InitAVL(conta1k);
                    break;
                case 3:
                    System.out.println("Você escolheu Rodar Arvore AVL - conta5k");
                    new InitAVL(conta5k);
                    break;
                case 4:
                    System.out.println("Você escolheu Rodar Arvore AVL - conta10k");
                    new InitAVL(conta10k);
                    break;
                case 5:
                    System.out.println("Você escolheu Rodar Arvore AVL - conta50k");
                    new InitAVL(conta50k);
                    break;
                case 6:
                    System.out.println("Você escolheu Rodar Arvore AVL - TODOS");
                    new InitAVL(conta500);
                    new InitAVL(conta1k);
                    new InitAVL(conta5k);
                    new InitAVL(conta10k);
                    new InitAVL(conta50k);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (op != 0);


    }

    private static void runHashingMenu() {
        int op;
        FilePath conta500 = FilePath.file500;
        FilePath conta1k = FilePath.file1000;
        FilePath conta5k = FilePath.file5000;
        FilePath conta10k = FilePath.file10000;
        FilePath conta50k = FilePath.file50000;

        do {
            printOp("Tabela Hash");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Você escolheu Rodar Tabela Hash - conta500");
                    new InitHash(conta500);
                    break;
                case 2:
                    System.out.println("Você escolheu Rodar Tabela Hash - conta1k");
                    new InitHash(conta1k);
                    break;
                case 3:
                    System.out.println("Você escolheu Rodar Tabela Hash - conta5k");
                    new InitHash(conta5k);
                    break;
                case 4:
                    System.out.println("Você escolheu Rodar Tabela Hash - conta10k");
                    new InitHash(conta10k);
                    break;
                case 5:
                    System.out.println("Você escolheu Rodar Tabela Hash - conta50k");
                    new InitHash(conta50k);
                    break;
                case 6:
                    System.out.println("Você escolheu Rodar Tabela Hash - TODOS");
                    new InitHash(conta500);
                    new InitHash(conta1k);
                    new InitHash(conta5k);
                    new InitHash(conta10k);
                    new InitHash(conta50k);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (op != 0);


    }

    private static void printOp(String metodo){
        System.out.println("\n\nEscolha uma opção para "+metodo+":");
        System.out.println("[ 1 ] Rodar conta500");
        System.out.println("[ 2 ] Rodar conta1k");
        System.out.println("[ 3 ] Rodar conta5k");
        System.out.println("[ 4 ] Rodar conta10k");
        System.out.println("[ 5 ] Rodar conta50k");
        System.out.println("[ 6 ] Rodar Todos");
        System.out.println("[ 0 ] Voltar ao menu principal");

        System.out.print("Digite o número da opção desejada: ");
    }

}

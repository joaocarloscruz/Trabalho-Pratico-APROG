import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TP{

    public static void main(String[] args) throws FileNotFoundException {
        String ficheiro = "planeamento.txt";

        int[][] matrizPlaneamento = lerPlaneamento(ficheiro);

        visualizarMatrizPlaneamento(matrizPlaneamento);
    }

    public static int[][] lerPlaneamento(String ficheiro) throws FileNotFoundException {
        int[][] matrizPlaneamento;
        Scanner scanner = new Scanner(new File(ficheiro));
        scanner.nextLine(); // Limpar título "Passagem de ano; 2023/12/28"

        String[] info = scanner.nextLine().split(" ");
        int numAutocarros = Integer.parseInt(info[0]);
        int numDias = Integer.parseInt(info[1]);

        matrizPlaneamento = new int[numAutocarros][numDias];

        for (int i = 0; i < numAutocarros; i++) {
            String[] linha = scanner.nextLine().split(" ");
            for (int j = 0; j < numDias; j++) {
                matrizPlaneamento[i][j] = Integer.parseInt(linha[j]);
            }
        }

        scanner.close();

        return matrizPlaneamento;
    }

    public static void visualizarMatrizPlaneamento(int[][] matriz) {
        System.out.println("b)");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("Bus " + i + " : ");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
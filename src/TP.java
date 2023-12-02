import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TP {
    static final String ficheiro = "planeamento.txt";
    static final int[][] distanciasCidades = {
            {0, 50, 60, 130, 300, 200},
            {50, 0, 130, 70, 250, 140},
            {60, 130, 0, 180, 370, 250},
            {130, 70, 180, 0, 200, 90},
            {300, 250, 370, 200, 0, 130},
            {200, 140, 250, 90, 130, 0}};

    static final String[] nomesCidades = {"Porto", "Aveiro", "Braga", "Coimbra", "Lisboa", "Fátima"};

    public static void main(String[] args) throws FileNotFoundException {

        int[][] matrizPlaneamento = lerPlaneamento(); //a)

        visualizarMatrizPlaneamento(matrizPlaneamento); //b)
        visualizarKmPercorrer(matrizPlaneamento); //c)

        visualizarTotalKmPorAutocarro(kmPercorrer(matrizPlaneamento)); //d)

        visualizarTotalKmFrota(kmPercorrer(matrizPlaneamento)); //e)

        visualizarMaxKmDia(kmPercorrer(matrizPlaneamento)); //f)

        mesmaCidade(kmPercorrer(matrizPlaneamento)); // g)

        visualizarMesmaCidade(matrizPlaneamento); //h)

        visualizarHistograma(totalKmPorAutocarro(kmPercorrer(matrizPlaneamento))); //i)

        visualizarAutocarroMaixProximo(lerJ(), matrizPlaneamento); //j)


    }

    public static int[][] lerPlaneamento() throws FileNotFoundException {
        int[][] matrizPlaneamento;
        Scanner scanner = new Scanner(new File(ficheiro));
        scanner.nextLine(); // Limpar título

        String[] informacao = scanner.nextLine().split(" ");
        int numAutocarros = Integer.parseInt(informacao[0]);
        int numDias = Integer.parseInt(informacao[1]);

        matrizPlaneamento = new int[numAutocarros][numDias];
        //Preeencher matrizPlaneamento
        for (int i = 0; i < numAutocarros; i++) {
            String[] linha = scanner.nextLine().split(" ");
            for (int j = 0; j < numDias; j++) {
                matrizPlaneamento[i][j] = Integer.parseInt(linha[j]);
            }
        }


        scanner.close();

        return matrizPlaneamento;
    }

    public static int[] lerJ() throws FileNotFoundException { //Ler valores da ultima linha do planeamento

        Scanner scanner = new Scanner(new File(ficheiro));
        scanner.nextLine(); // Limpar título

        String[] informacao = scanner.nextLine().split(" ");
        int numAutocarros = Integer.parseInt(informacao[0]);

        for (int i = 0; i < numAutocarros; i++) {
            scanner.nextLine();
        }

        String[] linha = scanner.nextLine().split(" ");
        int autocarro = Integer.parseInt(linha[0]);
        int dia = Integer.parseInt(linha[1]);

        int[] matriz = new int[2];
        matriz[0] = autocarro;
        matriz[1] = dia;


        scanner.close();

        return matriz;
    }

    public static void visualizarMatrizPlaneamento(int[][] matrizPlaneamento) {  // Visualizar a matriz de planeamento
        System.out.println("b)");
        for (int i = 0; i < matrizPlaneamento.length; i++) {
            System.out.print("Bus" + i + " : ");
            for (int j = 0; j < matrizPlaneamento[i].length; j++) {
                System.out.print(matrizPlaneamento[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] kmPercorrer(int[][] matrizPlaneamento) {    // Calcular os kilometros a percurrer apartir do planeamento e das distancias entre cidades e formar uma matrizPlaneamento com esses valores para enviar para o metodo seguinte.
        int cidadeAtual;
        int ultimaCidade = 0;
        int[][] distancias = new int[matrizPlaneamento.length][matrizPlaneamento[0].length];
        for (int i = 0; i < matrizPlaneamento.length; i++) {
            for (int j = 0; j < matrizPlaneamento[i].length; j++) {
                cidadeAtual = matrizPlaneamento[i][j];
                if (j != 0) {
                    distancias[i][j] = distanciasCidades[ultimaCidade][cidadeAtual];
                }
                ultimaCidade = cidadeAtual;
            }
        }
        return distancias;
    }

    public static void visualizarKmPercorrer(int[][] matrizPlaneamento) { // Calcular os kms a percorrer em cada iteração
        System.out.println("c)");

        int cidadeAtual;
        int ultimaCidade = 0;

        for (int i = 0; i < matrizPlaneamento.length; i++) {
            System.out.print("Bus" + i + " : ");
            System.out.printf("%2s", "0"); // Alinhar o primeiro valor à direita.
            for (int j = 0; j < matrizPlaneamento[i].length; j++) {
                cidadeAtual = matrizPlaneamento[i][j];
                if (j != 0) {
                    System.out.printf("%4d", distanciasCidades[ultimaCidade][cidadeAtual]); // Alinhar o resto dos valores à direita
                }
                ultimaCidade = cidadeAtual;
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void visualizarTotalKmPorAutocarro(int[][] distancias) {
        System.out.println("d)");
        int soma = 0;
        for (int i = 0; i < distancias.length; i++) {
            for (int j = 0; j < distancias[i].length; j++) {
                soma += distancias[i][j];
            }
            System.out.print("Bus" + i + " : " + soma + " km");
            System.out.println();
            soma = 0;

        }
        System.out.println();

    }

    public static int[] totalKmPorAutocarro(int[][] distancias) { // Criar matriz com os valores da alínea d), ou seja, os km percorridos por cada autocarro
        int[] totalKmPorAutocarro = new int[distancias.length];

        for (int i = 0; i < distancias.length; i++) {
            int soma = 0;
            for (int j = 0; j < distancias[i].length; j++) {
                soma += distancias[i][j];
            }
            totalKmPorAutocarro[i] = soma;
        }
        return totalKmPorAutocarro;
    }

    public static void visualizarTotalKmFrota(int[][] distancias) { // Calcular os km percorridos por todos os autocarros no total
        System.out.println("e)");
        int[] totalKmPorAutocarro = totalKmPorAutocarro(distancias);

        int soma = 0;


        for (int i = 0; i < totalKmPorAutocarro.length; i++) {
            soma += totalKmPorAutocarro[i];
        }

        System.out.println("Total de km a percorrer pela frota = " + soma + " km");
        System.out.println();
    }

    public static void visualizarMaxKmDia(int[][] distancias) { // Visualizar o máximo de kms percorridos num dia e o(s) dia(s) em que isso aconteceu
        System.out.println("f)");
        int soma;
        int kms = 0;
        int count = 0;
        int[] diasMax = new int[distancias[0].length];
        int[] totalDias = new int[distancias[0].length];

        for (int i = 0; i < distancias[0].length; i++) {
            soma = 0;
            for (int j = 0; j < distancias.length; j++) {
                soma += distancias[j][i];
                totalDias[i] = soma;
            }
        }

        for (int j = 0; j < distancias[0].length; j++) {
            if (totalDias[j] > kms) {
                kms = totalDias[j];
            }
        }

        System.out.print("máximo de kms num dia: (" + kms + " km), dias: [");
        for (int j = 0; j < distancias[0].length; j++) {
            if (totalDias[j] == kms) {
                diasMax[j] = j;
                count++;
                if (count > 1) {
                    System.out.print(", ");
                }
                System.out.print(diasMax[j]);
            }
        }
        System.out.println("]");
        System.out.println();
    }


    public static void mesmaCidade(int[][] distancias) { // Visualizar os autocarros que permanecem mais do que 1 dia na mesma cidade
        System.out.println("g)");
        System.out.print("Autocarros que permanecem mais de 1 dia consecutivo na mesma cidade:");
        boolean cidadeDiferente = true;
        for (int i = 0; i < distancias.length; i++) {
            for (int j = 1; j < distancias[i].length; j++) {
                if ((distancias[i][j] == 0) && cidadeDiferente) {
                    System.out.print(" Bus" + i);
                    cidadeDiferente = false;
                }
            }
            cidadeDiferente = true;
        }
        System.out.println();
        System.out.println();

    }

    public static void visualizarMesmaCidade(int[][] matrizPlaneamento) { // Visualizar o(s) dia(s) em que os autocarros sem encontram todos na mesma cidade
        System.out.println("h)");
        int diaMaisTardio = 0;
        int lugarTardio = 0;
        int primeiroValor;
        boolean todosIguais;
        for (int i = 0; i < matrizPlaneamento[0].length; i++) {
            todosIguais = true;
            primeiroValor = matrizPlaneamento[0][i];
            for (int j = 1; j < matrizPlaneamento.length; j++) {
                if (primeiroValor != matrizPlaneamento[j][i]) {
                    todosIguais = false;
                }
            }
            if (todosIguais) {
                diaMaisTardio = i;
                lugarTardio = primeiroValor;
            }
        }
        System.out.println("No dia" + " <" + diaMaisTardio + ">, todos os autocarros estarão em <" + nomesCidades[lugarTardio] + ">");
        System.out.println();
    }

    public static void visualizarHistograma(int[] totalKmPorAutocarro) { //Criar um histograma sobre os kms percorridos por cada autocarro ao longo dos dias.
        System.out.println("i)");
        int total = 0;
        int n;
        for (int i = 0; i < totalKmPorAutocarro.length; i++) {
            total += totalKmPorAutocarro[i];
        }
        for (int i = 0; i < totalKmPorAutocarro.length; i++) {
            double percentagemPercorrida = ((double) totalKmPorAutocarro[i] / total) * 100;
            n = (int) (percentagemPercorrida / 10);

            System.out.printf("Bus%d (%.2f%%) :", i, percentagemPercorrida);

            while (n > 0) {
                System.out.print("*");
                n--;
            }

            System.out.println();
        }
        System.out.println();
    }

    public static void visualizarAutocarroMaixProximo(int[] info, int[][] matrizPlaneamento) { // Calcular o autocarro que se encontra mais próximo doutro num certo dia.
        System.out.println("j)");
        int nBus = info[0];
        int nDia = info[1];
        int menor = -1;
        int nBusMaisProximo = -1;
        boolean primeiro = true;
        int[] comparacao = new int[matrizPlaneamento.length];
        for (int i = 0; i < comparacao.length; i++) {
            comparacao[i] = matrizPlaneamento[i][nDia];

        }
        int cidadeAtual = comparacao[nBus];
        for (int i = 0; i < comparacao.length; i++) {
            if (nBus != i) {
                int distancia = distanciasCidades[comparacao[i]][cidadeAtual];
                if (primeiro) {
                    menor = distancia;
                    primeiro = false;
                    nBusMaisProximo = i;
                } else {
                    if (distancia < menor) {
                        menor = distancia;
                        nBusMaisProximo = i;
                    }
                }
            }

        }
        int cidadeBusMaisProx = comparacao[nBusMaisProximo];
        System.out.println("No dia " + "<" + nDia + ">" + ", <Bus" + nBus + "> estará em <" + nomesCidades[cidadeAtual] + ">. <Bus" + nBusMaisProximo + "> é o mais próximo. Está em <" + nomesCidades[cidadeBusMaisProx] + "> a <" + menor + " km>");


    }


}
import java.io.*;
import java.util.*;

/**
 * Classe utilitária com métodos para manipulação e cálculo de matrizes
 */
public class MatrixUtils {
    
    /**
     * Função que retorna a soma de dois inteiros
     * @param a primeiro número
     * @param b segundo número
     * @return soma de a e b
     */
    public static int soma(int a, int b) {
        return a + b;
    }
    
    /**
     * Função que retorna a multiplicação de dois inteiros
     * @param a primeiro número
     * @param b segundo número
     * @return multiplicação de a e b
     */
    public static int multiplicacao(int a, int b) {
        return a * b;
    }
    
    /**
     * Lê uma matriz de um arquivo de texto
     * @param arquivo arquivo a ser lido
     * @return matriz lida do arquivo
     * @throws IOException se houver erro na leitura
     */
    public static int[][] lerMatrizDeArquivo(File arquivo) throws IOException {
        List<int[]> linhas = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Remove espaços extras e divide por espaços
                String[] valores = linha.trim().split("\\s+");
                int[] linhaMatriz = new int[valores.length];
                
                for (int i = 0; i < valores.length; i++) {
                    linhaMatriz[i] = Integer.parseInt(valores[i]);
                }
                linhas.add(linhaMatriz);
            }
        }
        
        // Converte List para array 2D
        int[][] matriz = new int[linhas.size()][];
        for (int i = 0; i < linhas.size(); i++) {
            matriz[i] = linhas.get(i);
        }
        
        return matriz;
    }
    
    /**
     * Verifica se uma matriz é quadrada
     * @param matriz matriz a ser verificada
     * @return true se a matriz for quadrada, false caso contrário
     */
    public static boolean validarMatrizQuadrada(int[][] matriz) {
        if (matriz == null || matriz.length == 0) {
            return false;
        }
        
        int numLinhas = matriz.length;
        int numColunas = matriz[0].length;
        
        // Verifica se todas as linhas têm o mesmo número de colunas
        for (int i = 1; i < numLinhas; i++) {
            if (matriz[i].length != numColunas) {
                return false;
            }
        }
        
        // Verifica se é quadrada (mesmo número de linhas e colunas)
        return numLinhas == numColunas;
    }
    
    /**
     * Soma duas matrizes usando apenas a função soma()
     * @param matrizA primeira matriz
     * @param matrizB segunda matriz
     * @return matriz resultante da soma
     */
    public static int[][] somarMatrizes(int[][] matrizA, int[][] matrizB) {
        int n = matrizA.length;
        int[][] matrizC = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Calcula o elemento C[i][j] da matriz resultante
                matrizC[i][j] = soma(matrizA[i][j], matrizB[i][j]);
            }
        }
        
        return matrizC;
    }
    
    /**
     * Multiplica duas matrizes usando apenas as funções soma() e multiplicacao()
     * @param matrizA primeira matriz
     * @param matrizB segunda matriz
     * @return matriz resultante da multiplicação
     */
    public static int[][] multiplicarMatrizes(int[][] matrizA, int[][] matrizB) {
        int n = matrizA.length;
        int[][] matrizC = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Calcula o elemento C[i][j] da matriz resultante
                int resultado = 0;
                for (int k = 0; k < n; k++) {
                    int produto = multiplicacao(matrizA[i][k], matrizB[k][j]);
                    resultado = soma(resultado, produto);
                }
                matrizC[i][j] = resultado;
            }
        }
        
        return matrizC;
    }
    
    /**
     * Gera o passo a passo detalhado da soma de matrizes
     * @param matrizA primeira matriz
     * @param matrizB segunda matriz
     * @return string com o passo a passo detalhado
     */
    public static String gerarPassoAPassoSoma(int[][] matrizA, int[][] matrizB) {
        StringBuilder passoAPasso = new StringBuilder();
        int n = matrizA.length;
        
        passoAPasso.append("=== PASSO A PASSO DA SOMA ===\n\n");
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                passoAPasso.append(String.format("C[%d][%d] = ", i, j));
                passoAPasso.append(String.format("soma(A[%d][%d], B[%d][%d])", i, j, i, j));
                passoAPasso.append(String.format(" = soma(%d, %d)", matrizA[i][j], matrizB[i][j]));
                passoAPasso.append(String.format(" = %d\n\n", soma(matrizA[i][j], matrizB[i][j])));
            }
        }
        
        return passoAPasso.toString();
    }
    
    /**
     * Gera o passo a passo detalhado da multiplicação de matrizes
     * @param matrizA primeira matriz
     * @param matrizB segunda matriz
     * @return string com o passo a passo detalhado
     */
    public static String gerarPassoAPassoMultiplicacao(int[][] matrizA, int[][] matrizB) {
        StringBuilder passoAPasso = new StringBuilder();
        int n = matrizA.length;
        
        passoAPasso.append("=== PASSO A PASSO DA MULTIPLICAÇÃO ===\n\n");
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                passoAPasso.append(String.format("C[%d][%d] = ", i, j));
                
                int resultado = 0;
                for (int k = 0; k < n; k++) {
                    int produto = multiplicacao(matrizA[i][k], matrizB[k][j]);
                    
                    if (k > 0) {
                        passoAPasso.append(" + ");
                    }
                    passoAPasso.append(String.format("multiplicacao(A[%d][%d], B[%d][%d])", i, k, k, j));
                    passoAPasso.append(String.format(" = %d", produto));
                    
                    resultado = soma(resultado, produto);
                }
                
                passoAPasso.append(String.format(" = %d\n\n", resultado));
            }
        }
        
        return passoAPasso.toString();
    }
    
    /**
     * Converte uma matriz para string formatada
     * @param matriz matriz a ser convertida
     * @param nome nome da matriz
     * @return string formatada da matriz
     */
    public static String matrizParaString(int[][] matriz, String nome) {
        StringBuilder sb = new StringBuilder();
        sb.append(nome).append(":\n");
        
        for (int i = 0; i < matriz.length; i++) {
            sb.append("  ");
            for (int j = 0; j < matriz[i].length; j++) {
                sb.append(String.format("%4d", matriz[i][j]));
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
} 
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Classe principal do programa de multiplicação de matrizes
 * Inicializa a interface gráfica
 */
public class MatrixCalculator {
    
    /**
     * Método principal que inicializa a aplicação
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Inicializa a interface gráfica na thread de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            MatrixUI ui = new MatrixUI();
            ui.setVisible(true);
        });
    }
} 
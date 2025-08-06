import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Interface gráfica para o programa de multiplicação de matrizes
 */
public class MatrixUI extends JFrame {
    
    // Componentes da interface
    private JButton btnSelecionarA, btnSelecionarB, btnMultiplicar, btnSomar;
    private JLabel lblArquivoA, lblArquivoB;
    private JTextArea txtMatrizA, txtMatrizB, txtMatrizC, txtPassoAPasso;
    private JScrollPane scrollMatrizA, scrollMatrizB, scrollMatrizC, scrollPassoAPasso;
    private JTabbedPane tabbedPane;
    private JLabel lblStatus;
    
    // Dados das matrizes
    private int[][] matrizA, matrizB, matrizC;
    private File arquivoA, arquivoB;
    
    /**
     * Construtor da interface gráfica
     */
    public MatrixUI() {
        inicializarInterface();
        configurarEventos();
    }
    
    /**
     * Inicializa os componentes da interface
     */
    private void inicializarInterface() {
        setTitle("Calculadora de Multiplicação de Matrizes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Painel superior para seleção de arquivos
        JPanel topPanel = criarPainelSelecaoArquivos();
        
        // Painel central com abas
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Matrizes", criarPainelMatrizes());
        tabbedPane.addTab("Passo a Passo", criarPainelPassoAPasso());
        
        // Painel inferior para status
        JPanel bottomPanel = criarPainelStatus();
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
    }
    
    /**
     * Cria o painel de seleção de arquivos
     */
    private JPanel criarPainelSelecaoArquivos() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Seleção de Arquivos"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Matriz A
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Matriz A:"), gbc);
        
        gbc.gridx = 1;
        lblArquivoA = new JLabel("Nenhum arquivo selecionado");
        panel.add(lblArquivoA, gbc);
        
        gbc.gridx = 2;
        btnSelecionarA = new JButton("Selecionar Arquivo A");
        panel.add(btnSelecionarA, gbc);
        
        // Matriz B
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Matriz B:"), gbc);
        
        gbc.gridx = 1;
        lblArquivoB = new JLabel("Nenhum arquivo selecionado");
        panel.add(lblArquivoB, gbc);
        
        gbc.gridx = 2;
        btnSelecionarB = new JButton("Selecionar Arquivo B");
        panel.add(btnSelecionarB, gbc);
        
        // Botões de cálculo
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnMultiplicar = new JButton("Multiplicar Matrizes");
        btnMultiplicar.setEnabled(false);
        panel.add(btnMultiplicar, gbc);
        
        gbc.gridx = 1;
        btnSomar = new JButton("Somar Matrizes");
        btnSomar.setEnabled(false);
        panel.add(btnSomar, gbc);
        
        return panel;
    }
    
    /**
     * Cria o painel de visualização das matrizes
     */
    private JPanel criarPainelMatrizes() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        
        // Matriz A
        JPanel panelA = new JPanel(new BorderLayout());
        panelA.setBorder(BorderFactory.createTitledBorder("Matriz A"));
        txtMatrizA = new JTextArea();
        txtMatrizA.setEditable(false);
        txtMatrizA.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollMatrizA = new JScrollPane(txtMatrizA);
        panelA.add(scrollMatrizA, BorderLayout.CENTER);
        
        // Matriz B
        JPanel panelB = new JPanel(new BorderLayout());
        panelB.setBorder(BorderFactory.createTitledBorder("Matriz B"));
        txtMatrizB = new JTextArea();
        txtMatrizB.setEditable(false);
        txtMatrizB.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollMatrizB = new JScrollPane(txtMatrizB);
        panelB.add(scrollMatrizB, BorderLayout.CENTER);
        
        // Matriz C (Resultado)
        JPanel panelC = new JPanel(new BorderLayout());
        panelC.setBorder(BorderFactory.createTitledBorder("Matriz C (Resultado)"));
        txtMatrizC = new JTextArea();
        txtMatrizC.setEditable(false);
        txtMatrizC.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollMatrizC = new JScrollPane(txtMatrizC);
        panelC.add(scrollMatrizC, BorderLayout.CENTER);
        
        panel.add(panelA);
        panel.add(panelB);
        panel.add(panelC);
        
        return panel;
    }
    
    /**
     * Cria o painel de passo a passo
     */
    private JPanel criarPainelPassoAPasso() {
        JPanel panel = new JPanel(new BorderLayout());
        
        txtPassoAPasso = new JTextArea();
        txtPassoAPasso.setEditable(false);
        txtPassoAPasso.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollPassoAPasso = new JScrollPane(txtPassoAPasso);
        
        panel.add(scrollPassoAPasso, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Cria o painel de status
     */
    private JPanel criarPainelStatus() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEtchedBorder());
        
        lblStatus = new JLabel("Pronto para selecionar arquivos");
        panel.add(lblStatus, BorderLayout.WEST);
        
        return panel;
    }
    
    /**
     * Configura os eventos dos componentes
     */
    private void configurarEventos() {
        // Botão selecionar arquivo A
        btnSelecionarA.addActionListener(e -> selecionarArquivo('A'));
        
        // Botão selecionar arquivo B
        btnSelecionarB.addActionListener(e -> selecionarArquivo('B'));
        
        // Botão multiplicar
        btnMultiplicar.addActionListener(e -> calcularMultiplicacao());
        
        // Botão somar
        btnSomar.addActionListener(e -> calcularSoma());
    }
    
    /**
     * Seleciona um arquivo para a matriz especificada
     * @param tipo 'A' para matriz A, 'B' para matriz B
     */
    private void selecionarArquivo(char tipo) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Arquivos de texto (*.txt)", "txt"));
        
        int resultado = fileChooser.showOpenDialog(this);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            
            try {
                int[][] matriz = MatrixUtils.lerMatrizDeArquivo(arquivo);
                
                if (tipo == 'A') {
                    matrizA = matriz;
                    arquivoA = arquivo;
                    lblArquivoA.setText(arquivo.getName());
                    txtMatrizA.setText(MatrixUtils.matrizParaString(matriz, "Matriz A"));
                    atualizarStatus("Matriz A carregada: " + matriz.length + "x" + matriz.length);
                } else {
                    matrizB = matriz;
                    arquivoB = arquivo;
                    lblArquivoB.setText(arquivo.getName());
                    txtMatrizB.setText(MatrixUtils.matrizParaString(matriz, "Matriz B"));
                    atualizarStatus("Matriz B carregada: " + matriz.length + "x" + matriz.length);
                }
                
                // Habilita os botões se ambas as matrizes foram carregadas
                if (matrizA != null && matrizB != null) {
                    btnMultiplicar.setEnabled(true);
                    btnSomar.setEnabled(true);
                    atualizarStatus("Ambas as matrizes carregadas. Escolha uma operação.");
                }
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Erro ao ler arquivo: " + ex.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE);
                atualizarStatus("Erro ao carregar arquivo");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Formato inválido no arquivo. Certifique-se de que contém apenas números separados por espaços.", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
                atualizarStatus("Formato inválido no arquivo");
            }
        }
    }
    
    /**
     * Calcula a multiplicação das matrizes
     */
    private void calcularMultiplicacao() {
        if (matrizA == null || matrizB == null) {
            JOptionPane.showMessageDialog(this, 
                "Selecione ambos os arquivos antes de calcular.", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Valida se as matrizes são quadradas
        if (!MatrixUtils.validarMatrizQuadrada(matrizA)) {
            JOptionPane.showMessageDialog(this, 
                "A matriz A não é quadrada!", 
                "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            atualizarStatus("Matriz A não é quadrada");
            return;
        }
        
        if (!MatrixUtils.validarMatrizQuadrada(matrizB)) {
            JOptionPane.showMessageDialog(this, 
                "A matriz B não é quadrada!", 
                "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            atualizarStatus("Matriz B não é quadrada");
            return;
        }
        
        // Verifica se as matrizes têm o mesmo tamanho
        if (matrizA.length != matrizB.length) {
            JOptionPane.showMessageDialog(this, 
                "As matrizes têm tamanhos diferentes!\n" +
                "Matriz A: " + matrizA.length + "x" + matrizA.length + "\n" +
                "Matriz B: " + matrizB.length + "x" + matrizB.length, 
                "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            atualizarStatus("Matrizes com tamanhos incompatíveis");
            return;
        }
        
        try {
            // Calcula a multiplicação
            matrizC = MatrixUtils.multiplicarMatrizes(matrizA, matrizB);
            
            // Exibe o resultado
            txtMatrizC.setText(MatrixUtils.matrizParaString(matrizC, "Matriz C (A × B)"));
            
            // Gera e exibe o passo a passo
            String passoAPasso = MatrixUtils.gerarPassoAPassoMultiplicacao(matrizA, matrizB);
            txtPassoAPasso.setText(passoAPasso);
            
            // Muda para a aba de passo a passo
            tabbedPane.setSelectedIndex(1);
            
            atualizarStatus("Multiplicação concluída com sucesso!");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Erro durante o cálculo: " + ex.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
            atualizarStatus("Erro durante o cálculo");
        }
    }
    
    /**
     * Calcula a soma das matrizes
     */
    private void calcularSoma() {
        if (matrizA == null || matrizB == null) {
            JOptionPane.showMessageDialog(this, 
                "Selecione ambos os arquivos antes de calcular.", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Valida se as matrizes são quadradas
        if (!MatrixUtils.validarMatrizQuadrada(matrizA)) {
            JOptionPane.showMessageDialog(this, 
                "A matriz A não é quadrada!", 
                "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            atualizarStatus("Matriz A não é quadrada");
            return;
        }
        
        if (!MatrixUtils.validarMatrizQuadrada(matrizB)) {
            JOptionPane.showMessageDialog(this, 
                "A matriz B não é quadrada!", 
                "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            atualizarStatus("Matriz B não é quadrada");
            return;
        }
        
        // Verifica se as matrizes têm o mesmo tamanho
        if (matrizA.length != matrizB.length) {
            JOptionPane.showMessageDialog(this, 
                "As matrizes têm tamanhos diferentes!\n" +
                "Matriz A: " + matrizA.length + "x" + matrizA.length + "\n" +
                "Matriz B: " + matrizB.length + "x" + matrizB.length, 
                "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            atualizarStatus("Matrizes com tamanhos incompatíveis");
            return;
        }
        
        try {
            // Calcula a soma
            matrizC = MatrixUtils.somarMatrizes(matrizA, matrizB);
            
            // Exibe o resultado
            txtMatrizC.setText(MatrixUtils.matrizParaString(matrizC, "Matriz C (A + B)"));
            
            // Gera e exibe o passo a passo
            String passoAPasso = MatrixUtils.gerarPassoAPassoSoma(matrizA, matrizB);
            txtPassoAPasso.setText(passoAPasso);
            
            // Muda para a aba de passo a passo
            tabbedPane.setSelectedIndex(1);
            
            atualizarStatus("Soma concluída com sucesso!");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Erro durante o cálculo: " + ex.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
            atualizarStatus("Erro durante o cálculo");
        }
    }
    
    /**
     * Atualiza a mensagem de status
     * @param mensagem nova mensagem de status
     */
    private void atualizarStatus(String mensagem) {
        lblStatus.setText(mensagem);
    }
} 
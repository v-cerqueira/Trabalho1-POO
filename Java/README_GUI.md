# 🖥️ Interface Gráfica - Calculadora de Matrizes

Documentação específica da **interface gráfica** do programa de multiplicação de matrizes, desenvolvida com Java Swing.

## 🎯 Visão Geral da Interface

A interface gráfica oferece uma experiência **intuitiva e moderna** para operações com matrizes, eliminando a necessidade de comandos de linha e proporcionando feedback visual imediato.

### ✨ Características Principais
- **Design responsivo** que se adapta ao tamanho da janela
- **Validação em tempo real** com feedback visual
- **Organização em abas** para melhor usabilidade
- **Tratamento robusto de erros** com diálogos informativos
- **Visualização clara** das matrizes e cálculos

## 🏗️ Arquitetura da Interface

### Classes Principais
```
MatrixCalculator.java    # Ponto de entrada da aplicação
MatrixUI.java           # Interface gráfica principal
MatrixUtils.java        # Lógica de negócio e cálculos
```

### Estrutura da Interface
```
┌─────────────────────────────────────────────────────────┐
│                    Título da Janela                     │
├─────────────────────────────────────────────────────────┤
│  [Seleção de Arquivos]                                 │
│  Matriz A: [arquivo.txt] [Selecionar Arquivo A]       │
│  Matriz B: [arquivo.txt] [Selecionar Arquivo B]       │
│  [Multiplicar Matrizes] [Somar Matrizes]              │
├─────────────────────────────────────────────────────────┤
│  [Matrizes] [Passo a Passo]                           │
│  ┌─────────┬─────────┬─────────┐                      │
│  │ Matriz A│ Matriz B│ Matriz C│                      │
│  │         │         │(Result) │                      │
│  └─────────┴─────────┴─────────┘                      │
├─────────────────────────────────────────────────────────┤
│  Status: Pronto para selecionar arquivos              │
└─────────────────────────────────────────────────────────┘
```

## 🎨 Componentes da Interface

### 1. Painel Superior - Controles
```java
// Seleção de arquivos com feedback visual
btnSelecionarA = new JButton("Selecionar Arquivo A");
btnSelecionarB = new JButton("Selecionar Arquivo B");

// Indicadores de status dos arquivos
lblArquivoA = new JLabel("Nenhum arquivo selecionado");
lblArquivoB = new JLabel("Nenhum arquivo selecionado");

// Botões de operação (habilitados apenas quando válido)
btnMultiplicar = new JButton("Multiplicar Matrizes");
btnSomar = new JButton("Somar Matrizes");
```

### 2. Aba "Matrizes" - Visualização
```java
// Áreas de texto para exibição das matrizes
txtMatrizA = new JTextArea();  // Matriz A
txtMatrizB = new JTextArea();  // Matriz B  
txtMatrizC = new JTextArea();  // Matriz Resultado

// Configuração para melhor visualização
txtMatrizA.setFont(new Font("Monospaced", Font.PLAIN, 12));
txtMatrizA.setEditable(false);
```

### 3. Aba "Passo a Passo" - Detalhes
```java
// Área para exibir cálculos detalhados
txtPassoAPasso = new JTextArea();
txtPassoAPasso.setFont(new Font("Monospaced", Font.PLAIN, 12));
txtPassoAPasso.setEditable(false);
```

### 4. Painel de Status - Feedback
```java
// Indicador de status em tempo real
lblStatus = new JLabel("Pronto para selecionar arquivos");
```

## 🔄 Fluxo de Interação

### 1. Inicialização
```java
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        MatrixUI ui = new MatrixUI();
        ui.setVisible(true);
    });
}
```

### 2. Seleção de Arquivos
```java
private void selecionarArquivo(char tipo) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter(
        "Arquivos de texto (*.txt)", "txt"));
    
    if (resultado == JFileChooser.APPROVE_OPTION) {
        // Carrega e valida matriz
        int[][] matriz = MatrixUtils.lerMatrizDeArquivo(arquivo);
        // Atualiza interface
    }
}
```

### 3. Validação e Cálculo
```java
private void calcularMultiplicacao() {
    // Validações
    if (!MatrixUtils.validarMatrizQuadrada(matrizA)) {
        // Exibe erro na interface
        return;
    }
    
    // Cálculo
    matrizC = MatrixUtils.multiplicarMatrizes(matrizA, matrizB);
    
    // Atualização da interface
    txtMatrizC.setText(MatrixUtils.matrizParaString(matrizC, "Matriz C"));
    txtPassoAPasso.setText(MatrixUtils.gerarPassoAPassoMultiplicacao(matrizA, matrizB));
}
```

## 🛡️ Tratamento de Erros na Interface

### Diálogos de Erro
```java
// Erro de arquivo não encontrado
JOptionPane.showMessageDialog(this, 
    "Erro ao ler arquivo: " + ex.getMessage(), 
    "Erro", JOptionPane.ERROR_MESSAGE);

// Erro de formato inválido
JOptionPane.showMessageDialog(this, 
    "Formato inválido no arquivo. Certifique-se de que contém apenas números separados por espaços.", 
    "Erro", JOptionPane.ERROR_MESSAGE);

// Erro de validação
JOptionPane.showMessageDialog(this, 
    "A matriz A não é quadrada!", 
    "Erro de Validação", JOptionPane.ERROR_MESSAGE);
```

### Validações Implementadas
1. **Arquivo existente**: Verifica se o arquivo selecionado existe
2. **Formato válido**: Confirma que contém apenas números separados por espaços
3. **Matriz quadrada**: Valida se a matriz tem mesmo número de linhas e colunas
4. **Tamanho compatível**: Verifica se ambas as matrizes têm o mesmo tamanho
5. **Dados numéricos**: Confirma que todos os valores são inteiros válidos

## 🎨 Design e Usabilidade

### Layout Responsivo
```java
// Layout principal com BorderLayout
JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

// GridBagLayout para controles superiores
JPanel panel = new JPanel(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(5, 5, 5, 5);
gbc.fill = GridBagConstraints.HORIZONTAL;
```

### Configurações Visuais
```java
// Configurações da janela principal
setTitle("Calculadora de Multiplicação de Matrizes");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setSize(1000, 700);
setLocationRelativeTo(null);  // Centraliza na tela

// Fontes monospaced para matrizes
txtMatrizA.setFont(new Font("Monospaced", Font.PLAIN, 12));
```

### Estados dos Componentes
```java
// Botões habilitados apenas quando válido
btnMultiplicar.setEnabled(matrizA != null && matrizB != null);
btnSomar.setEnabled(matrizA != null && matrizB != null);

// Atualização de status
atualizarStatus("Matriz A carregada: " + matriz.length + "x" + matriz.length);
```

## 📊 Exemplos de Uso da Interface

### Exemplo 1: Multiplicação Bem-sucedida
1. **Selecionar arquivos**: Clique em "Selecionar Arquivo A" e "Selecionar Arquivo B"
2. **Validação automática**: Interface verifica formato e compatibilidade
3. **Cálculo**: Clique em "Multiplicar Matrizes"
4. **Resultado**: Visualize matrizes na aba "Matrizes" e passo a passo na aba "Passo a Passo"

### Exemplo 2: Tratamento de Erro
1. **Arquivo inválido**: Selecione arquivo com formato incorreto
2. **Diálogo de erro**: Interface exibe mensagem explicativa
3. **Recuperação**: Usuário pode selecionar novo arquivo
4. **Feedback**: Status atualizado com informações do erro

## 🔧 Configurações Avançadas

### Personalização de Fontes
```java
// Fonte para matrizes (monospaced para alinhamento)
Font fontMatriz = new Font("Monospaced", Font.PLAIN, 12);

// Fonte para títulos
Font fontTitulo = new Font("Arial", Font.BOLD, 14);
```

### Configuração de Cores
```java
// Cores para diferentes estados
Color corSucesso = new Color(76, 175, 80);  // Verde
Color corErro = new Color(244, 67, 54);     // Vermelho
Color corAviso = new Color(255, 152, 0);    // Laranja
```

### Configuração de Bordas
```java
// Bordas para organizar componentes
panel.setBorder(BorderFactory.createTitledBorder("Seleção de Arquivos"));
panel.setBorder(BorderFactory.createEtchedBorder());
```

## 🚀 Melhorias Futuras da Interface

### Funcionalidades Planejadas
- [ ] **Drag and Drop** de arquivos
- [ ] **Preview** das matrizes antes de carregar
- [ ] **Zoom** para matrizes grandes
- [ ] **Temas visuais** personalizáveis
- [ ] **Atalhos de teclado** para operações comuns
- [ ] **Histórico** de operações recentes

### Melhorias de UX
- [ ] **Progress bar** para operações longas
- [ ] **Tooltips** explicativos
- [ ] **Undo/Redo** para operações
- [ ] **Exportação** de resultados
- [ ] **Impressão** de matrizes e cálculos

## 📝 Dicas de Desenvolvimento

### Boas Práticas Implementadas
1. **Separação de responsabilidades**: UI separada da lógica de negócio
2. **Tratamento de exceções**: Captura e exibe erros adequadamente
3. **Validação em camadas**: Múltiplos níveis de verificação
4. **Feedback constante**: Usuário sempre informado do status
5. **Interface responsiva**: Não trava durante operações

### Padrões de Design Utilizados
- **MVC (Model-View-Controller)**: Separação clara de responsabilidades
- **Observer Pattern**: Atualização automática da interface
- **Factory Pattern**: Criação de componentes de interface
- **Strategy Pattern**: Diferentes algoritmos de cálculo

---

**Interface desenvolvida com foco em usabilidade e experiência do usuário** 
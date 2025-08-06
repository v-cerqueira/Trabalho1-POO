# 🧮 Calculadora de Matrizes - Java

Um programa Java completo para operações com matrizes quadradas, incluindo **interface gráfica moderna** e **cálculos detalhados** com passo a passo.

## ✨ Funcionalidades Principais

### 🎯 Interface Gráfica (GUI)
- **Interface moderna** usando Swing com design responsivo
- **Seleção visual de arquivos** através de diálogos gráficos
- **Visualização em tempo real** das matrizes A, B e C
- **Abas organizadas** para matrizes e passo a passo
- **Feedback visual** com mensagens de status em tempo real

### 🔢 Operações Matemáticas
- **Multiplicação de matrizes** com passo a passo detalhado
- **Soma de matrizes** com cálculos explicativos
- **Validação automática** de matrizes quadradas
- **Verificação de compatibilidade** de tamanhos

### 📁 Gerenciamento de Arquivos
- **Leitura de arquivos .txt** externos
- **Validação de formato** automática
- **Tratamento de erros** robusto
- **Suporte a matrizes de qualquer tamanho** (quadradas)

## 🏗️ Arquitetura do Projeto

```
Java/
├── MatrixCalculator.java    # Classe principal (main)
├── MatrixUI.java           # Interface gráfica completa
├── MatrixUtils.java        # Utilitários e cálculos
├── matrizA.txt            # Arquivo de exemplo matriz A
├── matrizB.txt            # Arquivo de exemplo matriz B
├── README.md              # Documentação principal
└── README_GUI.md          # Documentação da interface
```

## 🚀 Como Executar

### 1. Compilar o Programa
```bash
javac *.java
```

### 2. Executar a Interface Gráfica
```bash
java MatrixCalculator
```

### 3. Usar o Programa
1. **Selecionar Matriz A**: Clique em "Selecionar Arquivo A"
2. **Selecionar Matriz B**: Clique em "Selecionar Arquivo B"
3. **Escolher Operação**: 
   - "Multiplicar Matrizes" para multiplicação
   - "Somar Matrizes" para soma
4. **Visualizar Resultados**: Navegue entre as abas para ver matrizes e passo a passo

## 📋 Formato dos Arquivos

### Estrutura do Arquivo .txt
```
1 2 3
4 5 6
7 8 9
```

### Regras Importantes
- ✅ **Cada linha** = uma linha da matriz
- ✅ **Números separados por espaços** (não vírgulas)
- ✅ **Matriz quadrada** (mesmo número de linhas e colunas)
- ✅ **Apenas números inteiros**
- ❌ **Não use vírgulas, pontos e vírgulas ou outros separadores**

## 🖥️ Interface Gráfica Detalhada

### Painel Superior - Controles
- **Seleção de Arquivos**: Botões para escolher matrizes A e B
- **Indicadores de Status**: Mostram arquivos carregados
- **Botões de Operação**: Multiplicar ou Somar matrizes
- **Validação Visual**: Botões habilitados apenas quando válido

### Aba "Matrizes"
- **Matriz A**: Visualização da primeira matriz
- **Matriz B**: Visualização da segunda matriz
- **Matriz C**: Resultado da operação escolhida
- **Formatação**: Números alinhados para fácil leitura

### Aba "Passo a Passo"
- **Cálculo Detalhado**: Mostra cada operação individual
- **Fórmulas**: Exibe uso das funções `soma()` e `multiplicacao()`
- **Resultados Intermediários**: Valores de cada etapa
- **Formatação Clara**: Fácil de acompanhar o processo

### Painel de Status
- **Mensagens Informativas**: Status atual do programa
- **Feedback de Erros**: Explicações claras de problemas
- **Confirmações**: Notificações de sucesso

## 🔧 Funcionalidades Técnicas

### Validações Implementadas
```java
// Verificação de matriz quadrada
public static boolean validarMatrizQuadrada(int[][] matriz)

// Verificação de compatibilidade de tamanhos
if (matrizA.length != matrizB.length)

// Validação de dados numéricos
Integer.parseInt(valores[i])
```

### Funções Matemáticas Básicas
```java
// Função soma - usada em todas as operações
public static int soma(int a, int b) {
    return a + b;
}

// Função multiplicação - usada na multiplicação de matrizes
public static int multiplicacao(int a, int b) {
    return a * b;
}
```

### Algoritmo de Multiplicação
```java
// Multiplicação usando apenas soma() e multiplicacao()
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        int resultado = 0;
        for (int k = 0; k < n; k++) {
            int produto = multiplicacao(matrizA[i][k], matrizB[k][j]);
            resultado = soma(resultado, produto);
        }
        matrizC[i][j] = resultado;
    }
}
```

## 📊 Exemplos Práticos

### Exemplo 1: Multiplicação 2x2

**matrizA.txt:**
```
1 2
3 4
```

**matrizB.txt:**
```
5 6
7 8
```

**Resultado (Matriz C):**
```
19 22
43 50
```

**Passo a Passo:**
```
C[0][0] = multiplicacao(A[0][0], B[0][0]) = 5 + multiplicacao(A[0][1], B[1][0]) = 14 = 19
C[0][1] = multiplicacao(A[0][0], B[0][1]) = 6 + multiplicacao(A[0][1], B[1][1]) = 16 = 22
C[1][0] = multiplicacao(A[1][0], B[0][0]) = 15 + multiplicacao(A[1][1], B[1][0]) = 28 = 43
C[1][1] = multiplicacao(A[1][0], B[0][1]) = 18 + multiplicacao(A[1][1], B[1][1]) = 32 = 50
```

### Exemplo 2: Soma 2x2

**matrizA.txt:**
```
1 2
3 4
```

**matrizB.txt:**
```
5 6
7 8
```

**Resultado (Matriz C):**
```
6  8
10 12
```

**Passo a Passo:**
```
C[0][0] = soma(A[0][0], B[0][0]) = soma(1, 5) = 6
C[0][1] = soma(A[0][1], B[0][1]) = soma(2, 6) = 8
C[1][0] = soma(A[1][0], B[1][0]) = soma(3, 7) = 10
C[1][1] = soma(A[1][1], B[1][1]) = soma(4, 8) = 12
```

## 🛡️ Tratamento de Erros

### Erros de Arquivo
- **Arquivo não encontrado**: Diálogo específico com sugestões
- **Formato inválido**: Explicação detalhada do formato esperado
- **Erro de leitura**: Informações sobre problemas de I/O
- **Dados não numéricos**: Identificação de valores inválidos

### Erros de Validação
- **Matriz não quadrada**: Especifica qual matriz e suas dimensões
- **Tamanhos incompatíveis**: Mostra dimensões de ambas as matrizes
- **Matriz vazia**: Tratamento para arquivos vazios
- **Linhas inconsistentes**: Verificação de formato uniforme

### Mensagens de Erro Exemplos
```
❌ "A matriz A não é quadrada!"
❌ "As matrizes têm tamanhos diferentes!"
❌ "Formato inválido no arquivo. Certifique-se de que contém apenas números separados por espaços."
✅ "Multiplicação concluída com sucesso!"
```

## 🎨 Características da Interface

### Design e Usabilidade
- **Look and Feel nativo**: Adapta-se ao sistema operacional
- **Layout responsivo**: Se ajusta ao tamanho da janela
- **Fontes monospaced**: Para melhor visualização de matrizes
- **Scroll automático**: Para matrizes grandes
- **Organização em abas**: Separação clara de funcionalidades

### Feedback Visual
- **Indicadores de status**: Mostram progresso das operações
- **Validação em tempo real**: Erros aparecem imediatamente
- **Confirmações visuais**: Sucessos são claramente indicados
- **Desabilitação inteligente**: Botões só ativos quando apropriado

## 📝 Requisitos do Sistema

### Software
- **Java 8 ou superior**
- **JRE (Java Runtime Environment)**
- **Sistema operacional**: Windows, macOS, Linux

### Arquivos
- **Formato**: Arquivos .txt com matrizes
- **Codificação**: UTF-8 recomendado
- **Tamanho**: Suporte a matrizes de qualquer tamanho (quadradas)

## 🔍 Vantagens do Programa

### Para Usuários
1. **Interface intuitiva**: Não requer conhecimento de linha de comando
2. **Feedback imediato**: Vê resultados instantaneamente
3. **Validação automática**: Previne erros antes do cálculo
4. **Visualização clara**: Matrizes organizadas e legíveis
5. **Passo a passo educativo**: Entende como os cálculos são feitos

### Para Desenvolvedores
1. **Código modular**: Separação clara de responsabilidades
2. **Tratamento robusto de erros**: Cobre todos os casos edge
3. **Documentação completa**: Código bem comentado
4. **Extensibilidade**: Fácil adicionar novas operações
5. **Testabilidade**: Funções isoladas e testáveis

## 🚀 Próximos Passos

### Possíveis Melhorias
- [ ] **Suporte a matrizes retangulares**
- [ ] **Mais operações** (determinante, inversa, transposta)
- [ ] **Exportação de resultados** para diferentes formatos
- [ ] **Histórico de operações**
- [ ] **Temas visuais** personalizáveis
- [ ] **Suporte a números decimais**

### Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para:
- Reportar bugs
- Sugerir melhorias
- Adicionar novas funcionalidades
- Melhorar a documentação

---

**Desenvolvido com ❤️ em Java usando Swing** 
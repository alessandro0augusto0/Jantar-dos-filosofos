# Jantar dos Filósofos - Os 5 Magos de O Senhor dos Anéis 🧙‍♂️

Bem-vindo ao repositório do **Jantar dos Filósofos**, uma simulação visual e interativa do clássico problema de concorrência em sistemas operacionais, com um toque mágico inspirado na trilogia **O Senhor dos Anéis**! Aqui, os filósofos são representados pelos 5 magos: **Saruman**, **Gandalf**, **Radagast**, **Alatar** e **Pallando**.

Este projeto foi desenvolvido em Java e utiliza interfaces gráficas para tornar a experiência mais imersiva e divertida. Além de resolver o problema do Jantar dos Filósofos, o programa exibe estatísticas em tempo real e resultados finais, tanto na interface gráfica quanto no terminal.

---

## 🎮 Como Funciona?

O problema do Jantar dos Filósofos é um clássico desafio de concorrência, onde cinco filósofos (ou, neste caso, magos) estão sentados em uma mesa redonda. Cada mago alterna entre **pensar** e **comer**. Para comer, um mago precisa pegar dois cajados (garfos) — um à sua esquerda e outro à sua direita. O desafio é evitar **deadlocks** (quando todos os magos pegam um cajado e ficam esperando pelo outro) e **starvation** (quando um mago nunca consegue comer).

Neste projeto, os magos são representados visualmente, e você pode acompanhar suas ações em tempo real!

---

## 🖼️ Interface Gráfica

### Tela Inicial
![Tela Inicial](https://github.com/user-attachments/assets/55c877b3-5d30-4df9-b7a3-6002f67837c5)

A tela inicial apresenta um botão **Start** para iniciar a simulação. Ao clicar, você é levado para a tela de execução, onde os magos começam a pensar e comer.

---

### Tela de Execução
![Tela de Execução](https://github.com/user-attachments/assets/80678b01-e9bd-445c-92c2-f877cef10a4a)
![Tela de Execução](https://github.com/user-attachments/assets/d2acae21-66f1-42cf-8068-c058de4a7c18)
![Tela de Execução](https://github.com/user-attachments/assets/e1825156-643f-453f-8423-2ab1e384a244)
![Tela de Execução](https://github.com/user-attachments/assets/ffa4e7ba-b024-47d1-9388-cfbee6e89328)

Na tela de execução, os magos são representados por imagens que mudam conforme suas ações:
- **Pensando**: O mago está refletindo.
- **Comendo**: O mago está comendo, utilizando dois cajados.

Para interagir com a simulação, passe o cursor do mouse sobre **"O Um Anel"** para revelar os botões **Iniciar** e **Parar**.

---

### Tela de Resultados
![Tela de Resultados](https://github.com/user-attachments/assets/ffc550df-14bb-4846-b134-75dac03a4841)

Ao clicar em **Parar**, a simulação é interrompida, e uma tela de resultados é exibida. Aqui, você pode ver quantas vezes cada mago comeu, o tempo médio de espera e o tempo máximo de espera.

---

## 🖥️ Terminal

Além da interface gráfica, o programa também exibe informações detalhadas no terminal, como:

```plaintext
Mago 1 está comendo.
Mago 1 comeu 38 vezes.
Mago 1 está pensando.
Mago 5 está comendo.
Mago 2 não come há 315 ms.
Mago 5 comeu 38 vezes.
Mago 5 está pensando.
Mago 4 está comendo.
Mago 1 não come há 556 ms.
Mago 4 comeu 38 vezes.
Mago 4 está pensando.
Mago 3 está comendo.
Mago 5 não come há 405 ms.
Mago 4 não come há 493 ms.
Mago 3 comeu 39 vezes.
```

E os resultados finais:

```plaintext
Tempo médio de espera do Mago 5: 571
Tempo médio de espera do Mago 2: 532
Tempo médio de espera do Mago 4: 543
Tempo médio de espera do Mago 1: 500
Tempo médio de espera do Mago 3: 599
Tempo máximo de espera do Mago 5: 1580
Tempo máximo de espera do Mago 2: 1549
Tempo máximo de espera do Mago 4: 1631
Tempo máximo de espera do Mago 1: 1933
Tempo máximo de espera do Mago 3: 1816
```


---

## 🛠️ Estrutura do Código

O projeto é dividido em três pacotes principais:

### 1. **`ui` (Interface Gráfica)**
- **`MainApp`**: Classe principal que gerencia a interface gráfica, incluindo a tela inicial, a tela de execução e a tela de resultados.
- **Imagens e Sons**: O programa utiliza imagens e sons temáticos para criar uma experiência imersiva.

### 2. **`magosFunctions` (Lógica do Programa)**
- **`MagosExecution`**: Gerencia a inicialização e parada dos magos.
- **`Mago`**: Classe que representa cada mago, contendo a lógica de pensar, comer e calcular estatísticas.

### 3. **Recursos**
- **Imagens**: Todas as imagens dos magos, fundos e o anel.
- **Sons**: Efeitos sonoros para iniciar, parar e finalizar a simulação.

---

## 🎯 Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/alessandro0augusto0/Jantar-dos-filosofos.git

Abra o projeto em uma IDE Java (como Eclipse ou IntelliJ).

Execute a classe `MainApp` no pacote `ui`.

Divirta-se acompanhando os magos em sua jornada!

## 🧙‍♂️ Inspiração
Este projeto foi inspirado no clássico problema do Jantar dos Filósofos, mas com um toque de fantasia. Os 5 magos de O Senhor dos Anéis foram escolhidos para trazer vida e magia ao desafio técnico.

## 📊 Estatísticas
O programa calcula e exibe:
- Quantidade de vezes que cada mago comeu.
- Tempo médio de espera para comer.
- Tempo máximo de espera para comer.

## 🎉 Conclusão
Este projeto combina conceitos avançados de concorrência com uma interface gráfica divertida e temática. É uma ótima maneira de visualizar e entender o problema do Jantar dos Filósofos de forma lúdica e interativa.

Seja bem-vindo ao mundo mágico dos magos! 🌟

---

**Desenvolvido por:** [Alessandro Augusto](https://github.com/alessandro0augusto0)  
**Inspiração:** O Senhor dos Anéis  
**Tecnologias:** Java, Swing, Concorrência
package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import magosFunctions.MagosExecution;

/**
 * @author Alessandro Augusto
 * @since 05/03/2025
 */

public class MainApp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static ArrayList<JLabel> labelMagos = new ArrayList<>();
    private static ArrayList<JLabel> labelMagosContadorComidas = new ArrayList<>();
    private static ArrayList<JLabel> labelMagosTempoSemComer = new ArrayList<>();
    private static ArrayList<JLabel> labelMagosTempoMedioEspera = new ArrayList<>();
    private static ArrayList<JLabel> labelMagosTempoMaximoEspera = new ArrayList<>();
    private static final String[] magosNomes = { "Saruman", "Gandalf", "Radagast", "Alatar", "Pallando" };

    enum Status {
        STOPPED, STARTED
    };

    private static Status status = Status.STOPPED;
    private JButton btnIniciar;
    private JButton btnParar;

    // Variáveis para controle de áudio
    private Clip currentClip;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainApp frame = new MainApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainApp() {
        setResizable(false);
        setTitle("Os 5 Magos de O Senhor dos Anéis");
        setIconImage(loadAndResizeImage("/images/anel.png", 32, 32).getImage()); // Ícone da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Interface inicial
        mostrarInterfaceInicial();
    }

    private void mostrarInterfaceInicial() {
        stopSound(); // Para qualquer som que esteja tocando
        contentPane.removeAll(); // Limpa o conteúdo anterior

        // Adiciona uma imagem de fundo
        JLabel background = new JLabel("");
        background.setBounds(0, 0, 800, 600);
        background.setIcon(loadAndResizeImage("/images/fundo_inicial.png", 800, 600)); // Redimensiona o fundo
        contentPane.add(background);

        // Botão Start
        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopSound(); // Para o som atual
                playSound("/sounds/magic_sound.wav"); // Toca o som magic_sound.wav
                contentPane.removeAll();
                adicionarMagos();
                adicionarBotoes();
                contentPane.revalidate();
                contentPane.repaint();
            }
        });
        btnStart.setBounds(350, 250, 100, 30);
        contentPane.add(btnStart);

        // Toca a música inicial
        playSound("/sounds/start_sound.wav");

        contentPane.revalidate();
        contentPane.repaint();
    }

    private void adicionarMagos() {
        // Posições dos magos na interface
        int[][] posicoes = { { 350, 50 }, { 550, 150 }, { 450, 350 }, { 250, 350 }, { 150, 150 } };

        // Tamanho das imagens dos magos
        int width = 100; // Largura desejada
        int height = 100; // Altura desejada

        for (int i = 0; i < 5; i++) {
            // Adiciona a imagem do mago
            JLabel lblMago = new JLabel("");
            lblMago.setBounds(posicoes[i][0], posicoes[i][1], width, height);
            lblMago.setIcon(loadAndResizeImage("/images/mago_" + (i + 1) + "_pensando.png", width, height));
            labelMagos.add(lblMago);
            contentPane.add(lblMago);

            // Adiciona os labels de estatísticas
            adicionarEstatisticas(i, posicoes[i][0], posicoes[i][1]);
        }

        // Adiciona uma imagem de fundo
        JLabel background = new JLabel("");
        background.setBounds(0, 0, 800, 600);
        background.setIcon(loadAndResizeImage("/images/fundo_magico.png", 800, 600)); // Redimensiona o fundo
        contentPane.add(background);
    }

    private void adicionarEstatisticas(int index, int x, int y) {
        int labelWidth = 250; // Aumenta a largura dos labels
        int labelHeight = 14;

        JLabel lblContador = new JLabel("") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawString(getText(), 1, 11); // Sombra preta
                g.setColor(Color.WHITE);
                g.drawString(getText(), 0, 10); // Texto branco
            }
        };
        lblContador.setBounds(x, y + 110, labelWidth, labelHeight);
        labelMagosContadorComidas.add(lblContador);
        contentPane.add(lblContador);

        JLabel lblTempoSemComer = new JLabel("") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawString(getText(), 1, 11); // Sombra preta
                g.setColor(Color.WHITE);
                g.drawString(getText(), 0, 10); // Texto branco
            }
        };
        lblTempoSemComer.setBounds(x, y + 130, labelWidth, labelHeight);
        labelMagosTempoSemComer.add(lblTempoSemComer);
        contentPane.add(lblTempoSemComer);

        JLabel lblTempoMedio = new JLabel("") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawString(getText(), 1, 11); // Sombra preta
                g.setColor(Color.WHITE);
                g.drawString(getText(), 0, 10); // Texto branco
            }
        };
        lblTempoMedio.setBounds(x, y + 150, labelWidth, labelHeight);
        labelMagosTempoMedioEspera.add(lblTempoMedio);
        contentPane.add(lblTempoMedio);

        JLabel lblTempoMaximo = new JLabel("") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawString(getText(), 1, 11); // Sombra preta
                g.setColor(Color.WHITE);
                g.drawString(getText(), 0, 10); // Texto branco
            }
        };
        lblTempoMaximo.setBounds(x, y + 170, labelWidth, labelHeight);
        labelMagosTempoMaximoEspera.add(lblTempoMaximo);
        contentPane.add(lblTempoMaximo);
    }

    private void adicionarBotoes() {
        btnIniciar = new JButton("Iniciar");
        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (status == Status.STOPPED) {
                    stopSound(); // Para o som atual
                    MagosExecution.magosStart();
                    StatusStart();
                    btnIniciar.setVisible(false);
                    btnParar.setVisible(true);
                }
            }
        });
        btnIniciar.setBounds(350, 250, 100, 30); // Centraliza o botão "Iniciar"
        btnIniciar.setVisible(true); // Garante que o botão "Iniciar" esteja visível
        contentPane.add(btnIniciar);

        btnParar = new JButton("Parar");
        btnParar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (status == Status.STARTED) {
                    stopSound(); // Para o som atual
                    MagosExecution.magosStop();
                    StatusStop();
                    mostrarResultados();
                }
            }
        });
        btnParar.setBounds(350, 300, 100, 30); // Centraliza o botão "Parar"
        btnParar.setVisible(false); // Inicialmente invisível
        contentPane.add(btnParar);

        contentPane.revalidate();
        contentPane.repaint();
    }

    private void mostrarResultados() {
        stopSound(); // Para o som atual
        JFrame resultadosFrame = new JFrame("Resultados");
        resultadosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultadosFrame.setBounds(100, 100, 800, 600); // Tamanho da tela de resultados
        resultadosFrame.setLocationRelativeTo(null); // Centraliza a janela na tela
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(null);
        resultadosFrame.setContentPane(panel);

        // Adiciona os resultados
        for (int i = 0; i < magosNomes.length; i++) {
            JLabel lblResultado = new JLabel(magosNomes[i] + " comeu: " + labelMagosContadorComidas.get(i).getText().split(":")[1].trim());
            lblResultado.setBounds(10, 10 + (i * 30), 380, 25);
            lblResultado.setForeground(Color.WHITE); // Texto branco para contrastar com o fundo
            panel.add(lblResultado);
        }

        // Adiciona uma imagem de fundo
        JLabel background = new JLabel("");
        background.setBounds(0, 0, 800, 600);
        background.setIcon(loadAndResizeImage("/images/fundo_resultados.png", 800, 600)); // Redimensiona o fundo
        panel.add(background);

        // Botão OK
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopSound(); // Para o som atual
                resultadosFrame.dispose(); // Fecha a tela de resultados
                System.exit(0); // Fecha o programa
            }
        });
        btnOk.setBounds(350, 500, 100, 30);
        panel.add(btnOk);

        // Toca a música de resultados
        playSound("/sounds/ok_sound.wav");

        resultadosFrame.setVisible(true);
    }

    // Método para carregar e redimensionar imagens
    private static ImageIcon loadAndResizeImage(String path, int width, int height) {
        try {
            // Carrega a imagem original
            ImageIcon originalIcon = new ImageIcon(MainApp.class.getResource(path));
            Image originalImage = originalIcon.getImage();

            // Redimensiona a imagem para o tamanho desejado
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Retorna a imagem redimensionada como um ImageIcon
            return new ImageIcon(resizedImage);
        } catch (Exception e) {
            System.err.println("Erro ao carregar a imagem: " + path);
            e.printStackTrace();
            return null;
        }
    }

    // Métodos para controle de áudio
    private void playSound(String soundFile) {
        try {
            // Para o som atual, se houver um tocando
            stopSound();

            // Carrega o arquivo de som
            URL url = MainApp.class.getResource(soundFile);
            if (url == null) {
                System.err.println("Arquivo de som não encontrado: " + soundFile);
                return;
            }

            // Abre o áudio
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            currentClip = AudioSystem.getClip();
            currentClip.open(audioInputStream);

            // Toca o som
            currentClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void stopSound() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop(); // Para o som atual
            currentClip.close(); // Libera os recursos
        }
    }

    // Métodos para atualizar a interface
    public static void MagoPensar(int index) {
        labelMagos.get(index).setIcon(loadAndResizeImage("/images/mago_" + (index + 1) + "_pensando.png", 100, 100));
    }

    public static void MagoComer(int index) {
        labelMagos.get(index).setIcon(loadAndResizeImage("/images/mago_" + (index + 1) + "_comendo.png", 100, 100));
    }

    public static void MagoOcioso(int index) {
        labelMagos.get(index).setIcon(loadAndResizeImage("/images/mago_" + (index + 1) + "_pensando.png", 100, 100));
    }

    public static void MagoContadorComidasUpdate(int index, int contador) {
        labelMagosContadorComidas.get(index).setText(magosNomes[index] + " comeu: " + contador + " vezes");
    }

    public static void MagoTempoSemComerUpdate(int index, long tempo) {
        labelMagosTempoSemComer.get(index).setText(magosNomes[index] + " sem comer: " + tempo + " ms");
    }

    public static void MagoTempoMedioEsperaUpdate(int index, long tempo) {
        labelMagosTempoMedioEspera.get(index).setText("Média: " + tempo + " ms");
    }

    public static void MagoTempoMaximoEsperaUpdate(int index, long tempo) {
        labelMagosTempoMaximoEspera.get(index).setText("Máximo: " + tempo + " ms");
    }

    public static void StatusStart() {
        status = Status.STARTED;
    }

    public static void StatusStop() {
        status = Status.STOPPED;
    }
}

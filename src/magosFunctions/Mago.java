package magosFunctions;

import java.util.ArrayList;
import java.util.List;
import ui.MainApp;

public class Mago extends Thread {

    private int idThread, contEat = 0;
    private long start = System.currentTimeMillis();
    private long timeWithoutEating = 0;
    private long timeAux = System.currentTimeMillis();
    private long thinkingEatingTime = 0;
    private long subAux = 0;
    private long timeout = 180000; // Tempo máximo de execução (3 minutos)
    private List<List<Long>> timeData;
    private volatile boolean running = true;

    public Mago(int num, int id) {
        this.idThread = id;
        this.timeData = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            timeData.add(new ArrayList<>());
        }
    }

    @Override
    public void run() {
        while (running) {
            // Limita o tempo de execução para testes
            if (System.currentTimeMillis() - start > timeout) {
                MainApp.StatusStop();
                break;
            }

            // Ciclo de vida do mago: pensar e comer
            pensar();

            // Garante a ordem para evitar deadlock
            synchronized (MagosExecution.getCajados()[idThread]) {
                synchronized (MagosExecution.getCajados()[(idThread + 1) % 5]) {
                    comer();
                }
            }

            timeWithoutEating = System.currentTimeMillis();
        }

        // Exibe estatísticas ao final
        calcularEstatisticas();
    }

    private void pensar() {
        if (!running) return;
        System.out.println("Mago " + (idThread + 1) + " está pensando.");
        MainApp.MagoPensar(idThread);
        try {
            // Gera um tempo aleatório entre 100 e 500 ms
            Thread.sleep((long) (Math.random() * 400 + 100));
            MainApp.MagoOcioso(idThread);

            // Inicia o tempo entre pensar e comer
            thinkingEatingTime = System.currentTimeMillis();

            // Tempo sem comer
            timeWithoutEating = System.currentTimeMillis() - timeAux;
            MainApp.MagoTempoSemComerUpdate(idThread, timeWithoutEating);
            System.out.println("Mago " + (idThread + 1) + " não come há " + timeWithoutEating + " ms.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaura o estado de interrupção
        }
    }

    private void comer() {
        if (!running) return;
        System.out.println("Mago " + (idThread + 1) + " está comendo.");
        MainApp.MagoComer(idThread);
        try {
            // Tempo de espera entre pensar e comer
            subAux = System.currentTimeMillis() - thinkingEatingTime;
            timeData.get(idThread).add(subAux);

            // Gera um tempo aleatório entre 100 e 500 ms
            Thread.sleep((long) (Math.random() * 400 + 100));
            MainApp.MagoOcioso(idThread);

            // Reinicia o contador de tempo sem comer
            timeAux = System.currentTimeMillis();

            // Contador de vezes que comeu
            contEat++;
            MainApp.MagoContadorComidasUpdate(idThread, contEat);
            System.out.println("Mago " + (idThread + 1) + " comeu " + contEat + " vezes.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaura o estado de interrupção
        }
    }

    private void calcularEstatisticas() {
        // Calcula o tempo médio de espera
        long sum = 0;
        for (List<Long> tD : timeData) {
            for (Long time : tD) {
                sum += time;
            }
        }
        MainApp.MagoTempoMedioEsperaUpdate(idThread, (sum / contEat));
        System.out.println("Tempo médio de espera do Mago " + (idThread + 1) + ": " + (sum / contEat));

        // Calcula o tempo máximo de espera
        long max = Long.MIN_VALUE;
        for (List<Long> tD : timeData) {
            for (Long time : tD) {
                if (time > max) {
                    max = time;
                }
            }
        }
        MainApp.MagoTempoMaximoEsperaUpdate(idThread, max);
        System.out.println("Tempo máximo de espera do Mago " + (idThread + 1) + ": " + max);
    }

    public void stopMago() {
        running = false;
        interrupt();
    }
}

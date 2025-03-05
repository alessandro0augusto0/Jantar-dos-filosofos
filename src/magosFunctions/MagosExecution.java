package magosFunctions;

public class MagosExecution {

    private static int numMagos = 5;
    private static Object[] cajados = new Object[numMagos];
    private static Mago[] magos = new Mago[numMagos];

    public static void magosStart() {
        for (int i = 0; i < numMagos; i++) {
            cajados[i] = new Object();
        }

        for (int i = 0; i < numMagos; i++) {
            magos[i] = new Mago(numMagos, i);
        }

        for (Mago mago : magos) {
            mago.start();
        }
    }

    public static void magosStop() {
        for (Mago mago : magos) {
            mago.stopMago();
        }
    }

    public static Object[] getCajados() {
        return cajados;
    }
}

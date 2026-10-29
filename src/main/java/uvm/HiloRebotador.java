package uvm;

import javax.swing.JLabel;

public class HiloRebotador implements Runnable {
    private JLabel[][] matriz;
    private int fila;
    private int col;
    private int dirFila = 1; // 1 baja, -1 sube
    private int dirCol = 1;  // 1 derecha, -1 izquierda
    private int filasMax;
    private int colsMax;
    private volatile boolean activo = true;

    public HiloRebotador(JLabel[][] matriz, int filaInicial, int colInicial) {
        this.matriz = matriz;
        this.fila = filaInicial;
        this.col = colInicial;
        this.filasMax = matriz.length;
        this.colsMax = matriz[0].length;
    }

    @Override
    public void run() {
        try {
            while (activo) {
                synchronized (matriz) {
                    matriz[fila][col].setText("");

                    fila += dirFila;
                    col += dirCol;

                    if (fila <= 0 || fila >= filasMax - 1) {
                        dirFila = -dirFila;
                    }

                    if (col <= 0 || col >= colsMax - 1) {
                        dirCol = -dirCol;
                    }

                    matriz[fila][col].setText("@");
                }
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void detener() {
        activo = false;
    }
}
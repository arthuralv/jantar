package mesa_dos_filosofos;

public class Mesa {

    final static int qtdFilosofos = 5;
    final static int headFilosofo = 0;
    final static int tailFilosofo = qtdFilosofos - 1;
    final static int pensando = 1;
    final static int comendo = 2;
    final static int comFome = 3;

    boolean[] garfos = new boolean[qtdFilosofos];
    int[] filosofos = new int[qtdFilosofos];
    int[] tentativas = new int[qtdFilosofos];

    public Mesa() {
        for (int i = 0; i < 5; i++) {
            garfos[i] = true;
            filosofos[i] = pensando;
            tentativas[i] = 0;
        }
    }

    public synchronized void getTalheres(int filosofo) {
        filosofos[filosofo] = comFome;

        while (filosofos[getLeft(filosofo)] == comendo || filosofos[getRight(filosofo)] == comendo) {
            try {
                tentativas[filosofo]++;
                wait();
            } catch (InterruptedException e) {
            }
        }

        System.out.println("O filosofo desistiu apos passar muito tempo esperando");

        tentativas[filosofo] = 0;
        garfos[talherLeft(filosofo)] = false;
        garfos[talherRight(filosofo)] = false;
        filosofos[filosofo] = comendo;

        showStatus();
        showTalheres();
        showTentativas();
    }

    public synchronized void retornandoTalheres(int filosofo) {
        garfos[talherLeft(filosofo)] = true;
        garfos[talherRight(filosofo)] = true;

        if (filosofos[getLeft(filosofo)] == comFome || filosofos[getRight(filosofo)] == comFome) {
            notifyAll();
        }

        filosofos[filosofo] = pensando;

        showStatus();
        showTalheres();
        showTentativas();
    }

    public int getRight(int filosofo) {
        int direito;

        if (filosofo == tailFilosofo) {
            direito = headFilosofo;
        } else {
            direito = filosofo + 1;
        }

        return direito;
    }

    public int getLeft(int filosofo) {
        int esquerdo;

        if (filosofo == headFilosofo) {
            esquerdo = tailFilosofo;
        } else {
            esquerdo = filosofo - 1;
        }

        return esquerdo;
    }

    public int talherLeft(int filosofo) {
        int talherLeft = filosofo;
        return talherLeft;
    }

    public int talherRight(int filosofo) {
        int talherRight;

        if (filosofo == tailFilosofo) {
            talherRight = 0;
        } else {
            talherRight = filosofo + 1;
        }

        return talherRight;
    }


    public void showTentativas() {
        System.out.print("filosofos que tentaram comer = (");

        for (int i = 0; i < qtdFilosofos; i++) {
            if (i < qtdFilosofos - 1) {
                System.out.print(filosofos[i] + ", ");
            } else {
                System.out.print(filosofos[i]);
            }
        }

        System.out.println(")");
    }

    public void showStatus() {
        String texto = "vazio";
        System.out.print("Status em sequencia = (");

        for (int i = 0; i < qtdFilosofos; i++) {
            switch (filosofos[i]) {
                case pensando:
                    texto = "pensando";
                    break;
                case comFome:
                    texto = "comFome";
                    break;
                case comendo:
                    texto = "comendo";
                    break;
            }
            if (i < qtdFilosofos - 1) {
                texto += ", ";
            }
            System.out.print(texto);
        }

        System.out.println(")");
    }

    public void showTalheres() {
        String garfo = "vazio";
        System.out.print("talheres em sequencia = (");

        for (int i = 0; i < qtdFilosofos; i++) {
            if (garfos[i]) {
                garfo = "LIVRE";
            } else {
                garfo = "OCUPADO";
            }
            System.out.print(garfo + " ");
        }
        
        System.out.println("]");
    }

    
}
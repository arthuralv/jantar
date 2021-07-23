package mesa_dos_filosofos;

public class Filosofo extends Thread {
    Mesa mesa;
    int filosofo;

    public Filosofo(String nome, Mesa mesa, int fil) {
        super(nome);
        this.mesa = mesa;
        filosofo = fil;
    }

    public void run() {
        while (true) {
            pensar(100);
            getTalher();
            comer(200);
            retornarTalheres();
        }
    }

    public void pensar(int tempo) {
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println("Filosofo " + this.filosofo + " passou tempo demais pensando");
        }
    }

    public void comer(int tempo) {
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println("Filosofo " + this.filosofo + " passou tempo demais comendo");
        }
    }

    public void retornarTalheres() {
        mesa.retornandoTalheres(filosofo);
    }

    public void getTalher() {
        mesa.getTalheres(filosofo);
    }

    
}

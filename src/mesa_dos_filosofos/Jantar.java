package mesa_dos_filosofos;

public class Jantar {
    public static void main(String[] args) {
        Mesa mesa = new Mesa();
        
        for (int filosofo = 0; filosofo < 5; filosofo++) {
            new Filosofo(filosofo + "° Filosofo_", mesa, filosofo).start();
        }
    }
}
import java.util.Scanner;

public class Principal {
    public Principal() {
        Scanner s = new Scanner(System.in);
        Jogo jogo = new Jogo();
        jogo.iniciar(s);
        
        s.close();
    }
    public static void main(String[] args) throws Exception {
        new Principal();
    }
}
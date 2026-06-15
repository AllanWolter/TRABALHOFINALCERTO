import java.util.Scanner;

public class Principal {
    public Principal() {
        Scanner s = new Scanner(System.in);
        Jogo jogo = new Jogo();
        String continuar = "S";
        while (continuar.equals("S")) {
            jogo.iniciar(s);
            do {
                System.out.print("\nDeseja jogar novamente? (S/N):");
                continuar = s.next().toUpperCase();
            } while (!continuar.equals("N") && !continuar.equals("S"));
        }
        s.close();
    }
    public static void main(String[] args) throws Exception {
        new Principal();
    }
}
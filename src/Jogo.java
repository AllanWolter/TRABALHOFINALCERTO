import java.util.Scanner;

public class Jogo {
    public void iniciar(Scanner s) {
        int opcao = 0, pontos = 0, tentativas = 8, tesourosencontrados = 0, armadilhasencontrados = 0,
                vaziosencontrados = 0, pontuação = 0;
        String tesouros[] = new String[15];
        sortear(tesouros);
        String mapa[] = new String[tesouros.length];
        for (int i = 0; i < mapa.length; i++) {
            mapa[i] = "?";
        }
        do {
            if (tentativas > 0) {
                System.out.print("\n===== ILHA DOS TESOUROS =====\n" +
                        "1 - Mostrar instruções\n" +
                        "2 - Mostrar mapa\n" +
                        "3 - Jogar\n" +
                        "4 - Mostrar status\n" +
                        "5 - Sair\n" +
                        "Escolha uma opção:");
                opcao = s.nextInt();
                System.out.println();

                switch (opcao) {
                    case 1:
                        mostrarinstrucoes();

                        break;

                    case 2:
                        mostrarmapa(mapa);

                        break;

                    case 3:
                        pontos = jogar(tesouros, mapa, s);
                        if (pontos < 0) {
                            armadilhasencontrados++;
                        } else if (pontos == 0) {
                            vaziosencontrados++;
                        } else if (pontos > 0) {
                            tesourosencontrados++;
                        }
                        pontuação += pontos;
                        tentativas--;
                        System.out.println("Jogadas restantes: " + tentativas);
                        break;

                    case 4:
                    case 5:
                        mostrarstatus(tentativas, tesourosencontrados, armadilhasencontrados, vaziosencontrados, pontuação, opcao);

                        break;

                    default:
                        System.out.println("Opção Inválida!");
                        break;
                }
            } else {
                mostrarstatus(tentativas, tesourosencontrados, armadilhasencontrados, vaziosencontrados, pontuação, opcao);
                if (pontuação < 1) {
                    System.out.println("\nVocê perdeu!");
                    break;
                } else {
                    System.out.println("\nVocê Venceu!");
                    break;
                }
            }
        } while (opcao != 5);
    }

    private void mostrarinstrucoes() {
        System.out.println("Como escolher uma posição?\n" +
                "Ao escolher a opção jogar, digite um número de 1 a 15 para tentar encontrar os tesouros escondidos.\n"
                +
                "\nQuais são os tipos de tesouros?\n" +
                "Ouro, Diamante e Rubi.\n" +
                "\nQuais são os tipos de armadilhas?\n" +
                "Buraco, Cobra, Espinhos, além das posições vazias que não contém perda ou ganho de pontos.\n" +
                "\nComo funciona a pontuação?\n");
        System.out.printf(
                "%-15s %-12s %s%n" +
                        "%-15s %-12s %s%n" +
                        "%-15s %-12s %s%n" +
                        "%-15s %-12s %s%n" +
                        "%-15s %-12s %s%n" +
                        "%-15s %-12s %s%n" +
                        "%-15s %-12s %s%n" +
                        "%-15s %-12s %s%n",
                "Item encontrado", "Tipo", "Pontuação",
                "OURO", "Tesouro", "+10 pontos",
                "DIAMANTE", "Tesouro", "+20 pontos",
                "RUBI", "Tesouro", "+15 pontos",
                "BURACO", "Armadilha", "-5 pontos",
                "COBRA", "Armadilha", "-10 pontos",
                "ESPINHOS", "Armadilha", "-7 pontos",
                "VAZIO", "Nenhum", "0 pontos");
    }

    private void mostrarmapa(String mapa[]) {
        for (int i = 0; i < mapa.length; i++) {
            System.out.println("[" + (i + 1) + "] " + mapa[i]);
        }
    }

    private void sortear(String tesouros[]) {
        for (int i = 0; i < tesouros.length; i++) {
            String[] tipos = { "VAZIO", "OURO", "DIAMANTE", "RUBI", "BURACO", "COBRA", "ESPINHOS" };
            int posicao = (int) (Math.random() * 7);
            tesouros[i] = tipos[posicao];
        }
    }

    private int jogar(String tesouros[], String mapa[], Scanner s) {
        int pontos = 0, posicao = 0;
        do {
            System.out.print("Digite uma posição para ser explorada: ");
            posicao = s.nextInt();
            while (posicao > 15 || posicao < 1) {
                System.out.println("Posição inválida! Digite novamente.");
                System.out.print("Digite uma posição para ser explorada: ");
                posicao = s.nextInt();
            }
            posicao--;
            if (mapa[posicao].equals("?")) {
                switch (tesouros[posicao]) {
                    case "VAZIO":
                        pontos = 0;
                        System.out.println("\nVocê encontrou VAZIO! +0 pontos.");
                        break;

                    case "OURO":
                        pontos = 10;
                        System.out.println("\nVocê encontrou OURO! +10 pontos.");
                        break;

                    case "DIAMANTE":
                        pontos = 20;
                        System.out.println("\nVocê encontrou DIAMANTE! +20 pontos.");
                        break;

                    case "RUBI":
                        pontos = 15;
                        System.out.println("\nVocê encontrou RUBI! +15 pontos.");
                        break;

                    case "BURACO":
                        pontos = -5;
                        System.out.println("\nVocê encontrou uma armadilha: BURACO! -5 pontos.");
                        break;

                    case "COBRA":
                        pontos = -10;
                        System.out.println("\nVocê encontrou uma armadilha: COBRA! -10 pontos.");
                        break;

                    case "ESPINHOS":
                        pontos = -7;
                        System.out.println("\nVocê encontrou uma armadilha: ESPINHOS! -7 pontos.");
                        break;

                    default:
                        break;
                }
            } else {
                System.out.println("\nEssa posição já foi explorada! Escolha outra.");
            }
        } while (mapa[posicao].equals("EXPLORADO"));
        mapa[posicao] = "EXPLORADO";
        return pontos;
    }

    private void mostrarstatus(int tentativas, int tesourosencontrados, int armadilhasencontrados,
            int vaziosencontrados, int pontuação, int opcao) {
        if (opcao == 5 || tentativas < 1) {
            System.out.print("\nStatus final:\n");
        } else {
            System.out.print("\nStatus atual:\n");
        }
        System.out.print("Pontuação: " + pontuação + " pontos\n" +
                "Tentativas restantes: " + tentativas + "\n" +
                "Tesouros encontrados: " + tesourosencontrados + "\n" +
                "Armadilhas encontradas: " + armadilhasencontrados + "\n" +
                "Posições vazias exploradas: " + vaziosencontrados + "\n");
    }
}
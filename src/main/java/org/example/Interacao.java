package org.example;
import java.util.ArrayList;
import java.util.Scanner;
public class Interacao {
    private static char player1;
    private static char player2;
    private final Tabuleiro tb = new Tabuleiro();
    public Interacao(){

    }
    //alterar para os dois poderem escolher
    private static char getPlayer1() {
        return player1;
    }

    private static void setPlayer1(char p1) {
        player1 = p1;
    }

    private static char getPlayer2() {
        return player2;
    }

    private static void setPlayer2(char p2) {
        player2 = p2;
    }

    public void setAdversario(char p1){
        if (p1 == 'X'){
            setPlayer1('X');
            setPlayer2('O');
        }
        else{
            setPlayer1('O');
            setPlayer2('X');
        }
    }
    private String leJogadaHumano(){
        Scanner tc = new Scanner(System.in);
        String jogada= tc.next();
        while (!tb.validaJogada(jogada)) {
            System.out.println("Digite a posição que deseja jogar (linha,coluna):");
            jogada = tc.nextLine();
        }
        return jogada;
    }

    private void rodadaPlayer(char player){
        //le a jogada, valida a jogada e adc ela na lista
        System.out.println("Digite a posição que deseja jogar (linha,coluna):");
        String jogada = leJogadaHumano();
        while (!tb.validaJogada(jogada)) {
            System.out.println("Digite a posição que deseja jogar (linha,coluna):");
            jogada = leJogadaHumano();
        }
        tb.jogada(player, jogada);
        Tabuleiro.adicionaJogadas(jogada);
    }
    public void interacao(char advesario){
        // uma jogada inteira com validação em loop
        int turnCont = 0;

        while(!tb.confereJogo()) {
            System.out.println("Rodada do jogador");
            rodadaPlayer(getPlayer1());
            turnCont ++;
            if (isGameOver(tb, turnCont)) break;

            if (advesario == '1') {
                System.out.println("Rodada do adversário");
                rodadaPlayer(getPlayer2());
                turnCont++;
                if (isGameOver(tb, turnCont)) break;
            }
            else{
                rodadaBot(getPlayer2());
                turnCont++;
                if (isGameOver(tb, turnCont)) break;
            }
        }

    }
    private boolean isGameOver(Tabuleiro tb, int turnCont){
        if (tb.confereJogo()){
            tb.imprimeTabuleiro();
            Tabuleiro.retornaJogadas();
            return true;
        }
        if (turnCont == 9){
            tb.imprimeTabuleiro();
            System.out.println("Empate");
            Tabuleiro.retornaJogadas();
            return true;
        }
        tb.imprimeTabuleiro();
        return false;
    }
    private void rodadaBot (char player2){
        System.out.println("Rodada do bot:");
        tb.jogada(player2, randomBot(tb.posicoesLivres()));
    }

    private String randomBot(ArrayList<String> posLivres){
        //posLivres atualizada a cada iteração
        int tam = posLivres.size();
        double randomPos = tam * Math.random();
        return posLivres.get((int)(Math.floor(randomPos)));
    }

}

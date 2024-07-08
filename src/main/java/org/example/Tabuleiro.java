package org.example;

import java.util.*;


public class Tabuleiro {

    private String[][] posicoes = new String[3][3];
    private static ArrayList<String> plays = new ArrayList<>();

    public Tabuleiro(String[][] pos) {
        this.posicoes = pos;
        for (int i =0; i<3; i++){
            for (int j=0; j<3; j++){
                this.posicoes[i][j] = "";
            }
        }
    }
    public Tabuleiro(){}

    public String[][] getPosicoes() {

        return posicoes;
    }

    private void setPosicoes(String pos, char player) {
        int x = Integer.parseInt(""+pos.charAt(0));
        int y = Integer.parseInt(""+pos.charAt(2));
        this.posicoes[x][y] = ""+player;
    }


    public void jogada(char player, String coordenadas){
        String x = ""+ coordenadas.charAt(1);
        String y = ""+ coordenadas.charAt(3);
        String pos = x + "," + y;
        setPosicoes(pos, player);
    }
    public boolean validaJogada(String pos){
        int x = Integer.parseInt(""+pos.charAt(1));
        int y = Integer.parseInt(""+pos.charAt(3));
        //System.out.println("X:" + x + " Y: " + y + " Posição: " + this.posicoes[x][y]);
        return (x <= 3) && (x >= 0) && (y <= 3) && (y >= 0) && (this.posicoes[x][y] == null);
    }
    public static void adicionaJogadas( String jogadas){
        plays.add(jogadas);
    }
    public static void retornaJogadas(){
        System.out.println(plays);
    }
    public void imprimeTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.posicoes[i][j] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(this.posicoes[i][j]);
                }

                if (j < 2) {
                    System.out.print("|");
                }
            }
            if (i < 2) {
                System.out.println("\n-----");
            }
        }
        System.out.println();
    }

    private boolean confereTres(String a, String b, String c){
        return a != null && !a.trim().isEmpty() && Objects.equals(a,b) && Objects.equals(a, c);
    }

    public ArrayList<String> posicoesLivres(){
        ArrayList<String> posLivre = new ArrayList<>();
        for(int i =0; i<3 ; i++){
            for(int j=0; j<3; j++){
                if (posicoes[i][j] == null){
                    posLivre.add("(" + i + "," + j + ")");
                }
            }
        }
        return posLivre;
    }
    public boolean confereJogo(){
        for(int i = 0 ; i < 3 ; i++){
            //confere linhas:
            if (confereTres(this.posicoes[i][0], this.posicoes[i][1], this.posicoes[i][2]))
                    {
                System.out.println("Jogo acabou! Linha " + i + " completa: " +
                        this.posicoes[i][0] + " " + this.posicoes[i][1] + " " + this.posicoes[i][2]);
                System.out.println(this.posicoes[i][0] + " " + this.posicoes[i][1] + " " + this.posicoes[i][2]);
                return true;
            }
            //confere colunas
            if (confereTres(this.posicoes[0][i],(this.posicoes[1][i]), this.posicoes[2][i])) {
                System.out.println("Jogo acabou! 1");
                return true;
            }
        }
        //diagonal principal
        if (confereTres(this.posicoes[0][0], (this.posicoes[1][1]), this.posicoes[2][2]) ){
            System.out.println("Jogo acabou! 2");
            return true;
        }
        //diagonal secundária
        if (confereTres(this.posicoes[0][2], (this.posicoes[1][1]), this.posicoes[2][0]) ){
            System.out.println("Jogo acabou! 3");
            return true;
        }

        return false;
        }
}

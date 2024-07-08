package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner tc = new Scanner(System.in);
        //tela de boas vindas
        System.out.println("""
                ==============================================
                ===   Seja bem-vindo ao Jogo da Velha!!!   ===
                ==============================================""");

        System.out.println("Você deseja jogar contra (1) outro humano ou (outro valor) um bot:");
        String input = tc.next();
        System.out.print("""
                Jogador: Digite o símbolo que deseja utilizar (será considerado apenas
                o primeiro símbolo digitado):
                """);
        String symbol = tc.next();
        Interacao jogo = new Interacao();

        jogo.setAdversario(symbol.charAt(0));
        jogo.interacao(input.charAt(0));
    }
}
package com.mycompany.cuatroenraya;

import java.util.Arrays;
import java.util.Scanner;

public class CuatroEnRaya {

    public static char[][] inicializarTablero() {
        char[][] tablero = new char[7][6];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = '$';
            }
        }
        return tablero;
    }

    public static void mostrarTablero(char[][] tablero) {
        for (char[] c : tablero) {
            System.out.println(Arrays.toString(c));
        }
    }
    
    public static char turnoJugador(char jugadorActual, char [][] tablero){
        for(char [] fila : tablero)
            for(char c : fila)
                if(c == '$')
                    return (jugadorActual == 'X' ? 'O' : 'X');
        return 'E';
    }

    public static int pedirEnteroPositivo(int min, int max, String msg) {
        Scanner sc = new Scanner(System.in);
        int n;
        do {
            try {
                System.out.println(msg);
                n = sc.nextInt();
                if (min <= n && n <= max) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error al introducir datos");
                sc.nextLine();
            }
        } while (true);
        return n;
    }

    public static void realizarJugada(char[][] tablero, int columna, char jugadorActual) {
        boolean filaEnc = false;
        for (int i = tablero.length - 1; i >= 0 && !filaEnc; i--) {
            if (tablero[i][columna] == '$') {
                tablero[i][columna] = jugadorActual;
                System.out.println("Ficha ubicada en [" + i + "][" + columna + "]");
                filaEnc = true;
            }
        }
        if (!filaEnc) {
            System.out.println("La columna " + columna + " está llena. Pierde su turno.");
        }
    }

    public static boolean hayGanador(char[][] tablero, char jugadorActual) {
        //verifico fila
        for (int i = tablero.length - 1; i >= 0; i--) {
            int check = 0;
            for (int j = 0; j < tablero[i].length - 1; j++) {
                if (tablero[i][j] == jugadorActual && tablero[i][j + 1] == jugadorActual) {
                    if (++check == 3) {
                        System.out.println("¡Jugador " + jugadorActual + " ha ganado!");
                        return true;
                    }
                }else{
                    check = 0;
                }
            }
        }
        //verifico columna
        for (int j = 0; j < 6; j++) {
            int check = 0;
            for (int i = tablero.length - 1; i > 0; i--) {
                if (tablero[i][j] == jugadorActual && tablero[i-1][j] == jugadorActual) {
                    if (++check == 3) {
                        System.out.println("¡Jugador " + jugadorActual + " ha ganado!");
                        return true;
                    }
                }else{
                    check = 0;
                }
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        char[][] tablero = inicializarTablero();
        char jugadorActual = 'X';
        // Bucle principal del juego
        mostrarTablero(tablero);
        do {
            System.out.println("Turno del jugador " + jugadorActual);
            int columna = pedirEnteroPositivo(0, 5, "Introduce el número de "
                    + "columna (0-5): ");
            realizarJugada(tablero, columna, jugadorActual);
            mostrarTablero(tablero);
        } while (!hayGanador(tablero, jugadorActual) && 
                (jugadorActual = turnoJugador(jugadorActual, tablero)) !='E');        
    }
}

package com.sebasydidac.Logica;
import java.util.Random;
import org.fusesource.jansi.Ansi;
public class Tablero {

    private final Random rnd = new Random();
    private Barco[] barcos;
    private String[][] tablero;

    public Tablero() {
        tablero = new String[10][10];
        barcos = new Barco[10];
        inicializarTablero();
        inicializarBarcos();
        generarBarcos();
    }
    public Barco getBarco(int i) {
        return barcos[i];
    }

    private void inicializarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = " "; // Espacios vacíos
            }
        }
    }
    //NUEVO PRUEBAS SEBAS
//    private void inicializarBarcos() {
//        int[] tipos = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
//        for (int i = 0; i < barcos.length; i++) {
//            String[] posiciones = new String[tipos[i]]; // Crear un arreglo de posiciones según el tamaño
//            barcos[i] = new Barco(posiciones, tipos[i]);  // Aquí se invoca el constructor correcto
//        }
//    }


    //ANTERIOR SEBAS
    private void inicializarBarcos() {
        int[] tipos = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        for (int i = 0; i < barcos.length; i++) {
            String[] posiciones = new String[tipos[i]]; // Crear un arreglo de posiciones según el tamaño
            barcos[i] = new Barco(posiciones, tipos[i]);  // Usa el constructor con parámetros
        }
    }


    private boolean colocarBarco(int x, int y, int tamano, boolean horizontal) {
        if (horizontal) {
            if (y + tamano > tablero.length) {
                return false; // Fuera de límites
            }
            for (int i = 0; i < tamano; i++) {
                if (!tablero[x][y + i].equals(" ")) {
                    return false; // Espacio ocupado
                }
            }
            for (int i = 0; i < tamano; i++) {
                tablero[x][y + i] = "B" + tamano; // Marca como ocupado e incluye el tamaño
            }
        } else {
            if (x + tamano > tablero.length) {
                return false; // Fuera de límites
            }
            for (int i = 0; i < tamano; i++) {
                if (!tablero[x + i][y].equals(" ")) {
                    return false; // Espacio ocupado
                }
            }
            for (int i = 0; i < tamano; i++) {
                tablero[x + i][y] = "B" + tamano; // Marca como ocupado e incluye el tamaño
            }
        }
        return true; // Retorno exitoso cuando el barco se coloca correctamente
    }

    //PRUEBAS NUEVO SEBAS

//    private void generarBarcos() {
//        for (int i = 0; i < barcos.length; i++) {
//            int tamano = barcos[i].getTipo();
//            boolean colocado = false;
//            while (!colocado) {
//                int x = generarNum();
//                int y = generarNum();
//                boolean horizontal = rnd.nextBoolean();
//                colocado = colocarBarco(x, y, tamano, horizontal);
//                if (colocado) {
//                    String[] posiciones = new String[tamano];
//                    for (int j = 0; j < tamano; j++) {
//                        posiciones[j] = horizontal ? (x + ";" + (y + j)) : ((x + j) + ";" + y);
//                    }
//                    barcos[i].setPosiciones(posiciones); // Asigna las posiciones correctamente
//                }
//            }
//        }
//    }


    private void generarBarcos() {
        for (int i = 0; i < barcos.length; i++) {
            int tamano = barcos[i].getTipo();
            boolean colocado = false;
            while (!colocado) {
                int x = generarNum();
                int y = generarNum();
                boolean horizontal = rnd.nextBoolean();
                colocado = colocarBarco(x, y, tamano, horizontal);
                if (colocado) {
                    String[] posiciones = new String[tamano];
                    for (int j = 0; j < tamano; j++) {
                        posiciones[j] = horizontal ? (x + ";" + (y + j)) : ((x + j) + ";" + y);
                    }
                    barcos[i].setPosiciones(posiciones); // Asignar las posiciones correctamente
                }
            }
        }
    }



    //ANTERIOR SEBAS
//    private void generarBarcos() {
//        for (int i = 0; i < barcos.length; i++) {
//            int tamano = barcos[i].getTipo();
//            boolean colocado = false;
//            while (!colocado) {
//                int x = generarNum();
//                int y = generarNum();
//                boolean horizontal = rnd.nextBoolean();
//                colocado = colocarBarco(x, y, tamano, horizontal);
//                if (colocado) {
//                    String[] posiciones = new String[tamano];
//                    for (int j = 0; j < tamano; j++) {
//                        posiciones[j] = horizontal ? (x + ";" + (y + j)) : ((x + j) + ";" + y);
//                    }
//                    barcos[i].setPosiciones(posiciones);
//                }
//            }
//        }
//    }

    //SEBAS ANTERIOR DE TODO CAMBIO
public void imprimirTablero() {
    // Encabezado de la tabla
    System.out.print("\t");
    for (int j = 0; j < tablero[0].length; j++) {
        System.out.print((j + 1) + "\t");
    }
    System.out.println();

    // Cuerpo de la tabla
    for (int i = 0; i < tablero.length; i++) {
        System.out.print((char) ('A' + i) + "\t"); // Letras de la fila
        for (int j = 0; j < tablero[i].length; j++) {
            String cell = tablero[i][j];
            if (cell.startsWith("B")) {
                // Representar barco por tamaño y orientación
                char representation = representarBarco(cell);
                System.out.print(Ansi.ansi().fg(Ansi.Color.GREEN).a(representation).reset() + "\t");
            } else {
                // Espacios vacíos representados como sombreado (char) 177
                System.out.print(Ansi.ansi().fg(Ansi.Color.BLUE).a((char) 177).reset() + "\t");
            }
        }
        System.out.println();
    }
}

    //DESPUES PRUEBAS
//    public void imprimirTablero() {
//        // Encabezado de la tabla
//        System.out.print("\t");
//        for (int j = 0; j < tablero[0].length; j++) {
//            System.out.print((j + 1) + "\t");
//        }
//        System.out.println();
//
//        // Cuerpo de la tabla
//        for (int i = 0; i < tablero.length; i++) {
//            System.out.print((char) ('A' + i) + "\t"); // Letras de la fila
//            for (int j = 0; j < tablero[i].length; j++) {
//                String cell = tablero[i][j];
//                if (cell.startsWith("B")) {
//                    // Buscar el barco correspondiente
//                    int tamano = Integer.parseInt(cell.substring(1)); // Obtener el tamaño del barco
//                    Barco barco = buscarBarcoPorTamaño(tamano);
//
//                    if (barco != null) {
//                        // Asegúrate de que el barco tiene el array tocado inicializado
//                        int indice = obtenerIndicePosicionBarco(barco, i, j); // Obtener el índice en el array de posiciones del barco
//
//                        // Verificar si la celda ha sido tocada --> barco.getTocado()[indice]
//                        if (indice != -1 && barco.getTocado()[indice]) {
//                            // Si está tocado, representarlo en rojo
//                            System.out.print(Ansi.ansi().fg(Ansi.Color.RED).a(representarBarco(cell)).reset() + "\t");
//                        } else {
//                            // Si no está tocado, representarlo en verde
//                            System.out.print(Ansi.ansi().fg(Ansi.Color.GREEN).a(representarBarco(cell)).reset() + "\t");
//                        }
//                    }
//                } else {
//                    // Si la celda está vacía o no tiene barco
//                    System.out.print(Ansi.ansi().fg(Ansi.Color.BLUE).a((char) 177).reset() + "\t");
//                }
//            }
//            System.out.println();
//        }
//    }



    //ANTES PRUEBA
//public void imprimirTablero() {
//    // Encabezado de la tabla
//    System.out.print("\t");
//    for (int j = 0; j < tablero[0].length; j++) {
//        System.out.print((j + 1) + "\t");
//    }
//    System.out.println();
//
//    // Cuerpo de la tabla
//    for (int i = 0; i < tablero.length; i++) {
//        System.out.print((char) ('A' + i) + "\t"); // Letras de la fila
//        for (int j = 0; j < tablero[i].length; j++) {
//            String cell = tablero[i][j];
//            if (cell.startsWith("B")) {
//                // Buscar el barco correspondiente
//                int tamano = Integer.parseInt(cell.substring(1)); // Obtener el tamaño del barco
//                Barco barco = buscarBarcoPorTamaño(tamano);
//
//                // Encontrar la posición del barco
//                int indice = obtenerIndicePosicionBarco(barco, i, j);
//
//                // Verificar si la celda ha sido tocada -->barco.getTocado()[indice]
//                if (indice != -1 && barco.getTocado()[indice]) {
//                    // Representar barco tocado en rojo
//                    System.out.print(Ansi.ansi().fg(Ansi.Color.RED).a(representarBarco(cell)).reset() + "\t");
//                } else {
//                    // Representar barco intacto en verde
//                    System.out.print(Ansi.ansi().fg(Ansi.Color.GREEN).a(representarBarco(cell)).reset() + "\t");
//                }
//            } else {
//                // Espacios vacíos representados como sombreado (char) 177
//                System.out.print(Ansi.ansi().fg(Ansi.Color.BLUE).a((char) 177).reset() + "\t");
//            }
//        }
//        System.out.println();
//    }
//}

    private Barco buscarBarcoPorTamaño(int tamano) {
        for (Barco barco : barcos) {
            if (barco.getTipo() == tamano) {
                return barco;
            }
        }
        return null;
    }

    private int obtenerIndicePosicionBarco(Barco barco, int x, int y) {
        for (int i = 0; i < barco.getPosiciones().length; i++) {
            String[] pos = barco.getPosiciones()[i].split(";");
            int barcoX = Integer.parseInt(pos[0]);
            int barcoY = Integer.parseInt(pos[1]);

            // Comparar las posiciones
            if (barcoX == x && barcoY == y) {
                return i;
            }
        }
        return -1; // Si no se encuentra la posición
    }

    private char representarBarco(String cell) {
        // Validar si la celda contiene un barco
        if (cell.startsWith("B")) {
            try {
                // Extrae el tamaño del barco después de "B"
                int tamano = Integer.parseInt(cell.substring(1));
                switch (tamano) {
                    case 1:
                        return '1'; // Barco de tamaño 1
                    case 2:
                        return '2'; // Barco de tamaño 2
                    case 3:
                        return '3'; // Barco de tamaño 3
                    case 4:
                        return '4'; // Barco de tamaño 4
                    default:
                        return '?'; // En caso de error
                }
            } catch (NumberFormatException e) {
                // Manejo de errores si no se puede convertir
                System.err.println("Error al interpretar el tamaño del barco: " + cell);
                return '?';
            }
        }
        // Si la celda no contiene un barco, devolver un espacio vacío
        return (char) 177; // Representación de sombreado
    }

    private int generarNum() {
        return rnd.nextInt(10);
    }
}

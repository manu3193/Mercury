package test;

/**
 * Created by manzumbado on 22/03/16.
 */

import mercury.ErrorList;
import mercury.LexicalAnalysis;
import mercury.arm.assembler.TokenList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public final static int GENERAR = 1;
    public final static int EJECUTAR = 2;
    public final static int SALIR = 3;

    /**
     * Es un menu para elegir entre generar el analizador lexico y sintactico, o
     * ejecutarlos sobre un archivo de pruebas.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.util.Scanner in = new Scanner(System.in);
        int valor = 0;
        do {
            System.out.println("Elija una opcion: ");
            System.out.println("1) Generar");
            System.out.println("2) Ejecutar");
            System.out.println("3) Salir");
            System.out.print("Opcion: ");
            valor = in.nextInt();
            switch (valor) {
                /*  Generamos el analizador lexico y sintactico.
                 lcalc.flex contiene la definicion del analizador lexico
                 ycalc.cup contiene la definicion del analizador sintactico
                 */
                case GENERAR: {
                    System.out.println("\n*** Generando ***\n");
                    String archLexico = "";
                    String archSintactico = "";
                    if (args.length > 0) {
                        System.out.println("\n*** Procesando archivos custom ***\n");
                        archLexico = args[0];
                        archSintactico = args[1];
                    } else {
                        System.out.println("\n*** Procesando archivo default ***\n");
                        archLexico = "LexicalDefinition.flex";
                       // archSintactico = "asintactico.cup";
                    }
                    String[] alexico = {archLexico};
                    jflex.Main.main(alexico);
                    try {
                       // java_cup.Main.main(asintactico);
                    } catch (Exception ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //movemos los archivos generados
                    boolean mvAL = moverArch("LexicalAnalysis.java");
                    boolean mvAS = moverArch("AnalizadorSintactico.java");
                    boolean mvSym= moverArch("sym.java");
                    if(mvAL && mvAS && mvSym){
                        System.exit(0);
                    }
                    System.out.println("Generado!");
                    break;
                }
                case EJECUTAR: {
                    //  Ejecutamos el analizador lexico y sintactico
                    // sobre un archivo de pruebas.

                    String entrada = "program.arm4";
                    // AnalizadorSintactico.main(archivoPrueba);
                  try {
                        BufferedReader bf = null;
                        bf = new BufferedReader(new FileReader(entrada));
                        LexicalAnalysis a = new LexicalAnalysis(bf);
                        a.yylex();

                        TokenList tokens = a.getTokens();
                        ErrorList errores = a.getErrors();
                        System.out.println(tokens.toString());
                        System.out.println(errores.generateReport());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Ejecutado!");
                    break;
                }
                case SALIR: {
                    System.out.println("Adios!");
                    break;
                }
                default: {
                    System.out.println("Opcion no valida!");
                    break;
                }
            }
        } while (valor != 3);

    }

    public static boolean moverArch(String archNombre) {
        boolean efectuado = false;
        File arch = new File(archNombre);
        if (arch.exists()) {
            System.out.println("\n*** Moviendo " + arch + " \n***");
            Path currentRelativePath = Paths.get("");
            String nuevoDir = currentRelativePath.toAbsolutePath().toString()
                    + File.separator + "mercury" + File.separator+ arch.getName();
            File archViejo = new File(nuevoDir);
            archViejo.delete();
            if (arch.renameTo(new File(nuevoDir))) {
                System.out.println("\n*** Generado " + archNombre + "***\n");
                efectuado = true;
            } else {
                System.out.println("\n*** No movido " + archNombre + " ***\n");
            }

        } else {
            System.out.println("\n*** Codigo no existente ***\n");
        }
        return efectuado;
    }
}
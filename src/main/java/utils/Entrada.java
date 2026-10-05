package utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/** Ajuda a ler dados do teclado sem repetir Scanner em todo lugar. */
public class Entrada {
    private static final Scanner sc = new Scanner(System.in);

    public static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine().trim();
    }

    public static int lerInt(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(lerTexto(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    public static double lerDouble(String mensagem) {
        while (true) {
            try {
                return Double.parseDouble(lerTexto(mensagem).replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número (ex.: 150.50).");
            }
        }
    }

    public static LocalDate lerData(String mensagem) {
        while (true) {
            try {
                String data = lerTexto(mensagem + " (DD/MM/AAAA): ");

                String[] partes = data.split("/");

                if (partes.length != 3) {
                    throw new DateTimeParseException("Formato inválido", data, 0);
                }

                int dia = Integer.parseInt(partes[0]);
                int mes = Integer.parseInt(partes[1]);
                int ano = Integer.parseInt(partes[2]);

                return LocalDate.of(ano, mes, dia);

            } catch (DateTimeParseException | NumberFormatException e) {
                System.out.println("Data inválida. Use o formato DD/MM/AAAA.");
            }
        }
    }
    public static boolean lerSimNao(String mensagem) {
        while (true) {
            String r = lerTexto(mensagem + " (S/N): ").toUpperCase();
            if (r.equals("S")) return true;
            if (r.equals("N")) return false;
            System.out.println("Responda S ou N.");
        }
    }
}

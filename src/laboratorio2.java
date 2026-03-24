import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class PabellonCirugia {
    private int numero;
    private String especialidad;
    private boolean disponible;

    public PabellonCirugia(int numero, String especialidad) {
        this.numero = numero;
        this.especialidad = especialidad;
        this.disponible = true;
    }

    public int getNumero() { return numero; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return numero + "," + especialidad + "," + (disponible ? "disponible" : "ocupado");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return this.numero == ((PabellonCirugia) obj).numero;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numero);
    }
}

public class DemoPabellonCirugia {

    private static final int CANTIDAD_PABELLONES = 6;
    private ArrayList<PabellonCirugia> lista = new ArrayList<>();

    public static void main(String[] args) {
        new DemoPabellonCirugia().procesa();
    }

    public void procesa() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < CANTIDAD_PABELLONES; i++) {
            System.out.println("\nPabellón " + (i + 1));
            lista.add(ingresarPabellon(sc));
        }

        marcarOcupados();
        mostrarPabellones();
        buscarDuplicados();

        sc.close();
    }

    private PabellonCirugia ingresarPabellon(Scanner sc) {
        int numero = leerNumeroValido(sc);
        String especialidad = leerEspecialidadValida(sc);
        return new PabellonCirugia(numero, especialidad);
    }

    private int leerNumeroValido(Scanner sc) {
        while (true) {
            System.out.print("Ingrese número: ");
            try {
                int numero = sc.nextInt();
                sc.nextLine();
                if (numero <= 0) {
                    System.out.println("Error: el número debe ser positivo.");
                    continue;
                }
                if (numeroDuplicado(numero)) {
                    System.out.println("Error: ya existe un pabellón con ese número.");
                    continue;
                }
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("Error: ingrese un número entero válido.");
                sc.nextLine();
            }
        }
    }

    private String leerEspecialidadValida(Scanner sc) {
        while (true) {
            System.out.print("Ingrese especialidad: ");
            String especialidad = sc.nextLine().trim();
            if (!especialidad.isEmpty()) return especialidad;
            System.out.println("Error: la especialidad no puede estar vacía.");
        }
    }

    private boolean numeroDuplicado(int numero) {
        for (PabellonCirugia p : lista) {
            if (p.getNumero() == numero) return true;
        }
        return false;
    }

    private void marcarOcupados() {
        for (int i = 0; i < lista.size(); i += 2) {
            lista.get(i).setDisponible(false);
        }
    }

    private void mostrarPabellones() {
        System.out.println("\n--- Listado de pabellones ---");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Posición " + i + ": " + lista.get(i));
        }
    }

    private void buscarDuplicados() {
        System.out.println("\n--- Pabellones iguales ---");
        boolean hayIguales = false;

        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).equals(lista.get(j))) {
                    System.out.println("Iguales en posiciones: " + i + " y " + j);
                    hayIguales = true;
                }
            }
        }

        if (!hayIguales) System.out.println("No hay pabellones iguales.");
    }
}

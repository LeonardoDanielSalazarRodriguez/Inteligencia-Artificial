// Leonardo Daniel Salazar Rodriguez
import java.util.*;


public class eightPuzzle
{

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        String objetivo = "12345678*";
        Nodo raiz = new Nodo("123*85647");
        ArbolBusqueda arbolBusqueda = new ArbolBusqueda(raiz, objetivo);
     
        int opcion = 0;

            System.out.println("Elige un modo de búsqueda: ");
            System.out.println("1. Búsqueda primero en anchura");
            System.out.println("2. Búsqueda primero en profundidad");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    arbolBusqueda.busquedaAnchura();
                    break;
                case 2:            
                    arbolBusqueda.busquedaProfundidad();
                    break;
                default:
                    System.out.println("Es 1 o 2 nada más, vuelve a ejecutar el código");
                    break;
            }
    }
}
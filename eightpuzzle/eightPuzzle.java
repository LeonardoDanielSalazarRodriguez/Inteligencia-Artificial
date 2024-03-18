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
        
            System.out.println("Escoja una opción:");
            System.out.println("1. Búsqueda primero en anchura");
            System.out.println("2. Búsqueda primero en profundidad");
            System.out.println("3. Búsqueda heuristica 1");
            System.out.println("4. Búsqueda heuristica 2");
            System.out.println("5. Búsqueda heuristica 3");
            opcion = scanner.nextInt();

            switch (opcion) 
            {
                case 1:
                    arbolBusqueda.busquedaPorAnchura();
                    break;
                case 2:
                    arbolBusqueda.busquedaPorProfundidad();
                    break;
                case 3:
                	arbolBusqueda.busquedaHeuristica(arbolBusqueda.new Heuristica1());
                    break;
                case 4:
                	arbolBusqueda.busquedaHeuristica(arbolBusqueda.new Heuristica2());
                    break;
                case 5:            
                    arbolBusqueda.busquedaHeuristica(arbolBusqueda.new Heuristica3());
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }

         
    }
}
    
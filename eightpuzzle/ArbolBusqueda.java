import java.util.*;

public class ArbolBusqueda 
{
    private Nodo raiz;
    private static String objetivo;
    private Set<String> visitados;

    public ArbolBusqueda(Nodo raiz, String objetivo) 
    {
        this.raiz = raiz;
        this.objetivo = objetivo;
        this.visitados = new HashSet<>();
    }

    // Método para la búsqueda primero en anchura
    public void busquedaPorAnchura() 
    {
        System.out.println("Búsqueda primero en Anchura:");
        Queue<Nodo> cola = new LinkedList<>();
        cola.offer(raiz);
        visitados.add(raiz.getEstado());
        while (!cola.isEmpty()) 
        {
            Nodo actual = cola.poll();
            if (actual.getEstado().equals(objetivo)) 
            {
                imprimirSolucion(actual, new String());
                return;
            }
            for (String sucesor : actual.generaHijos()) 
            {
                Nodo nodoSucesor = new Nodo(sucesor);
                if (!visitados.contains(sucesor)) 
                {
                    nodoSucesor.setPadre(actual);
                    cola.offer(nodoSucesor);
                    visitados.add(sucesor);
                }
            }
        }
        System.out.println("No se encontró solución.");
    }

    // Metodo para la busqueda primero en profundidad
    public void busquedaPorProfundidad() 
    {
        System.out.println("\nBúsqueda primero en Profundidad:");
        Stack<Nodo> pila = new Stack<>();
        pila.push(raiz);
        visitados.add(raiz.getEstado());
        while (!pila.isEmpty()) 
        {
            Nodo actual = pila.pop();
            if (actual.getEstado().equals(objetivo)) 
            {
                Solucion(actual);
                return;
            }
            for (String sucesor : actual.generaHijos()) 
            {
                Nodo nodoSucesor = new Nodo(sucesor);
                if (!visitados.contains(sucesor)) {
                    nodoSucesor.setPadre(actual);
                    pila.push(nodoSucesor);
                    visitados.add(sucesor);
                }
            }
        }
        System.out.println("No se encontró solución.");
    }

    public abstract class Heuristica 
    {
        public abstract int evaluar(String estado);
    }

    public class Heuristica1 extends Heuristica 
    {
        @Override
        public int evaluar(String estado) 
        {
            int distancia = 0;
            for (int i = 0; i < estado.length(); i++) 
            {
                if (estado.charAt(i) != '*' && estado.charAt(i) != objetivo.charAt(i)) 
                {
                    distancia++;
                }
            }
            return distancia;
        }
    }

    public class Heuristica2 extends Heuristica 
    {
        @Override
        public int evaluar(String estado) 
        {
            int distancia = 0;
            for (int i = 0; i < estado.length(); i++) 
            {
                if (estado.charAt(i) != '*') 
                {
                    int x1 = i % 3;
                    int y1 = i / 3;
                    int x2 = objetivo.indexOf(estado.charAt(i)) % 3;
                    int y2 = objetivo.indexOf(estado.charAt(i)) / 3;
                    distancia += Math.abs(x1 - x2) + Math.abs(y1 - y2);
                }
            }
            return distancia;
        }
    }

    public class Heuristica3 extends Heuristica 
    {
        @Override
        public int evaluar(String estado) {
            int distancia = 0;
            for (int i = 0; i < estado.length(); i++) 
            {
                if (estado.charAt(i) != '*') 
                {
                    int x1 = i % 3;
                    int y1 = i / 3;
                    int x2 = objetivo.indexOf(estado.charAt(i)) % 3;
                    int y2 = objetivo.indexOf(estado.charAt(i)) / 3;
                    distancia += 
                    (Math.abs(x1 - x2) + Math.abs(y1 - y2)) * (new Nodo(estado).getProfundidad() + 1);
                }
            }
            return distancia;
        }
    }

    public void busquedaHeuristica(Heuristica heuristica) 
    {
        System.out.println("\nBúsqueda con " + heuristica.getClass().getSimpleName() + ":");
        PriorityQueue<Nodo> colaPrioridad = 
        new PriorityQueue<>(Comparator.comparingInt(o -> o.getProfundidad() + heuristica.evaluar(o.getEstado())));
        colaPrioridad.offer(raiz);
        visitados.add(raiz.getEstado());
        while (!colaPrioridad.isEmpty()) {
            Nodo actual = colaPrioridad.poll();
            if (actual.getEstado().equals(objetivo)) 
            {
                imprimirSolucion(actual, new String());
                return;
            }
            for (String sucesor : actual.generaHijos()) 
            {
                Nodo nodoSucesor = new Nodo(sucesor);
                if (!visitados.contains(nodoSucesor.getEstado())) 
                {
                    visitados.add(nodoSucesor.getEstado());
                    nodoSucesor.setPadre(actual);
                    colaPrioridad.offer(nodoSucesor);
                }
            }
        }
        System.out.println("No se encontró solución.");
    }

    public void imprimirSolucion(Nodo nodo, String aux ) 
    {
    	   if (nodo.getPadre() != null) 
           {
    		   imprimirSolucion(nodo.getPadre(), aux);
           }
           aux = nodo.getEstado();
           System.out.println(aux.charAt(0) +
            " | " + 
            aux.charAt(1) + 
            " | " + 
            aux.charAt(2));
           System.out.println(aux.charAt(3) + 
           " | " + 
           aux.charAt(4) + 
           " | " + 
           aux.charAt(5));
           System.out.println(aux.charAt(6) + 
           " | " + 
           aux.charAt(7) + 
           " | " + 
           aux.charAt(8));
           System.out.println();
    }
    public void Solucion(Nodo nodo) 
    {
        System.out.println("Solución encontrada:");
        Stack<Nodo> pila = new Stack<>();
        while (nodo != null) 
        {
            pila.push(nodo);
            nodo = nodo.getPadre();
        }
        int profundidad = 0;
        while (!pila.isEmpty()) 
        {
            Nodo actual = pila.pop();
            System.out.println("Profundidad " + profundidad + ":");
            System.out.println(actual);
            profundidad++;
        }
    }


    }
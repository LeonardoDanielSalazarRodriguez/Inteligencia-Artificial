import java.util.ArrayList;

public class Nodo 
{
	
    private String estado;
    private Nodo padre;
    private ArrayList<Nodo> hijos;
    private int nivel;

    public Nodo(String estado) 
    {
        this.estado = estado;
        this.padre = null;
        this.hijos = new ArrayList<>();
        this.nivel = 0;
    }

    public String getEstado() 
    {
        return estado;
    }

    public Nodo getPadre() 
    {
        return padre;
    }

    public void setPadre(Nodo padre) 
    {
        this.padre = padre;
        this.nivel = padre.getNivel() + 1;
    }

    public ArrayList<Nodo> getHijos() 
    {
        return hijos;
    }

    public int getNivel() 
    {
        return nivel;
    }

    public ArrayList<String> generaHijos() 
    {
        int index = estado.indexOf('*');
        int x = index % 3;
        int y = index / 3;
        ArrayList<String> sucesores = new ArrayList<>();
        if (x > 0)
            sucesores.add(interchange(index, index - 1));
        if (x < 2) 
            sucesores.add(interchange(index, index + 1));
        if (y > 0)
            sucesores.add(interchange(index, index - 3));
        if (y < 2) 
            sucesores.add(interchange(index, index + 3));
        return sucesores;
    }

    private String interchange(int i, int j) 
    {
        StringBuilder sb = new StringBuilder(estado);
        char temp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, temp);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (obj == this) 
            return true;
        if (!(obj instanceof Nodo)) 
            return false;
        Nodo other = (Nodo) obj;
        return this.estado.equals(other.estado);
    }

    public String toString() 
    {
        return estado;
    }
    public int hashCode() 
    {
        return estado.hashCode();
    }

}

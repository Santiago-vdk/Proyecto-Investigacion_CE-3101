package kingofthehill.logica;

/**
 *
 * @author RafaelAngel
 */
public class Zona {
    private Zona _next = null;
    private Zona _prev = null;
    private String _color = "";
    private String _lat1 ="";
    private String _long1 = "";
    private String _lat2 ="";
    private String _long2 = "";
    private String _nombre ="";
    
    /**
     *
     * @param pNombre
     * @param pLat1
     * @param pLong1
     * @param pLat2
     * @param pLong2
     * @param pColor
     */
    public Zona(String pNombre,String pLat1,String pLong1,String pLat2,String pLong2,String pColor){
        _color = pColor;
        _lat1 = pLat1;
        _long1 = pLong1;
        _lat2 = pLat2;
        _long2 = pLong2;
        _nombre = pNombre;
    }

    /**
     * @return the _next
     */
    public Zona getNext() {
        return _next;
    }

    /**
     * @param pNext
     */
    public void setNext(Zona pNext) {
        _next = pNext;
    }

    /**
     * @return the _prev
     */
    public Zona getPrev() {
        return _prev;
    }

    /**
     * @param pPrev
     */
    public void setPrev(Zona pPrev) {
        _prev = pPrev;
    }

    /**
     * @return the _color
     */
    public String getColor() {
        return _color;
    }

    /**
     * @param pEscuela
     */
    public void setColor(String pEscuela) {
        _color = pEscuela;
    }

    /**
     * @return the _lat1
     */
    public String getLat1() {
        return _lat1;
    }

    /**
     * @param pLat1
     */
    public void setLat1(String pLat1) {
        _lat1 = pLat1;
    }

    /**
     * @return the _long1
     */
    public String getLong1() {
        return _long1;
    }

    /**
     * @param pLong1
     */
    public void setLong1(String pLong1) {
        _long1 = pLong1;
    }

    /**
     * @return the _lat2
     */
    public String getLat2() {
        return _lat2;
    }

    /**
     * @param pLat2
     */
    public void setLat2(String pLat2) {
        _lat2 = pLat2;
    }

    /**
     * @return the _long2
     */
    public String getLong2() {
        return _long2;
    }

    /**
     * @param pLong2
     */
    public void setLong2(String pLong2) {
        _long2 = pLong2;
    }

    /**
     * @return the _nombre
     */
    public String getNombre() {
        return _nombre;
    }

    /**
     * @param pNombre
     */
    public void setNombre(String pNombre) {
        _nombre = pNombre;
    }
    
    
}

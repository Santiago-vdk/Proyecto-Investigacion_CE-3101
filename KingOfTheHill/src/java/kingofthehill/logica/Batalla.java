package kingofthehill.logica;

/**
 *
// * @author RafaelAngel
 */
public class Batalla extends Thread{
    
    private Batalla _next;
    private Batalla _prev;
    private float _scoreJugador1 = -1;
    private float _scoreJugador2 = -1;
    private User _jugador1;
    private User _jugador2;
    private boolean _necesario =true;
   
    /**
     *
     */
    public Batalla(User pJugador1,User pJugador2){
        _jugador1 = pJugador1;
        _jugador2 = pJugador2;
        start(); //Volatil
    }
    
    /**
     *
     * @param user
     * @param LatJugador
     * @param LongJugador
     * @return 
     */
    
    
//    public boolean UserMoved(User user,Double LatJugador,Double LongJugador) {
//        
//        for(int i=0;i<Regiones.getInstance().getZonasList().getTam();i++){
//                String nombre = Regiones.getInstance().getZonasList().buscar(i).getNombre();
//                String color = Regiones.getInstance().getZonasList().buscar(i).getColor();
//                
//                Double lat1 = Double.parseDouble(Regiones.getInstance().getZonasList().buscar(i).getLat1());
//                Double long1 = Double.parseDouble(Regiones.getInstance().getZonasList().buscar(i).getLong1());
//                
//                Double lat2 = Double.parseDouble(Regiones.getInstance().getZonasList().buscar(i).getLat2());
//                Double long2 = Double.parseDouble(Regiones.getInstance().getZonasList().buscar(i).getLong2());
//                
//                if(LatJugador>lat1 && LatJugador<lat2 
//                        && LongJugador>long1 && LongJugador<long2){//jugador dentro de la zona
//                    
//                    if(user.CambioZona(nombre)){//entro a una zona nueva
//                        User defensor = Jugadores.getInstance().defensor(nombre,color);
//                        if(defensor!= null){
//                            //pelea entre user y defensor
//                            user.setEnPelea(true);
//                            defensor.setEnPelea(true);
//                            _jugador1 = user;
//                            _jugador2 = defensor;
//                            return true;
//                            
//                        } else {
//                            //conquisto zona
//                            String escuela = user.getEscuela();
//                            Regiones.getInstance().getZonasList().buscar(i).setColor(escuela);
//                            
//                        }
//                    }
//                }
//        }
//        return false;
//    }
    
    /**
     *
     * @param pToken
     * @return
     */
    public boolean buscarUser(String pToken){
        if(_jugador1.getToken().compareTo(pToken)==0){
            return true;
        }
        else if(_jugador2.getToken().compareTo(pToken)==0){
            return true;
        }
        return false;
    }
    
    /**
     *
     * @param pToken
     * @param pScore
     * @return
     */
    public boolean ScoreBatalla(String pToken,float pScore){
        if(_jugador1.getToken().compareTo(pToken)==0){
            _scoreJugador1 = pScore;
            if(_scoreJugador1 != -1 || _scoreJugador2 != -1){
                return true;
            }
        }
        else if(_jugador2.getToken().compareTo(pToken)==0){
            _scoreJugador2 = pScore;
            if(_scoreJugador1 != -1 || _scoreJugador2 != -1){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void run(){
        while(_necesario || _scoreJugador1 == -1 || _scoreJugador2 == -1){
        }
        this.stop();
        //Definir ganador
    }

    /**
     * @return the _next
     */
    public Batalla getNext() {
        return _next;
    }

    /**
     * @param pNext
     */
    public void setNext(Batalla pNext) {
        _next = pNext;
    }

    /**
     * @return the _prev
     */
    public Batalla getPrev() {
        return _prev;
    }

    /**
     * @param pPrev
     */
    public void setPrev(Batalla pPrev) {
        _prev = pPrev;
    }

    /**
     * @return the _necesario
     */
    public boolean isNecesario() {
        return _necesario;
    }

    /**
     * @param pNecesario
     */
    public void setNecesario(boolean pNecesario) {
        _necesario = pNecesario;
    }
    
    
}

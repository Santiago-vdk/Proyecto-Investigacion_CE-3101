/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingofthehill.logica;

/**
 *
 * @author RafaelAngel
 */
public class BattleManager {
    private static BattleManager _singleton = new BattleManager();
    private listaBatallas _batallas = new listaBatallas(); 
    
    private BattleManager(){
    }
    
    /**
     *
     * @return
     */
    public static BattleManager getInstance(){
        return _singleton;
    }
    
    /**
     *
     * @param pToken
     * @param pScore
     */
    public void scoreBatalla(String pToken,float pScore){//metodo llamado por el post de batalla result
        Batalla batalla = _batallas.buscarConToken(pToken);
            if(batalla.buscarUser(pToken)){
                boolean flag = batalla.ScoreBatalla(pToken, pScore);
                if(flag){
                    _batallas.borrarConToken(pToken);
                }
            
        }
        
    }
    
    /**
     *
     * @param pUser
     * @param pLat
     * @param pLong
     */
    public void nuevaBatalla(User pUser, double pLat, double pLong){
        Batalla battle = new Batalla();
        boolean flag = battle.UserMoved(pUser, pLat, pLong);
        if(flag){
            _batallas.insertar(battle);
        }
        battle.setNecesario(false);
        
        //Meter en lista de batallas
        
        
    }
}

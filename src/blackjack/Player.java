/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author gubotdev
 */
public class Player {
    private Hand myHand = new Hand();
    private String name = null;
    
    public Player(String username){
        name = username;
    }
    
    public String getName(){
        return name; //CHANGE!!!!!
    }
    
    public Hand getHand(){
        return myHand; 
    }
    
    
}

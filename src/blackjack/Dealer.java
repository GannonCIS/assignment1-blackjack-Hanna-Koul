/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

/**
 *
 * @author gubotdev
 */
public class Dealer {
    
    private Hand dealerHand = new Hand();
    private Player[] myPlayers = new Player[0];
    private Deck myDeck = new Deck();
    private Scanner scan = new Scanner(System.in);
    
    public Dealer(){
        System.out.println("How many players?");
        int num = scan.nextInt();
        initPlayers(num);
    }
    
    Dealer(int numOfPlayers){
        initPlayers(numOfPlayers);
    }
    
    public void dealOpeningHand(){
        for (int i = 0; i < 2; i++){
            for (Player currPlayer : myPlayers){
                currPlayer.getHand().addCard(myDeck.dealCard());
            }
            dealerHand.addCard(myDeck.dealCard());
        }
    }
    
    public void playOutPlayerHands(){
        for (Player currPlayer : myPlayers){
            System.out.println("\n" + currPlayer.getName() + "'s Hand");
            currPlayer.getHand().printHand();
            while (currPlayer.getHand().getNumOfCards() < 5 && 
                    currPlayer.getHand().getScore() < 21){
                System.out.println("Want a hit? (y/n)");
                char opt = scan.next().toLowerCase().charAt(0);
                System.out.print("\n");
                if (opt == 'y'){
                    currPlayer.getHand().addCard(myDeck.dealCard());
                }else{
                    break;
                }
                currPlayer.getHand().printHand();
            }
        }
    }
    
    public void playOutDealerHand(){
        while (dealerHand.getScore() < 16 && dealerHand.getNumOfCards() < 5){
            dealerHand.addCard(myDeck.dealCard());
        }
        System.out.println("Dealer's Hand: ");
        dealerHand.printHand();
    }
    
    public void declareWinners(){
        if(dealerHand.getScore() > 21){
            System.out.println("The dealer loses, everyone else wins!");
        }else{
            for (Player currPlayer : myPlayers){
                if(currPlayer.getHand().getNumOfCards() > 5 || 
                        currPlayer.getHand().getScore() > 21 || 
                        (currPlayer.getHand().getNumOfCards() > 5 &&
                        currPlayer.getHand().getScore() > 21)){
                    System.out.println(currPlayer.getName() + " busted!");
                }else{
                    if(currPlayer.getHand().getScore() == 21){
                        if (dealerHand.getScore() == 21){
                            System.out.println("The dealer defeats " + 
                                    currPlayer.getName() + 
                                    " with a score of 21!");
                        }else{
                            System.out.println(currPlayer.getName() + 
                                    " defeats the dealer with a score of 21!");
                        }
                    }else if(currPlayer.getHand().getScore() > 
                            dealerHand.getScore()){
                        System.out.println(currPlayer.getName() + 
                                " defeats the dealer with a score of " + 
                                currPlayer.getHand().getScore());
                    }else{
                        System.out.println("The dealer defeats " + 
                                    currPlayer.getName() + 
                                    " with a score of " + 
                                dealerHand.getScore());
                    }
                }
            }
        }
    }
    
    private void initPlayers(int numOfPlayers){
        myPlayers = new Player[numOfPlayers];
        for(int i = 0; i < myPlayers.length; i++){
            System.out.println("Player " + (i+1) + ", what's your name?");
            String name = scan.next();
            if(name.equals("")){
                myPlayers[i] = new Player(i+1);
            }else{
                myPlayers[i] = new Player(name);
            }
        }
    }
    
}

package com.example.project;
import java.util.ArrayList;

public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards = new ArrayList<>(); //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
   
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){      
        for (int i = 0; i < 3; i++) {
            allCards.add(communityCards.get(i));
        }
        for (int i = 0; i < 2; i++) {
            allCards.add(hand.get(i));
        }
        boolean suits = false;
        boolean consecutive = false;
        if (findSuitFrequency().get(0) == 5 || findSuitFrequency().get(1) == 5 || findSuitFrequency().get(2) == 5 || findSuitFrequency().get(3) == 5) {
            suits = true;
            if (findRankingFrequency().get(12) == 1 && findRankingFrequency().get(11) == 1 && findRankingFrequency().get(10) == 1 && findRankingFrequency().get(9) == 1 && findRankingFrequency().get(8) == 1) {
                return "Royal Flush";
            }
        }
        for (int i = 0; i < ranks.length - 4; i++) {
            for (int j = 1; j < 5; j++) {
                if (findRankingFrequency().get(i) == 1 && findRankingFrequency().get(i + j) == 1) {
                   consecutive = true;
                }
            }
        }
        return "Nothing";
    }

    public void sortAllCards(){
        allCards.sort(null);
    }


    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> frequency = new ArrayList();
        for (String string : ranks) {
            frequency.add(0);
        }
        for (int i = 0; i < allCards.size(); i++) {
            for (int j = 2; j < 15; j++) {
                if (Utility.getRankValue(allCards.get(i).getRank()) == j) {
                    frequency.set(j - 2, frequency.get(j - 2) + 1);
                }
            }
        }
        return frequency;
    }


    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> frequency = new ArrayList();
        for (String string : suits) {
            frequency.add(0);
        }
        for (int i = 0; i < allCards.size(); i++) {
            for (int j = 0; j < 4; j++) {
                if (allCards.get(i).getSuit() == suits[j]) {
                    frequency.set(j, frequency.get(j) + 1);
                }
            }
        }
        return frequency;
    }
   
    @Override
    public String toString(){
        return hand.toString();
    }

}




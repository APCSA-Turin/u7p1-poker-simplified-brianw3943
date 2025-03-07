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
    private boolean flush() {
        ArrayList<Integer> suitFrequency = findSuitFrequency();
        for (int frequency : suitFrequency) {
            if (frequency == 5) {
                return true;
            }
        }
        return false;
    }
    private boolean straight() {
        int consecutive = 1;
        for (int i = 0; i < allCards.size() - 1; i++) {
            int curRank = Utility.getRankValue(allCards.get(i).getRank());
            int nextRank = Utility.getRankValue(allCards.get(i + 1).getRank());
            if (nextRank == curRank + 1) {
                consecutive++;
                if (consecutive == 5) {
                    return true;
                }
            } else if (nextRank != curRank) {
                consecutive = 1;
            }
        }
        return false;
    }
    private boolean pair() {
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        for (int frequency : rankFrequency) {
            if (frequency == 2) {
                return true;
            }
        }
        return false;
    }
    private boolean twoPair() {
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        int pairCount = 0;
        for (int frequency : rankFrequency) {
            if (frequency == 2) {
                pairCount++;
            }
        }
        if (pairCount >= 2) {
            return true;
        } else {
            return false;
        }
    }
    private boolean threeOfAKind() {
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        for (int frequency : rankFrequency) {
            if (frequency == 3) {
                return true;
            }
        }
        return false;
    }
    private boolean fullHouse() {
        if (two)
    }
    private boolean fourOfAKind() {
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        for (int frequency : rankFrequency) {
            if (frequency == 4) {
                return true;
            }
        }
        return false;
    }
    private boolean straightFlush() {

    }
    private boolean royalFlush() {

    }

    public String playHand(ArrayList<Card> communityCards){      
        for (int i = 0; i < 3; i++) {
            allCards.add(communityCards.get(i));
        }
        for (int i = 0; i < 2; i++) {
            allCards.add(hand.get(i));
        }
        
        
        return "Nothing";
    }

    public void sortAllCards(){
        for (int i = 0; i < allCards.size() - 1; i++) {
            for (int j = 0; j < allCards.size() - i - 1; j++) {
                int rank = Utility.getRankValue(allCards.get(j).getRank());
                int rank2 = Utility.getRankValue(allCards.get(j + 1).getRank());
                if (rank > rank2) {
                    Card temp = allCards.get(j);
                    allCards.set(j, allCards.get(j + 1));
                    allCards.set(j + 1, temp);
                }
            }
        }
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




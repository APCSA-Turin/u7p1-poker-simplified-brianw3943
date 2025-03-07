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
        if (pair() && threeOfAKind()) {
            return true;
        }
        return false;
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
        if (straight() && flush()) {
            return true;
        }
        return false;
    }
    private boolean royalFlush() {
        if (straightFlush() && allCards.get(allCards.size() - 1).getRank().equals("A")) {
            return true;
        }
        return false;
    }

    public String playHand(ArrayList<Card> communityCards){      
        allCards.clear();
        //Fills allCards with communityCards and cards in hand
        for (int i = 0; i < 3; i++) {
            allCards.add(communityCards.get(i));
        }
        for (int i = 0; i < 2; i++) {
            allCards.add(hand.get(i));
        }
        // Sorts the cards
        sortAllCards();
        // Checks for Royal Flush
        if (royalFlush()) {
            return "Royal Flush";
        }
        if (straightFlush()) {
            return "Straight Flush";
        }
        // Checks for Four of a Kind
        if (fourOfAKind()) {
            return "Four of a Kind";
        }
        // Checks for Full House
        if (fullHouse()) {
            return "Full House";
        }
        // Checks for Flush
        if (flush()) {
            return "Flush";
        }
        // Checks for Straight
        if (straight()) {
            return "Straight";
        }
        // Checks for Three of a Kind
        if (threeOfAKind()) {
            return "Three of a Kind";
        }
        // Checks for Two Pair
        if (twoPair()) {
            return "Two Pair";
        }
        // Checks for A Pair
        if (pair()) {
            return "A Pair";
        }
        ArrayList<Card> playerHand = new ArrayList<>(hand);

        // Find the highest card in the player's hand
        String handHighestCard = hand.get(0).getRank();
        int handHighestCardValue = Utility.getRankValue(handHighestCard);
        int secondCardValue = Utility.getRankValue(hand.get(1).getRank());
        if (secondCardValue > handHighestCardValue) {
            handHighestCard = hand.get(1).getRank();
            handHighestCardValue = secondCardValue;
        }
        for (Card card : communityCards) {
            int communityCardValue = Utility.getRankValue(card.getRank());
            if (communityCardValue >= handHighestCardValue) {
                return "Nothing"; // Highest card is in community cards
            }
        }
        return "High Card"; // Highest card is not in community cards
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




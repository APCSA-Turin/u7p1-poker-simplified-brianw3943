package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        // Determine hand for both players
        String p1BestHand = p1.playHand(communityCards);
        String p2BestHand = p2.playHand(communityCards);
        int p1Rank = Utility.getHandRanking(p1BestHand);
        int p2Rank = Utility.getHandRanking(p2BestHand);
        // Compare hand rankings
        if (p1Rank > p2Rank) {
            return "Player 1 wins!";
        } else if (p2Rank > p1Rank) {
            return "Player 2 wins!";
        } else {
            // Compares highest cards of both players (as a tiebreaker)
            int p1HighestCard = Utility.getRankValue(p1.getHand().get(0).getRank());
            int p2HighestCard = Utility.getRankValue(p2.getHand().get(0).getRank());
            if (p1.getHand().get(0).getRank().compareTo(p1.getHand().get(1).getRank()) < 0) {
                p1HighestCard = Utility.getRankValue(p1.getHand().get(1).getRank());
            }
            if (p2.getHand().get(0).getRank().compareTo(p2.getHand().get(1).getRank()) < 0) {
                p2HighestCard = Utility.getRankValue(p2.getHand().get(1).getRank());
            }
            if (p1HighestCard > p2HighestCard) {
                return "Player 1 wins!";
            } else if (p1HighestCard < p2HighestCard) {
                return "Player 2 wins!";
            }
            return "Tie!";
        }
    }

    public static void play(){ //simulate card playing
    
    }
        
        

}
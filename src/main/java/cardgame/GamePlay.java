package cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cardgame.Card.Rank;

public class GamePlay {
    /**
     * start function for start and restart the game
     * @param deck passing the deck for pass the Cards to the player
     * @param x @param y for access the recieveIntialCard function
     * @return deck because we need the updated deck;
     */
    List<Card> start(List<Card> deck, PlayerX x, PlayerY y) {
        int i;
        List<Card> playerX=new ArrayList<>();
        List<Card> playerY=new ArrayList<>();
        for(i=0;i<10;i++) {
            if(i%2==0)
                playerX.add(deck.get(0));
            else
                playerY.add(deck.get(0));
            deck.remove(0);
        }
        logger.log();
        logger.log("Player X : ");
        for(i=0;i<playerX.size();i++) {
            System.out.println(playerX.get(i).getRank()+" "+playerY.get(i).getSuit()+" ");
        }
        logger.log("----------------------------------------------------");
        logger.log("Player Y : ");
        for(i=0;i<playerY.size();i++) {
            logger.log(playerY.get(i).getRank()+" "+playerY.get(i).getSuit()+" ");
        }
        logger.log();
        x.receiveInitialCards(playerX);
        y.receiveInitialCards(playerY);
        return deck;
    }
    /**
     * Play function for play the Crazy 8's and get points
     * @param deck is passing for access the deck cards to play the game;
     * @param x @param y for access their game Strategy and their Cards
     */
    void play(List<Card> deck, PlayerX x, PlayerY y) {
        GamePlay gamePlay=new GamePlay();
        int point1=0,i,point2=0;
        Card topCard;
        topCard=deck.get(0);//topcard at index position 0
        deck.remove(0);
        logger.log("TopCard : "+topCard.getRank()+" "+topCard.getSuit());
        Card.Suit deCsuit=null;
        while(point1<200 && point2<200) {
            /**For player 2 to draw the 3 cards for matching with the top card
             *
             */
            for(i=0;i<3;i++) {
                if(x.shouldDrawCard(topCard, deCsuit)) {   //bug!!
                    if(deck.size()!=0) {
                        y.receiveCard(deck.get(0));
                        deck.remove(0);
                    }
                }
                else {
                    topCard=y.playCard(topCard);
                    System.out.println("TopCard : "+topCard.getRank()+" "+topCard.getSuit());
                    if(topCard.getRank()==Rank.EIGHT  &&  y.myCards.size()!=0) {
                        deCsuit=y.declareSuit();
                    }
                    break;
                }
            }
            /**
             * for player 3 to draw the 3 cards for mayching with the top card
             */
            for(i=0;i<3;i++) {
                if(x.shouldDrawCard(topCard, deCsuit)) {
                    if(deck.size()!=0) {
                        x.receiveCard(deck.get(0));
                        deck.remove(0);

                    }
                }
                else {
                    topCard=x.playCard(topCard);
                    System.out.println("TopCard : "+topCard.getRank()+" "+topCard.getSuit());
                    if(topCard.getRank().equals(Rank.EIGHT)  &&  x.myCards.size()!=0) {
                        deCsuit=x.declareSuit();
                    }
                    break;
                }
            }
            /** Points for players x and y
             *
             */
            if(x.myCards.size()==0  ||  deck.size()==0) {
                point1=x.getScore(point1);
                System.out.println("playerX :"+point1);
            }
            if(y.myCards.size()==0  ||  deck.size()==0) {
                point2=y.getScore(point2);
                System.out.println("playerY :"+point2);
            }
            if(deck.size()==0  && point1<200  &&  point2<200) {
                deck=Card.getDeck();
                Collections.shuffle(deck);
                deck=gamePlay.start(deck,x,y);
            }
        }
        gamePlay.results(point1,point2);
    }
    /**
     * results function for show the results in console
     * @param p1 @param p2 are the points of the player 1 and player 2;
     */
    void results(int p1,int p2) {
        if(p1>=200) {
            logger.log("playerX wins");
        }
        else if(p2>=200) {
            logger.log("playerY wins");
        }
    }
}

package cardgame;

import java.util.List;

public class PlayerY implements PlayerStrategy{
    int playerId;
    List<Integer> opponentIds;
    List<Card> myCards;
    Card topPileCard;
    Card.Suit changedSuit;

}
    public void receiveInitialCards(List<Card> cards) {
        this.myCards=cards;

    }
    public boolean shouldDrawCard(Card topPileCard, Card.Suit changedSuit) {

        this.topPileCard=topPileCard;
        this.changedSuit=changedSuit;
        if(changedSuit==null) {
            for(int i=0;i<myCards.size();i++) {
                if(myCards.get(i).getSuit().equals(topPileCard.getSuit())||myCards.get(i).getRank().equals(topPileCard.getRank())) {
                    return false;
                }
            }
        }
        else {
            for(int i=0; i<myCards.size(); i++) {
                if(myCards.get(i).getSuit().equals(changedSuit)) {
                    return false;
                }
            }
        }
        return true;
rm
    }
    public void receiveCard(Card drawnCard) {
        System.out.println("PlayerY recieved :"+drawnCard.getRank()+" "+drawnCard.getSuit());
        myCards.add(drawnCard);
    }
    public Card playCard(Card topPileCard) {

        Card outCard=null;
        if(changedSuit==null) {
            for(int i=0; i<myCards.size(); i++)
            {
                if(myCards.get(i).getSuit().equals(topPileCard.getSuit()) || myCards.get(i).getRank().equals(topPileCard.getRank()))
                {
                    System.out.println("PlayerY played: "+myCards.get(i).getRank()+" "+myCards.get(i).getSuit());
                    outCard=myCards.get(i);
                    myCards.remove(i);
                    break;
                }
            }
        }
        else {
            for(int i=0;i<myCards.size();i++) {
                if(myCards.get(i).getSuit().equals(changedSuit)) {
                    System.out.println("PlayerY played: "+myCards.get(i).getRank()+" "+myCards.get(i).getSuit());
                    outCard=myCards.get(i);
                    myCards.remove(i);
                    break;
                }
            }
        }
        return outCard;

    }
    public Card.Suit declareSuit(){
        Card wishCard=myCards.get(0);
        int max=52,count=0;
        for(int i=0;i<myCards.size();i++) {
            count=0;
            for(int j=0;j<myCards.size();j++) {
                if(myCards.get(i)==myCards.get(j))
                    count++;
            }
            if(count<max) {
                max=count;
                wishCard=myCards.get(i);
            }
        }
        System.out.println("Delcare suit: "+wishCard.getSuit());
        return wishCard.getSuit();

    }
    public void processOpponentActions(List<PlayerTurn> opponentActions) {

    }
    public void reset() {

    }
    public int getScore(int point) {
        for(int i=0;i<myCards.size();i++) {
            if(point<=200)
                point+=myCards.get(i).getPointValue();
        }
        return point;
    }
}

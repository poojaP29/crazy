package cardgame;
import java.util.*;
public class Game {
    public static void main(String[] args) {
        GamePlay gamePlay=new GamePlay();

/**Creating object x and y for playerX and playerY respectively..
 *
 */
        PlayerX x=new PlayerX();
        PlayerY y=new PlayerY();
        /** we need to get the card using List
         * And store in the deck
         * And we have to shuffle the deck cards using inbuilt shuffle() method in Collection framework
         */

        List<Card> deck=new ArrayList<>();
        deck=Card.getDeck();
        Collections.shuffle(deck);

/** We passing arguments to the Game play class of deck and two players  x and y for two functions start and play..
 *
 */
        deck=gamePlay.start(deck,x,y);
        gamePlay.play(deck,x,y);;
    }
}
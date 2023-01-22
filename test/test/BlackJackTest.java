package test;
import java.util.*;
import model.*;
import model.BlackJack.Card;
import model.BlackJack.Hand;
import model.BlackJack.Player;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

public class BlackJackTest{
         
    @Test
    public void test_hand_value_with_one_card() {
        assertEquals(3, BlackJack.createHand(Card._3).value());
        assertEquals(10, BlackJack.createHand(Card._10).value());
        assertEquals(10, BlackJack.createHand(Card.Jack).value());
        assertEquals(10, BlackJack.createHand(Card.Queen).value());
        assertEquals(10, BlackJack.createHand(Card.King).value());
        assertEquals(11, BlackJack.createHand(Card.Ace).value());
    }
    
    @Test
    public void test_hand_value_with_two_cards() {
        assertEquals(8, BlackJack.createHand(Card._3, Card._5).value());        
    }
    
    @Test
    public void test_hand_is_blackjack() {
        assertEquals(false, BlackJack.createHand(Card._3, Card._5).isBlackJack());        
        assertEquals(true, BlackJack.createHand(Card.Ace, Card.Jack).isBlackJack());
        assertEquals(true, BlackJack.createHand(Card.Ace, Card.King).isBlackJack());       
    }
    
    @Test
     public void test_hand_not_blackjack() {
       assertEquals(false, BlackJack.createHand(Card._5, Card._6, Card.King).isBlackJack());      
       assertEquals(false, BlackJack.createHand(Card._5, Card._6).isBlackJack());
       assertEquals(false, BlackJack.createHand(Card._5, Card._9).isBlackJack());
    }

    @Test
    public void test_hand__not_bust() {
       assertEquals(false, BlackJack.createHand(Card._4, Card._3).isBust());    
       assertEquals(false, BlackJack.createHand(Card._5, Card._6).isBust()); 
       assertEquals(false, BlackJack.createHand(Card.Ace, Card._3).isBust()); 
    }
    
    @Test
    public void test_hand_is_bust() {
       assertEquals(true, BlackJack.createHand(Card._4,Card.Jack, Card.King).isBust());
    }
        
}

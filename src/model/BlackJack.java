package model;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

import java.util.stream.Stream;



public class BlackJack {

    private final Player croupier;
    private List<Player> players;
    
    public BlackJack(Player croupier, Player... players) {
        this.croupier = croupier;
        this.players = new ArrayList<>(Arrays.asList(players));
    }
    
    public final List<Player> getWinners(Hand croupierh, List<Card> deck, Hand... playersHand) {
        
        for (Card card: croupierh.getCards()) {
            if (!croupier.addCard(card)) break;
        }
        
        while (deck.size() > 0 && croupier.getHand().value() < 17) croupier.addCard(deck.remove(0));
        if (croupier.getHand().isBlackJack()) return  new ArrayList<>();
        
        List<Player> winners = new ArrayList<>();
        for (int i = 0; i<= this.players.size(); i++) {
            for (Card card: playersHand[i].getCards()) {
                if (!players.get(i).addCard(card)) break;
            }
            
            if (players.get(i).getHand().value() > croupier.getHand().value()){ 
                winners.add(players.get(i));
            }
            
            if (players.get(i).getHand().isBlackJack()) return winners;
            
        }
        return winners;
        
    }

    public static Hand createHand(Card... cards) {
        return new Hand() {
            
            private List<Card> cardsl = new ArrayList<>(Arrays.asList(cards));
            
            public List<Card> getCards(){
                return cardsl;
            }
            
              public boolean addCard(Card c) {
                 return (!isBust() && !isBlackJack()) ? cardsl.add(c) : false;
            }
            
            public int value() {
                return canUseAceExtendedValue() ? sum() + 10 : sum();
            }

            private int sum() {
                return Stream.of(cards).mapToInt(c->c.value()).sum();
            }

            private boolean canUseAceExtendedValue() {
                return sum() <= 11 && containsAce();
            }

            private boolean containsAce() {
                return Stream.of(cards).anyMatch(c->c==c.Ace);
            }

            public boolean isBlackJack() {
                return value() == 21 && cards.length == 2;
            }

            @Override
            public boolean isBust() {                
                return value() > 21;
            }

        };
    }

    public interface Hand {
        int value();
        boolean isBlackJack();
        boolean isBust();
        boolean addCard(Card c);

        public List<Card> getCards();
    }

    public enum Card {
        Ace, _2, _3, _4, _5, _6, _7, _8, _9, _10, Jack, Queen, King;

       
        public boolean isFace() {
            return this == Jack || this == Queen || this == King;            
        }

        public int value() {
            return isFace() ? 10 : ordinal() + 1;
        }
        
        
    } 
    
    public static class Player {
        private Hand hand;
        private String name;
        
        public Player(String name) {
            this.hand = createHand();
            this.name = name;
        }
        
        public String getName(){
            return this.name;
        }
        
        public Hand getHand() {
            return this.hand;
        }
        
        public boolean addCard(Card c) {
            return hand.addCard(c);
        }
        
        @Override public String toString() {
            return getName(); 
        }
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;;

public class HandCard {
    // fields
    private final int id;
    private final List<Card> handCards;
    // private static Integer totalNumPlayers;
    
    // methods
    public HandCard(int id) {
        // if (totalNumPlayers == null) totalNumPlayers = -1;
        // if (id <= totalNumPlayers) throw new RuntimeException("id already exists");
        if (id < 0) throw new RuntimeException("id cannot be negative");
        this.id = id;
        handCards = new ArrayList<>();
    }
    
    public HandCard() {
        this(0);
    }
    
    public void addCard(Card card) {
        if (card == null) throw new IllegalArgumentException("Not able to add null to hand cards");
        handCards.add(card);
    }
    
    public void addCard(List<Card> listCards) {
        if (listCards == null || listCards.size() == 0)
            throw new IllegalArgumentException("Not able to add null list to hand cards");
        for (Card card : listCards) {
            addCard(card);
        }
    }
    
    public boolean removeCard(int i) {
        if (i < 0 || i > handCards.size())
            return false;
        while (i-- > 0)
            removeCard();
        return true;
    }
    
    public boolean removeCard() {
        handCards.remove(handCards.size() - 1);
        return true;
    }
    
    public boolean removeCard(Card card) {
        if (card == null)
            return false;
        for (Card hand : handCards) {
            if (hand.equals(card)) {
                handCards.remove(hand);
                return true;
            }
        }
        return false;
    }
    
    public void showHand() {
        System.out.println(handCards.toString());
    }
    
    public static void main(String[] args) {
        HandCard hc = new HandCard();
        hc.addCard(new Card(1, Suit.HEART));
        // hc.removeCard(0);
        // hc.removeCard(new Card(1, Suit.HEART));
        System.out.print(hc.removeCard(new Card(1, Suit.HEART)));
        hc.showHand();
        List<Card> list = new LinkedList<>();
        for (Suit suit : Suit.values()) {
            for (int i = 1; i < 14; ++i) {
                list.add(new Card(i, suit));
            }
        }
        hc.addCard(list);
        hc.showHand();
    }
}

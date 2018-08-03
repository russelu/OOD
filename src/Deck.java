import java.util.*;

public class Deck {
    private final List<Card> cards;

    // methods
    public Deck() {
        cards = new ArrayList<>(); // init cards holder
        for (Suit suit : Suit.values()) {
            for (int i = 1; i < 14; ++i) {
                cards.add(new Card(i, suit)); // add cards
            }
        }
    }

    public void shuffle() {
        Random random = new Random();
        for (int j = cards.size() - 1; j >= 0; --j) {
            int i = random.nextInt(j); // backward swap
            swap(i, j);
        }
    }

    private void swap(int i, int j) {
        Card tmp = cards.get(i);
        cards.set(i, cards.get(j));
        cards.set(j, tmp);
    }

    public Card deal() { // deal one
        if (cards.isEmpty())
            throw new NoSuchElementException("deck is empty");
        int lastIndex = cards.size() - 1;
        Card popCard = cards.get(lastIndex);
        cards.remove(lastIndex);
        return popCard;
    }

    public List<Card> deal(int n) { // deal n
        if (n <= 0 || n > cards.size())
            throw new IndexOutOfBoundsException("can't not deal this many cards: " + n);
        List<Card> popCards = new LinkedList<>(); // list of cards
        int lastIndex = cards.size() - 1;
        while (n-- > 0) {
            popCards.add(cards.get(lastIndex));
            cards.remove(lastIndex);
            lastIndex--;
        }
        return popCards;
    }
    
    public void print() {
        System.out.println("Printing deck: ");
        for (Card card : cards) {
            card.print();
            System.out.print(", ");
        }
        System.out.println();
    }
}

class Card {
    // fields
    private final int val; // all fields final
    private final Suit suit;

    // methods
    public Card(int val, Suit suit) {
        this.val = val;
        this.suit = suit;
    }

    public int getVal() {
        return val;
    }

    public Suit getSuit() {
        return suit;
    }

    public String show() {
        String show = String.valueOf(suit) + String.valueOf(val);
        System.out.print(show);
        return show;
    }
    
    public void print() {
        System.out.print(this.toString());
    }
    
    public String toString() {
        return String.valueOf(suit) + String.valueOf(val);
    }
    
    public boolean equals(Card other) {
        return this.val == other.val && this.suit == other.suit;
    }
}

enum Suit {
    HEART,
    DIAMOND,
    CLUB,
    SPADE
}

import java.util.*;

public class Deck {
    private final List<Card> cards;

    // methods
    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (int i = 1; i < 14; ++i) {
                cards.add(new Card(i, suit));
            }
        }
    }

    public void shuffle() {
        Random random = new Random();
        for (int j = cards.size() - 1; j >= 0; --j) {
            int i = random.nextInt(j);
            swap(i, j);
        }
    }

    private void swap(int i, int j) {
        Card tmp = cards.get(i);
        cards.set(i, cards.get(j));
        cards.set(j, tmp);
    }

    public Card deal() {
        if (cards.isEmpty())
            throw new NoSuchElementException("deck is empty");
        int lastIndex = cards.size() - 1;
        Card popCard = cards.get(lastIndex);
        cards.remove(lastIndex);
        return popCard;
    }

    public List<Card> deal(int n) {
        if (n <= 0 || n > cards.size())
            throw new IndexOutOfBoundsException("can't not deal this many cards: " + n);
        List<Card> popCards = new LinkedList<>();
        int lastIndex = cards.size() - 1;
        while (n-- > 0) {
            popCards.add(cards.get(lastIndex));
            cards.remove(lastIndex);
            lastIndex--;
        }
        return popCards;
    }
}

class Card {
    // fields
    private final int val;
    private final Suit suit;

    // methods
    public Card(int val, Suit suit) {
        this.val = val;
        this.suit = suit;
    }

    public String show() {
        String show = String.valueOf(suit) + String.valueOf(val);
        System.out.print(show);
        return show;
    }
}

enum Suit {
    HEART,
    DIAMOND,
    CLUB,
    SPADE
}
import java.util.*;

public class BlackJackHandCard extends HandCard {
    // fields
    private int notASum;
    private int numA;
    private static final int TWENTY_ONE = 21;
    
    // methods
    public BlackJackHandCard(int id) {
        super(id);
        notASum = 0;
        numA = 0;
    }
    
    public void addCard(Card card) {
        super.addCard(card);
        int val = card.getValue();
        if (val >= 10) notASum += 10;
        else if (val > 1) notASum += val;
        else numA++;
    }
    
    public int score() {
        int sum = notASum + numA;
        return numA == 0 || sum + 10 > TWENTY_ONE ? sum: sum + 10;
    }
    
    public boolean isBursted() {
        return notASum + numA > TWENTY_ONE;
    }
    
    public boolean is21() {
        return score() == TWENTY_ONE;
    }
    
    public boolean isBJ() {
        return numA == 2 && notASum == 0;
    }
    
    public static void main(String[] args) {
        BlackJackHandCard bjhc = new BlackJackHandCard(0);
        // System.out.println(bjhc.getId());
        bjhc.addCard(new Card(1, Suit.SPADE));
        bjhc.addCard(new Card(1, Suit.HEART));
        // if (bjhc.isBJ()) System.out.println("Black Jack!!");
        bjhc.addCard(new Card(6, Suit.HEART));
        // bjhc.addCard(new Card(13, Suit.HEART));
        bjhc.addCard(new Card(3, Suit.HEART));
        System.out.println("Score: " + bjhc.score());
        // if (bjhc.isBursted()) System.out.println("Bursted!!");
        if (bjhc.is21()) System.out.println("21!!");
    }
}

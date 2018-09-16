package VoteSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Candidate implements Comparable<Candidate> {
    private final List<Ticket> tickets;
    private final String name;
    private final String id;
    protected int cnt;

    public Candidate(String name, String id) {
        this.name = name;
        this.id = id;
        tickets = new ArrayList<Ticket>();
    }

    public boolean addTicket(Ticket ticket) {
        if (ticket == null) return false;
        tickets.add(ticket);
        return true;
    }

    public String getName() {
        return name;
    }

    public void setCompareNumber(int cnt) {
        this.cnt = cnt;
    }

    public int getTicketNumber(long time) {
        int start = 0, end = tickets.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (tickets.get(mid).getTime() <= time) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    @Override
    public int compareTo(Candidate that) {
        return Integer.compare(that.cnt, cnt);
    }
}

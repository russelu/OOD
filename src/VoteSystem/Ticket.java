package VoteSystem;

public class Ticket {
    private final String id;
    private final Candidate candidate;
    private final long time;

    public Ticket(String id, Candidate candidate, long time) {
        this.candidate = candidate;
        this.id = id;
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}

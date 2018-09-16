package VoteSystem;

import java.util.*;

public class VoteSystem {
    private final Set<Candidate> candidates;

    private static VoteSystem instance;

    private VoteSystem() {
        candidates = new HashSet<>();
    }

    public static VoteSystem getInstance() {
        if (instance == null) {
            instance = new VoteSystem();
        }
        return instance;
    }

    public boolean addCandidate(final Candidate candidate) {
        return candidates.add(candidate);
    }

    public boolean voteForCandidate(final Candidate candidate, final long time) {
        if (!candidates.contains(candidate)) {
            return false;
        }

        final Ticket ticket = new Ticket(System.currentTimeMillis() + "", candidate, time);

        candidate.addTicket(ticket);
        return true;
    }

    public Set<Candidate> getCandidates() {
        return candidates;
    }

    public List<Candidate> findTopK(final long time, final int k) {
        if (time > System.currentTimeMillis() || k < 0) throw new IllegalArgumentException("");
        final List<Candidate> res = new ArrayList<Candidate>();

        for (Candidate candidate : candidates) {
            candidate.setCompareNumber(candidate.getTicketNumber(time));
            res.add(candidate);
        }

        Collections.sort(res);

        return (k >= res.size()) ? res: res.subList(0, k);
    }
}

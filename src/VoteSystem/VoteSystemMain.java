package VoteSystem;

import java.util.List;

public class VoteSystemMain {
    public static void main(String[] args) {
        final VoteSystem voteSystem = VoteSystem.getInstance();

        final Candidate Tom = new Candidate("Tom", "Tom123");
        final Candidate Jerry = new Candidate("Jerry", "Jerry123");
        final Candidate Ted = new Candidate("Ted","Ted123");

        voteSystem.addCandidate(Tom);
        voteSystem.addCandidate(Jerry);
        voteSystem.addCandidate(Ted);

        for(int i = 0; i < 6; i++){
            voteSystem.voteForCandidate(Tom,1);
            voteSystem.voteForCandidate(Tom,1);
            voteSystem.voteForCandidate(Jerry, 1);
        }

        for(int i = 0; i < 5; i++){
            voteSystem.voteForCandidate(Tom,2);
            voteSystem.voteForCandidate(Jerry, 2);
        }

        for(int i = 0; i < 4; i++){
            voteSystem.voteForCandidate(Tom,3);
            voteSystem.voteForCandidate(Ted, 3);
            voteSystem.voteForCandidate(Ted, 3);
            voteSystem.voteForCandidate(Ted, 3);
            voteSystem.voteForCandidate(Ted, 3);
        }

        List<Candidate> list1 = voteSystem.findTopK(1,2);
        List<Candidate> list2 = voteSystem.findTopK(2,2);
        List<Candidate> list3 = voteSystem.findTopK(3,3);

        for (Candidate candidate: list1){
            System.out.println(candidate.getName() + " has ticket " + candidate.getTicketNumber(1));
        }

        System.out.println("----------------");

        for (Candidate candidate: list2){
            System.out.println(candidate.getName() + " has ticket " + candidate.getTicketNumber(2));
        }

        System.out.println("----------------");

        for (Candidate candidate: list3){
            System.out.println(candidate.getName() + " has ticket " + candidate.getTicketNumber(3));
        }

    }
}

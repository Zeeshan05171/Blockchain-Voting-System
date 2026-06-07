import java.util.ArrayList;
import java.util.HashMap;

public class Blockchain {

    private ArrayList<Block> chain;

    public Blockchain() {
        chain = new ArrayList<Block>();

        System.out.println("  [+] Genesis Block created - Chain started!");
        chain.add(new GenesisBlock());
    }


    public void addBlock(Block newBlock) {
        chain.add(newBlock);
    }

    public void addBlock(String voterId, String candidate) {

        String previousHash = getLastBlock().getCurrentHash();

        String timestamp = new java.util.Date().toString();

        Block newBlock = new Block(voterId, candidate, timestamp, previousHash);

        chain.add(newBlock);
    }

    public Block getLastBlock() {
        return chain.get(chain.size() - 1);
    }

    public boolean isChainValid() {

        for (int i = 1; i < chain.size(); i++) {

            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            String recalculatedHash = currentBlock.calculateHash();
            if (!currentBlock.getCurrentHash().equals(recalculatedHash)) {
                System.out.println("  [!] Block " + i + " hash does not match! TAMPERED!");
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getCurrentHash())) {
                System.out.println("  [!] Block " + i + " link is broken! TAMPERED!");
                return false;
            }
        }
        return true;
    }

    public void displayChain() {
        System.out.println("\n  ============ BLOCKCHAIN ============");
        for (int i = 0; i < chain.size(); i++) {
            System.out.println("\n  --- Block #" + i + " ---");
            chain.get(i).displayBlock();
            if (i < chain.size() - 1) {
                System.out.println("          |");
                System.out.println("          v  (linked)");
            }
        }
        System.out.println("  ====================================\n");
    }

    public void declareWinner() {

        HashMap<String, Integer> voteCount = new HashMap<String, Integer>();

        for (int i = 1; i < chain.size(); i++) {
            String candidate = chain.get(i).getCandidate();

            if (voteCount.containsKey(candidate)) {
                voteCount.put(candidate, voteCount.get(candidate) + 1);
            } else {
                voteCount.put(candidate, 1);
            }
        }

        System.out.println("\n  ============ VOTE RESULTS ============");

        int totalVotes = chain.size() - 1;
        System.out.println("  Total Votes Cast: " + totalVotes);
        System.out.println("  --------------------------------------");

        String winner = "";
        int maxVotes = 0;

        for (String candidate : voteCount.keySet()) {
            int votes = voteCount.get(candidate);
            double percent = (totalVotes > 0) ? (votes * 100.0 / totalVotes) : 0;
            System.out.printf("  %-15s : %d votes (%.1f%%)\n", candidate, votes, percent);

            if (votes > maxVotes) {
                maxVotes = votes;
                winner = candidate;
            }
        }

        System.out.println("  --------------------------------------");
        if (!winner.isEmpty()) {
            System.out.println("  🏆 WINNER: " + winner + " with " + maxVotes + " votes!");
        } else {
            System.out.println("  No votes have been cast yet.");
        }
        System.out.println("  ======================================\n");
    }

    public int getChainSize() {
        return chain.size();
    }
}

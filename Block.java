
public class Block {

    private String voterId;
    private String candidate;
    private String timestamp;
    private String previousHash;
    private String currentHash;

    public Block(String voterId, String candidate, String timestamp, String previousHash) {

        this.voterId = encryptVoterId(voterId);

        this.candidate = candidate;
        this.timestamp = timestamp;
        this.previousHash = previousHash;

        this.currentHash = calculateHash();
    }

    public String calculateHash() {
        String dataToHash = voterId + candidate + timestamp + previousHash;

        int hashCode = dataToHash.hashCode();

        return "HASH-" + Math.abs(hashCode);
    }

    // Example: 'A' (65) -> 'D' (68)
    private String encryptVoterId(String id) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            encrypted.append((char) (c + 3));
        }
        return encrypted.toString();
    }

    public String getVoterId() {
        return voterId;
    }

    public String getCandidate() {
        return candidate;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public void displayBlock() {
        System.out.println("  +------------------------------------------+");
        System.out.println("  | Voter ID   : " + voterId);
        System.out.println("  | Candidate  : " + candidate);
        System.out.println("  | Time       : " + timestamp);
        System.out.println("  | Prev Hash  : " + previousHash);
        System.out.println("  | Curr Hash  : " + currentHash);
        System.out.println("  +------------------------------------------+");
    }
}

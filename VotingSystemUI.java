
import java.util.Scanner;   

public class VotingSystemUI {


    private Blockchain   blockchain;
    private VoterManager voterManager;
    private Scanner      scanner;

    
    public VotingSystemUI() {
        blockchain   = new Blockchain();
        voterManager = new VoterManager();
        scanner      = new Scanner(System.in);
    }

    
    public void showMenu() {
        int choice = 0;

       
        while (choice != 7) {
            printMenuBox();

            System.out.print("  Enter your choice (1-7): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();  
            } else {
                scanner.nextLine(); 
                System.out.println("  [!] Please enter numbers only!\n");
                continue;
            }

            
            switch (choice) {
                case 1: registerVoter();      break;
                case 2: castVote();           break;
                case 3: displayBlockchain();  break;
                case 4: validateChain();      break;
                case 5: showResults();        break;
                case 6: showStats();          break;
                case 7: exitSystem();         break;
                default:
                    System.out.println("  [!] Invalid choice! Please choose between 1 and 7.\n");
            }
        }
    }

    
    private void printMenuBox() {
        System.out.println("\n  +======================================+");
        System.out.println("  |   BLOCKCHAIN SECURE VOTING SYSTEM    |");
        System.out.println("  +======================================+");
        System.out.println("  |  1.  Register Voter                  |");
        System.out.println("  |  2.  Cast Vote                       |");
        System.out.println("  |  3.  View Blockchain                 |");
        System.out.println("  |  4.  Validate Chain                  |");
        System.out.println("  |  5.  View Results                    |");
        System.out.println("  |  6.  View Statistics                 |");
        System.out.println("  |  7.  Exit                            |");
        System.out.println("  +======================================+");
    }

    // ---- Feature 1: Voter Registration ----
    private void registerVoter() {
        System.out.println("\n  --- VOTER REGISTRATION ---");
        System.out.print("  Enter Voter ID (e.g. V001): ");
        String voterId = scanner.nextLine().trim();

        if (voterId.isEmpty()) {
            System.out.println("  [!] Voter ID cannot be empty!");
            return;
        }

        voterManager.registerVoter(voterId);
    }

    // ---- Feature 2: Vote Casting ----
    private void castVote() {
        System.out.println("\n  --- VOTE CASTING ---");
        System.out.print("  Enter your Voter ID: ");
        String voterId = scanner.nextLine().trim();

        // Check 1: Is registered?
        if (!voterManager.isRegistered(voterId)) {
            System.out.println("  [!] This Voter ID is not registered!");
            System.out.println("  [i] Register first using Option 1.");
            return;
        }

        // Check 2: Has already voted?
        if (voterManager.hasVoted(voterId)) {
            System.out.println("  [!] You have already cast your vote!");
            System.out.println("  [i] A voter can only vote once.");
            return;
        }

        // Candidates list 
        System.out.println("\n  Candidates:");
        System.out.println("  1. Hamza Khan");
        System.out.println("  2. Zeeshan Hyder");
        System.out.println("  3.  M Jameel");
        System.out.print("\n  Type candidate name: ");
        String candidate = scanner.nextLine().trim();

        
        if (!candidate.equals("Hamza Khan") &&
            !candidate.equals("Zeeshan Hyder") &&
            !candidate.equals("M Jameel")) {
            System.out.println("  [!] Invalid candidate name! Please choose from the list.");
            return;
        }

        // Add vote to the blockchain
        blockchain.addBlock(voterId, candidate);

        // Mark voter as voted (to prevent duplicate voting)
        voterManager.markAsVoted(voterId);

        System.out.println("  [✓] Vote successfully cast!");
        System.out.println("  [✓] Your vote has been secured in the blockchain.");
    }

    // ---- Feature 3: Blockchain Display ----
    private void displayBlockchain() {
        System.out.println("\n  --- BLOCKCHAIN DISPLAY ---");
        blockchain.displayChain();
    }

    // ---- Feature 4: Chain Validation ----
    private void validateChain() {
        System.out.println("\n  --- CHAIN VALIDATION ---");
        System.out.println("  Validating the chain...");

        boolean isValid = blockchain.isChainValid();

        if (isValid) {
            System.out.println("  [✓] Chain is VALID! No tampering detected.");
        } else {
            System.out.println("  [✗] Chain is INVALID! Tampering detected!");
        }
    }

    // ---- Feature 5: Results ----
    private void showResults() {
        System.out.println("\n  --- ELECTION RESULTS ---");

        if (blockchain.getChainSize() <= 1) {
            System.out.println("  [!] No votes have been cast yet.");
            return;
        }

        blockchain.declareWinner();
    }

    // ---- Feature 6: Statistics ----
    private void showStats() {
        System.out.println("\n  --- STATISTICS ---");
        System.out.println("  Registered Voters : " + voterManager.getTotalRegistered());
        System.out.println("  Votes Cast        : " + voterManager.getTotalVoted());
        System.out.println("  Blocks in Chain   : " + blockchain.getChainSize());
        System.out.println("  (1 Genesis Block + " + (blockchain.getChainSize() - 1) + " Vote Blocks)");
    }

    // ---- Feature 7: Exit ----
    private void exitSystem() {
        System.out.println("\n  Thank you! Exiting the Blockchain Voting System...");
        System.out.println("  Goodbye!\n");
        scanner.close();
    }
}

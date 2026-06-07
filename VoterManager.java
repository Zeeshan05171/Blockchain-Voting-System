
import java.util.HashSet;  

public class VoterManager {

   
    private HashSet<String> registeredVoters;


    private HashSet<String> votedVoters;

  
    public VoterManager() {
        registeredVoters = new HashSet<String>();
        votedVoters      = new HashSet<String>();
    }

   
    public boolean registerVoter(String voterId) {

        if (registeredVoters.contains(voterId)) {
            System.out.println("  [!] Voter ID " + voterId + " is already registered!");
            return false;
        }

       
        registeredVoters.add(voterId);
        System.out.println("  [✓] Voter " + voterId + " successfully registered!");
        return true;
    }

   
    public boolean isRegistered(String voterId) {
        return registeredVoters.contains(voterId);
    }

   
    public boolean hasVoted(String voterId) {
        return votedVoters.contains(voterId);
    }

    
    public void markAsVoted(String voterId) {
        votedVoters.add(voterId);
    }

    
    public int getTotalRegistered() {
        return registeredVoters.size();
    }

    
    public int getTotalVoted() {
        return votedVoters.size();
    }
}

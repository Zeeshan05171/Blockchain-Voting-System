

public class GenesisBlock extends Block {

    public GenesisBlock() {
       
        super("GENESIS", "NONE", "START", "0");
    }

   
    @Override
    public void displayBlock() {
        System.out.println("  +------------------------------------------+");
        System.out.println("  |  *** GENESIS BLOCK (Chain Start) ***     |");
        System.out.println("  | Prev Hash  : 0 (No previous block)      |");
        System.out.println("  | Curr Hash  : " + getCurrentHash());
        System.out.println("  +------------------------------------------+");
    }
}

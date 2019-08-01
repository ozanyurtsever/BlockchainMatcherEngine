import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Blockchain implements Serializable{
	private static final long serialVersionUID = 1L;
	CopyOnWriteArrayList<Block> chain = new CopyOnWriteArrayList<Block>();
	private int difficulty;
	String fileName;
	long initTime;


	public Blockchain(int difficulty) {
		this.difficulty = difficulty;
		//Creating and adding the genesis block to the chain
		Block newBlock = new Block(0);
		newBlock.Mine(this.difficulty, new Order(null, null, null, null, null, null ,null ,null));
		this.chain.add(newBlock);   //Genesis block of the blockchain
		initTime = System.currentTimeMillis();
	}

	public synchronized boolean addNewBlock(Block newBlock) {

		if (getLatestBlock().getId() + 1 != newBlock.getId()) {
			return false;
		}
		newBlock.setPreviousHash(this.getLatestBlock().getHashValue());
		this.chain.add(newBlock);
		//        System.out.println("New Block: " + newBlock);
		if(chain.size() % 100 == 0) {
			long fin = System.currentTimeMillis();
			System.out.println("Avg. throughput: "  + (fin - initTime)/chain.size());
		}
		return true;
	}

	public int getSize() {
		return this.chain.size();
	}

	public Block getLatestBlock() {
		return this.chain.get(this.chain.size()-1);
	}

	public int getDifficulty() {
		return this.difficulty;
	}

	public boolean verify() {
		for (Block block : this.chain) {
			String seedString = block.getSeedString();
			int blockSalt = block.getBlockSalt();

			if (!(block.verify(seedString, blockSalt)))
				return false;
		}
		return true;
	}

	public String toString() {

		String result = "";
		tryAgain:
			try {
				for (Block block : this.chain) {
					result += block.toString() + "\n";
				}
			} catch (Exception e) {
				break tryAgain;
			}
		return result;
	}

	public ArrayList<Block> findOrderbyId(String orderID) {
		ArrayList<Block> result = new ArrayList<Block>();
		for(Block block:chain) {
			if(block.getOrder().getOrderID().equals(orderID))
				result.add(block);
		}
		return result;
	}

	public ArrayList<Block> findOrderbyAccount(String accountID) {
		ArrayList<Block> result = new ArrayList<Block>();
		for(Block block:chain) {
			if(block.getOrder().getAccountID().equals(accountID))
				result.add(block);
		}
		return result;
	}

	public ArrayList<Block> findOrderbyCustomer(String customerID) {
		ArrayList<Block> result = new ArrayList<Block>();
		for(Block block:chain) {
			if(block.getOrder().getCustomerID().equals(customerID))
				result.add(block);
		}
		return result;
	}
	
}

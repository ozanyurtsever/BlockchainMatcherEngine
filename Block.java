import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hashValue;
	private int id;
	private int blockSalt = -1;
	private String previousHash;
	private Order order;
	public Block (int id) {
		this.id = id;
	}

	

	public void Mine(int difficulty, Order order) {

		this.order = order;

		String checkDiff = "";
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < difficulty; i++)
			builder.append('0');
		checkDiff = builder.toString();

		byte[] hashedByteArray;

		//Hashing has been changed according to proper data structure.
		do {
			blockSalt++;
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				hashedByteArray = digest.digest(((order.getOrderID() 
						+ order.getAccountID() 
						+ order.getCustomerID() 
						+ order.getDebitCredit() 
						+ order.getType() 
						+ order.getPrice()
						+ order.getUnits()
						+ order.getSentTime()) + blockSalt).getBytes(StandardCharsets.UTF_8));
				this.hashValue = bytesToHex(hashedByteArray);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

		} while (!hashValue.substring(0, difficulty).equals(checkDiff));

	}

	public boolean verify(String seedString, int blockSalt) {

		byte[] hashedByteArray;
		String verifyHashValue = "";


		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			hashedByteArray = digest.digest((seedString + blockSalt).getBytes(StandardCharsets.UTF_8));
			verifyHashValue = bytesToHex(hashedByteArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean isEqual = verifyHashValue.equals(this.getHashValue());
	
		return isEqual;
	
	}

	private static String bytesToHex(byte[] byteArray) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			String hexValue = Integer.toHexString(0xff & byteArray[i]);

			if(hexValue.length() == 1) hexString.append('0');

			hexString.append(hexValue);
		}
		return hexString.toString();
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getHashValue() {
		return this.hashValue;
	}

	public int getId() {
		return this.id;
	}

	public int getBlockSalt() { return this.blockSalt; }  //Is this safe to implement?

	public String getSeedString() { return (order.getOrderID() 
			+ order.getAccountID() 
			+ order.getCustomerID() 
			+ order.getDebitCredit() 
			+ order.getType() 
			+ order.getPrice()
			+ order.getUnits()
			+ order.getSentTime()); 
	}

	public String toString() {

		String result = "[ id: " + this.id + ", hash: " + this.hashValue + ", previous hash: " + this.previousHash + "]";

		return result;
	}

	public Order getOrder() {
		return order;
	}
}

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block{

	private static final long serialVersionUID = 1L;
	private String hashValue;
	private int id;
	private int blockSalt = -1;
	private String previousHash;
	private Order sendOrder, buyOrder;
	
	public Block (int id) {
		this.id = id;
	}

	

	public void Mine(int difficulty, Order sendOrder, Order buyOrder) {

		this.sendOrder = sendOrder;
		this.buyOrder = buyOrder;

		String checkDiff = "";
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < difficulty; i++)
			builder.append('0');
		checkDiff = builder.toString();

		byte[] hashedByteArray;

	
		do {
			blockSalt++;
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				hashedByteArray = digest.digest(((sendOrder.getOrderID() 
						+ sendOrder.getAccountID() 
						+ sendOrder.getCustomerID() 
						+ sendOrder.getDebitCredit() 
						+ sendOrder.getType() 
						+ sendOrder.getPrice()
						+ sendOrder.getUnits()
						+ sendOrder.getSentTime()
						+ buyOrder.getOrderID()
						+ buyOrder.getAccountID()
						+ buyOrder.getCustomerID()
						+ buyOrder.getDebitCredit()
						+ buyOrder.getType()
						+ buyOrder.getPrice()
						+ buyOrder.getUnits()
						+ buyOrder.getSentTime()) + blockSalt).getBytes(StandardCharsets.UTF_8));
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

	public String getSeedString() { return (sendOrder.getOrderID() 
			+ sendOrder.getAccountID() 
			+ sendOrder.getCustomerID() 
			+ sendOrder.getDebitCredit() 
			+ sendOrder.getType() 
			+ sendOrder.getPrice()
			+ sendOrder.getUnits()
			+ sendOrder.getSentTime()
			+ buyOrder.getOrderID()
			+ buyOrder.getAccountID()
			+ buyOrder.getCustomerID()
			+ buyOrder.getDebitCredit()
			+ buyOrder.getType()
			+ buyOrder.getPrice()
			+ buyOrder.getUnits()
			+ buyOrder.getSentTime()); 
	}

	public String toString() {

		String result = "{ id: " + this.id + ", hash: " + this.hashValue + ", previous hash: " + this.previousHash + ",\nsendOrder: " 
		+ sendOrder.toString() + "\n buyOrder:" + buyOrder.toString() + "}";

		return result;
	}

	public Order getSendOrder() {
		return sendOrder;
	}
}

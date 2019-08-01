import java.math.BigDecimal;
import java.util.Date;

public class Order {

	private String orderID; //Order id
	private BigDecimal price; //Fiyat
	private BigDecimal units; //Lot sayýsý
	private String type; //Tip
	private String debitCredit; //Alýþ satýþ
	private String accountID; //Hesap no
	private String customerID; //Müþteri no
	private Date sentTime; //Gönderim zamaný
	private String insrumentName; //GARAN vs;
	
	public Order(String orderID, BigDecimal price, BigDecimal units, String type, String accountID, String debitCredit,
			String customerID, String instrumentName) {
		this.orderID = orderID;
		this.price = price;
		this.accountID = accountID;
		this.customerID = customerID;
		this.debitCredit = debitCredit;
		this.insrumentName = instrumentName;
		this.units = units;
		this.sentTime = new Date();
	}

	public String getOrderID() {
		return orderID;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getUnits() {
		return units;
	}

	public String getType() {
		return type;
	}

	public String getDebitCredit() {
		return debitCredit;
	}

	public String getAccountID() {
		return accountID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public String getInsrumentName() {
		return insrumentName;
	}
	
	public String toString() {
		String result = "";
		result = "[orderID: " + this.orderID + ", customerID: " + this.customerID + ", accountID: " + this.accountID
				+ ", units: " + this.units + ", price: " + this.price + ", type: " + this.type + ", debitCredit: "
				+ this.debitCredit + ", sentTime: " + this.sentTime + "]";
		return result;
	}
}

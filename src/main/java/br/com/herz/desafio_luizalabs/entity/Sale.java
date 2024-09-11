package br.com.herz.desafio_luizalabs.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Sale {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	private String purchaserName;
	
	private String itemDescription;
	
	private BigDecimal itemPrice;
	
    private BigDecimal purchaseCount;
	
	private String merchantAddress;
	
	private String merchantName;

	public Sale() {
		super();
	}

    public Sale(String purchaserName, String itemDescription, BigDecimal itemPrice, BigDecimal purchaseCount,
			String merchantAddress, String merchantName) {
		super();
		this.purchaserName = purchaserName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
        this.purchaseCount = purchaseCount;
		this.merchantAddress = merchantAddress;
		this.merchantName = merchantName;
	}

	public UUID getId() {
		return id;
	}

	public String getPurchaserName() {
		return purchaserName;
	}

	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

    public BigDecimal getPurchaseCount() {
        return purchaseCount;
	}

    public void setPurchaseCount(BigDecimal purchaseCount) {
        this.purchaseCount = purchaseCount;
	}

	public String getMerchantAddress() {
		return merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

    @Override
    public int hashCode() {
        return Objects.hash(id, itemDescription, itemPrice, merchantAddress, merchantName, purchaseCount,
                purchaserName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sale other = (Sale) obj;
        return Objects.equals(id, other.id) && Objects.equals(itemDescription, other.itemDescription)
                && Objects.equals(itemPrice, other.itemPrice) && Objects.equals(merchantAddress, other.merchantAddress)
                && Objects.equals(merchantName, other.merchantName)
                && Objects.equals(purchaseCount, other.purchaseCount)
                && Objects.equals(purchaserName, other.purchaserName);
    }

}

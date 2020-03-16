package com.hthk.crm.domain.embed;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hthk.crm.domain.enumeration.RecordStatus;

/**
 * A AdvancePayment.
 */
public class AdvancePayment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Long advancePaymentId;

    private String offerId;

    private Integer advancePaymentMonths;

    private BigDecimal offerPrice;

    private BigDecimal offerDiscount;

    private RecordStatus status;

    
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAdvancePaymentId() {
        return advancePaymentId;
    }

    
    public void setAdvancePaymentId(Long advancePaymentId) {
        this.advancePaymentId = advancePaymentId;
    }

    public String getOfferId() {
        return offerId;
    }

    
    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Integer getAdvancePaymentMonths() {
        return advancePaymentMonths;
    }

    
    public void setAdvancePaymentMonths(Integer advancePaymentMonths) {
        this.advancePaymentMonths = advancePaymentMonths;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    
    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public BigDecimal getOfferDiscount() {
        return offerDiscount;
    }

    public void setOfferDiscount(BigDecimal offerDiscount) {
        this.offerDiscount = offerDiscount;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }

 
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdvancePayment)) {
            return false;
        }
        return id != null && id.equals(((AdvancePayment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OfferAdvancePayment{" +
            "id=" + getId() +
            ", advancePaymentId=" + getAdvancePaymentId() +
            ", offerId='" + getOfferId() + "'" +
            ", advancePaymentMonths=" + getAdvancePaymentMonths() +
            ", offerPrice=" + getOfferPrice() +
            ", offerDiscount=" + getOfferDiscount() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}

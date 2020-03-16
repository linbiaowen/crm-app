package com.hthk.crm.domain.embed;

import java.io.Serializable;

/**
 * A DeliveryOption
 */

public class DeliveryOption implements Serializable {

    private static final long serialVersionUID = 1L;

    private String deliveryOption;

	public String getDeliveryOption() {
		return deliveryOption;
	}

	public void setDeliveryOption(String deliveryOption) {
		this.deliveryOption = deliveryOption;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryOption)) {
            return false;
        }
        return deliveryOption != null && deliveryOption.equals(((DeliveryOption) o).deliveryOption);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DeliveryOption{" +
            "deliveryOption=" + getDeliveryOption() +
            "}";
    }

}

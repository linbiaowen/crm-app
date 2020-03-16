package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class SubsDeliveryItemDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubsDeliveryItemDetails.class);
        SubsDeliveryItemDetails subsDeliveryItemDetails1 = new SubsDeliveryItemDetails();
        subsDeliveryItemDetails1.setId("id1");
        SubsDeliveryItemDetails subsDeliveryItemDetails2 = new SubsDeliveryItemDetails();
        subsDeliveryItemDetails2.setId(subsDeliveryItemDetails1.getId());
        assertThat(subsDeliveryItemDetails1).isEqualTo(subsDeliveryItemDetails2);
        subsDeliveryItemDetails2.setId("id2");
        assertThat(subsDeliveryItemDetails1).isNotEqualTo(subsDeliveryItemDetails2);
        subsDeliveryItemDetails1.setId(null);
        assertThat(subsDeliveryItemDetails1).isNotEqualTo(subsDeliveryItemDetails2);
    }
}

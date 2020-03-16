package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class SubsItemDeliveryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubsItemDelivery.class);
        SubsItemDelivery subsItemDelivery1 = new SubsItemDelivery();
        subsItemDelivery1.setId("id1");
        SubsItemDelivery subsItemDelivery2 = new SubsItemDelivery();
        subsItemDelivery2.setId(subsItemDelivery1.getId());
        assertThat(subsItemDelivery1).isEqualTo(subsItemDelivery2);
        subsItemDelivery2.setId("id2");
        assertThat(subsItemDelivery1).isNotEqualTo(subsItemDelivery2);
        subsItemDelivery1.setId(null);
        assertThat(subsItemDelivery1).isNotEqualTo(subsItemDelivery2);
    }
}

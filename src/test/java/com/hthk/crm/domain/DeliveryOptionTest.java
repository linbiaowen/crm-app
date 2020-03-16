package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class DeliveryOptionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryOption.class);
        DeliveryOption deliveryOption1 = new DeliveryOption();
        deliveryOption1.setId("id1");
        DeliveryOption deliveryOption2 = new DeliveryOption();
        deliveryOption2.setId(deliveryOption1.getId());
        assertThat(deliveryOption1).isEqualTo(deliveryOption2);
        deliveryOption2.setId("id2");
        assertThat(deliveryOption1).isNotEqualTo(deliveryOption2);
        deliveryOption1.setId(null);
        assertThat(deliveryOption1).isNotEqualTo(deliveryOption2);
    }
}

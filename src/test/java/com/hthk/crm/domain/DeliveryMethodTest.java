package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class DeliveryMethodTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryMethod.class);
        DeliveryMethod deliveryMethod1 = new DeliveryMethod();
        deliveryMethod1.setId("id1");
        DeliveryMethod deliveryMethod2 = new DeliveryMethod();
        deliveryMethod2.setId(deliveryMethod1.getId());
        assertThat(deliveryMethod1).isEqualTo(deliveryMethod2);
        deliveryMethod2.setId("id2");
        assertThat(deliveryMethod1).isNotEqualTo(deliveryMethod2);
        deliveryMethod1.setId(null);
        assertThat(deliveryMethod1).isNotEqualTo(deliveryMethod2);
    }
}

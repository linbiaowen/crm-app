package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class SubscriptionProductTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubscriptionProduct.class);
        SubscriptionProduct subscriptionProduct1 = new SubscriptionProduct();
        subscriptionProduct1.setId("id1");
        SubscriptionProduct subscriptionProduct2 = new SubscriptionProduct();
        subscriptionProduct2.setId(subscriptionProduct1.getId());
        assertThat(subscriptionProduct1).isEqualTo(subscriptionProduct2);
        subscriptionProduct2.setId("id2");
        assertThat(subscriptionProduct1).isNotEqualTo(subscriptionProduct2);
        subscriptionProduct1.setId(null);
        assertThat(subscriptionProduct1).isNotEqualTo(subscriptionProduct2);
    }
}

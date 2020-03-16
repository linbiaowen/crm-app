package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class SubscriptionDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubscriptionDetails.class);
        SubscriptionDetails subscriptionDetails1 = new SubscriptionDetails();
        subscriptionDetails1.setId("id1");
        SubscriptionDetails subscriptionDetails2 = new SubscriptionDetails();
        subscriptionDetails2.setId(subscriptionDetails1.getId());
        assertThat(subscriptionDetails1).isEqualTo(subscriptionDetails2);
        subscriptionDetails2.setId("id2");
        assertThat(subscriptionDetails1).isNotEqualTo(subscriptionDetails2);
        subscriptionDetails1.setId(null);
        assertThat(subscriptionDetails1).isNotEqualTo(subscriptionDetails2);
    }
}

package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class SubscriptionProvisionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubscriptionProvision.class);
        SubscriptionProvision subscriptionProvision1 = new SubscriptionProvision();
        subscriptionProvision1.setId("id1");
        SubscriptionProvision subscriptionProvision2 = new SubscriptionProvision();
        subscriptionProvision2.setId(subscriptionProvision1.getId());
        assertThat(subscriptionProvision1).isEqualTo(subscriptionProvision2);
        subscriptionProvision2.setId("id2");
        assertThat(subscriptionProvision1).isNotEqualTo(subscriptionProvision2);
        subscriptionProvision1.setId(null);
        assertThat(subscriptionProvision1).isNotEqualTo(subscriptionProvision2);
    }
}

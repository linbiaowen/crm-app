package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class CustSubscriptionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustSubscription.class);
        CustSubscription custSubscription1 = new CustSubscription();
        custSubscription1.setId("id1");
        CustSubscription custSubscription2 = new CustSubscription();
        custSubscription2.setId(custSubscription1.getId());
        assertThat(custSubscription1).isEqualTo(custSubscription2);
        custSubscription2.setId("id2");
        assertThat(custSubscription1).isNotEqualTo(custSubscription2);
        custSubscription1.setId(null);
        assertThat(custSubscription1).isNotEqualTo(custSubscription2);
    }
}

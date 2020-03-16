package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class OrderProcessConfigTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderProcessConfig.class);
        OrderProcessConfig orderProcessConfig1 = new OrderProcessConfig();
        orderProcessConfig1.setId("id1");
        OrderProcessConfig orderProcessConfig2 = new OrderProcessConfig();
        orderProcessConfig2.setId(orderProcessConfig1.getId());
        assertThat(orderProcessConfig1).isEqualTo(orderProcessConfig2);
        orderProcessConfig2.setId("id2");
        assertThat(orderProcessConfig1).isNotEqualTo(orderProcessConfig2);
        orderProcessConfig1.setId(null);
        assertThat(orderProcessConfig1).isNotEqualTo(orderProcessConfig2);
    }
}

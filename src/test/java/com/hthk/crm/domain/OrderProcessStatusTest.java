package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class OrderProcessStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderProcessStatus.class);
        OrderProcessStatus orderProcessStatus1 = new OrderProcessStatus();
        orderProcessStatus1.setId("id1");
        OrderProcessStatus orderProcessStatus2 = new OrderProcessStatus();
        orderProcessStatus2.setId(orderProcessStatus1.getId());
        assertThat(orderProcessStatus1).isEqualTo(orderProcessStatus2);
        orderProcessStatus2.setId("id2");
        assertThat(orderProcessStatus1).isNotEqualTo(orderProcessStatus2);
        orderProcessStatus1.setId(null);
        assertThat(orderProcessStatus1).isNotEqualTo(orderProcessStatus2);
    }
}

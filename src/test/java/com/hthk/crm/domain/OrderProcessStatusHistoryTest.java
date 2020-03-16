package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class OrderProcessStatusHistoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderProcessStatusHistory.class);
        OrderProcessStatusHistory orderProcessStatusHistory1 = new OrderProcessStatusHistory();
        orderProcessStatusHistory1.setId("id1");
        OrderProcessStatusHistory orderProcessStatusHistory2 = new OrderProcessStatusHistory();
        orderProcessStatusHistory2.setId(orderProcessStatusHistory1.getId());
        assertThat(orderProcessStatusHistory1).isEqualTo(orderProcessStatusHistory2);
        orderProcessStatusHistory2.setId("id2");
        assertThat(orderProcessStatusHistory1).isNotEqualTo(orderProcessStatusHistory2);
        orderProcessStatusHistory1.setId(null);
        assertThat(orderProcessStatusHistory1).isNotEqualTo(orderProcessStatusHistory2);
    }
}

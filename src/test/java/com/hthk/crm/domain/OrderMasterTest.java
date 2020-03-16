package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class OrderMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderMaster.class);
        OrderMaster orderMaster1 = new OrderMaster();
        orderMaster1.setId("id1");
        OrderMaster orderMaster2 = new OrderMaster();
        orderMaster2.setId(orderMaster1.getId());
        assertThat(orderMaster1).isEqualTo(orderMaster2);
        orderMaster2.setId("id2");
        assertThat(orderMaster1).isNotEqualTo(orderMaster2);
        orderMaster1.setId(null);
        assertThat(orderMaster1).isNotEqualTo(orderMaster2);
    }
}

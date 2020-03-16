package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class BillCycleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillCycle.class);
        BillCycle billCycle1 = new BillCycle();
        billCycle1.setId("id1");
        BillCycle billCycle2 = new BillCycle();
        billCycle2.setId(billCycle1.getId());
        assertThat(billCycle1).isEqualTo(billCycle2);
        billCycle2.setId("id2");
        assertThat(billCycle1).isNotEqualTo(billCycle2);
        billCycle1.setId(null);
        assertThat(billCycle1).isNotEqualTo(billCycle2);
    }
}

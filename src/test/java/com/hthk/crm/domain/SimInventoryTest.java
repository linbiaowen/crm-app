package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class SimInventoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SimInventory.class);
        SimInventory simInventory1 = new SimInventory();
        simInventory1.setId("id1");
        SimInventory simInventory2 = new SimInventory();
        simInventory2.setId(simInventory1.getId());
        assertThat(simInventory1).isEqualTo(simInventory2);
        simInventory2.setId("id2");
        assertThat(simInventory1).isNotEqualTo(simInventory2);
        simInventory1.setId(null);
        assertThat(simInventory1).isNotEqualTo(simInventory2);
    }
}

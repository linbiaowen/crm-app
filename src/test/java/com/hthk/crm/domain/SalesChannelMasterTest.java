package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class SalesChannelMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesChannelMaster.class);
        SalesChannelMaster salesChannelMaster1 = new SalesChannelMaster();
        salesChannelMaster1.setId("id1");
        SalesChannelMaster salesChannelMaster2 = new SalesChannelMaster();
        salesChannelMaster2.setId(salesChannelMaster1.getId());
        assertThat(salesChannelMaster1).isEqualTo(salesChannelMaster2);
        salesChannelMaster2.setId("id2");
        assertThat(salesChannelMaster1).isNotEqualTo(salesChannelMaster2);
        salesChannelMaster1.setId(null);
        assertThat(salesChannelMaster1).isNotEqualTo(salesChannelMaster2);
    }
}

package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class BlackListMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlackListMaster.class);
        BlackListMaster blackListMaster1 = new BlackListMaster();
        blackListMaster1.setId("id1");
        BlackListMaster blackListMaster2 = new BlackListMaster();
        blackListMaster2.setId(blackListMaster1.getId());
        assertThat(blackListMaster1).isEqualTo(blackListMaster2);
        blackListMaster2.setId("id2");
        assertThat(blackListMaster1).isNotEqualTo(blackListMaster2);
        blackListMaster1.setId(null);
        assertThat(blackListMaster1).isNotEqualTo(blackListMaster2);
    }
}

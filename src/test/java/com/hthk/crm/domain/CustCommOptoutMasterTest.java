package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class CustCommOptoutMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustCommOptoutMaster.class);
        CustCommOptoutMaster custCommOptoutMaster1 = new CustCommOptoutMaster();
        custCommOptoutMaster1.setId("id1");
        CustCommOptoutMaster custCommOptoutMaster2 = new CustCommOptoutMaster();
        custCommOptoutMaster2.setId(custCommOptoutMaster1.getId());
        assertThat(custCommOptoutMaster1).isEqualTo(custCommOptoutMaster2);
        custCommOptoutMaster2.setId("id2");
        assertThat(custCommOptoutMaster1).isNotEqualTo(custCommOptoutMaster2);
        custCommOptoutMaster1.setId(null);
        assertThat(custCommOptoutMaster1).isNotEqualTo(custCommOptoutMaster2);
    }
}

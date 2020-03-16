package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class CustAddressTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustAddress.class);
        CustAddress custAddress1 = new CustAddress();
        custAddress1.setId("id1");
        CustAddress custAddress2 = new CustAddress();
        custAddress2.setId(custAddress1.getId());
        assertThat(custAddress1).isEqualTo(custAddress2);
        custAddress2.setId("id2");
        assertThat(custAddress1).isNotEqualTo(custAddress2);
        custAddress1.setId(null);
        assertThat(custAddress1).isNotEqualTo(custAddress2);
    }
}

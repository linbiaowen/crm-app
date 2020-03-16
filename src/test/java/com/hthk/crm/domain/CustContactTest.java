package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class CustContactTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustContact.class);
        CustContact custContact1 = new CustContact();
        custContact1.setId("id1");
        CustContact custContact2 = new CustContact();
        custContact2.setId(custContact1.getId());
        assertThat(custContact1).isEqualTo(custContact2);
        custContact2.setId("id2");
        assertThat(custContact1).isNotEqualTo(custContact2);
        custContact1.setId(null);
        assertThat(custContact1).isNotEqualTo(custContact2);
    }
}

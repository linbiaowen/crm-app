package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class CustDocumentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustDocument.class);
        CustDocument custDocument1 = new CustDocument();
        custDocument1.setId("id1");
        CustDocument custDocument2 = new CustDocument();
        custDocument2.setId(custDocument1.getId());
        assertThat(custDocument1).isEqualTo(custDocument2);
        custDocument2.setId("id2");
        assertThat(custDocument1).isNotEqualTo(custDocument2);
        custDocument1.setId(null);
        assertThat(custDocument1).isNotEqualTo(custDocument2);
    }
}

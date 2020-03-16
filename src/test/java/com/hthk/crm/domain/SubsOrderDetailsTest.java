package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class SubsOrderDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubsOrderDetails.class);
        SubsOrderDetails subsOrderDetails1 = new SubsOrderDetails();
        subsOrderDetails1.setId("id1");
        SubsOrderDetails subsOrderDetails2 = new SubsOrderDetails();
        subsOrderDetails2.setId(subsOrderDetails1.getId());
        assertThat(subsOrderDetails1).isEqualTo(subsOrderDetails2);
        subsOrderDetails2.setId("id2");
        assertThat(subsOrderDetails1).isNotEqualTo(subsOrderDetails2);
        subsOrderDetails1.setId(null);
        assertThat(subsOrderDetails1).isNotEqualTo(subsOrderDetails2);
    }
}

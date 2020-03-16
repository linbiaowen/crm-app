package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class CfsServicesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CfsServices.class);
        CfsServices cfsServices1 = new CfsServices();
        cfsServices1.setId("id1");
        CfsServices cfsServices2 = new CfsServices();
        cfsServices2.setId(cfsServices1.getId());
        assertThat(cfsServices1).isEqualTo(cfsServices2);
        cfsServices2.setId("id2");
        assertThat(cfsServices1).isNotEqualTo(cfsServices2);
        cfsServices1.setId(null);
        assertThat(cfsServices1).isNotEqualTo(cfsServices2);
    }
}

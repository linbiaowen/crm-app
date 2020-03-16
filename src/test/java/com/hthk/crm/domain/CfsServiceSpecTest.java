package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class CfsServiceSpecTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CfsServiceSpec.class);
        CfsServiceSpec cfsServiceSpec1 = new CfsServiceSpec();
        cfsServiceSpec1.setId("id1");
        CfsServiceSpec cfsServiceSpec2 = new CfsServiceSpec();
        cfsServiceSpec2.setId(cfsServiceSpec1.getId());
        assertThat(cfsServiceSpec1).isEqualTo(cfsServiceSpec2);
        cfsServiceSpec2.setId("id2");
        assertThat(cfsServiceSpec1).isNotEqualTo(cfsServiceSpec2);
        cfsServiceSpec1.setId(null);
        assertThat(cfsServiceSpec1).isNotEqualTo(cfsServiceSpec2);
    }
}

package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class DataServiceSpecTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataServiceSpec.class);
        DataServiceSpec dataServiceSpec1 = new DataServiceSpec();
        dataServiceSpec1.setId("id1");
        DataServiceSpec dataServiceSpec2 = new DataServiceSpec();
        dataServiceSpec2.setId(dataServiceSpec1.getId());
        assertThat(dataServiceSpec1).isEqualTo(dataServiceSpec2);
        dataServiceSpec2.setId("id2");
        assertThat(dataServiceSpec1).isNotEqualTo(dataServiceSpec2);
        dataServiceSpec1.setId(null);
        assertThat(dataServiceSpec1).isNotEqualTo(dataServiceSpec2);
    }
}

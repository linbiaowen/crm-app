package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class SupremeMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupremeMaster.class);
        SupremeMaster supremeMaster1 = new SupremeMaster();
        supremeMaster1.setId("id1");
        SupremeMaster supremeMaster2 = new SupremeMaster();
        supremeMaster2.setId(supremeMaster1.getId());
        assertThat(supremeMaster1).isEqualTo(supremeMaster2);
        supremeMaster2.setId("id2");
        assertThat(supremeMaster1).isNotEqualTo(supremeMaster2);
        supremeMaster1.setId(null);
        assertThat(supremeMaster1).isNotEqualTo(supremeMaster2);
    }
}

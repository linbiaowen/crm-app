package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class ModelGroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModelGroup.class);
        ModelGroup modelGroup1 = new ModelGroup();
        modelGroup1.setId("id1");
        ModelGroup modelGroup2 = new ModelGroup();
        modelGroup2.setId(modelGroup1.getId());
        assertThat(modelGroup1).isEqualTo(modelGroup2);
        modelGroup2.setId("id2");
        assertThat(modelGroup1).isNotEqualTo(modelGroup2);
        modelGroup1.setId(null);
        assertThat(modelGroup1).isNotEqualTo(modelGroup2);
    }
}

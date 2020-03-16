package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class ModelCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModelCategory.class);
        ModelCategory modelCategory1 = new ModelCategory();
        modelCategory1.setId("id1");
        ModelCategory modelCategory2 = new ModelCategory();
        modelCategory2.setId(modelCategory1.getId());
        assertThat(modelCategory1).isEqualTo(modelCategory2);
        modelCategory2.setId("id2");
        assertThat(modelCategory1).isNotEqualTo(modelCategory2);
        modelCategory1.setId(null);
        assertThat(modelCategory1).isNotEqualTo(modelCategory2);
    }
}

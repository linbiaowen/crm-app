package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class ResourceSpecificationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceSpecification.class);
        ResourceSpecification resourceSpecification1 = new ResourceSpecification();
        resourceSpecification1.setId("id1");
        ResourceSpecification resourceSpecification2 = new ResourceSpecification();
        resourceSpecification2.setId(resourceSpecification1.getId());
        assertThat(resourceSpecification1).isEqualTo(resourceSpecification2);
        resourceSpecification2.setId("id2");
        assertThat(resourceSpecification1).isNotEqualTo(resourceSpecification2);
        resourceSpecification1.setId(null);
        assertThat(resourceSpecification1).isNotEqualTo(resourceSpecification2);
    }
}

package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class ProductSpecificationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductSpecification.class);
        ProductSpecification productSpecification1 = new ProductSpecification();
        productSpecification1.setId("id1");
        ProductSpecification productSpecification2 = new ProductSpecification();
        productSpecification2.setId(productSpecification1.getId());
        assertThat(productSpecification1).isEqualTo(productSpecification2);
        productSpecification2.setId("id2");
        assertThat(productSpecification1).isNotEqualTo(productSpecification2);
        productSpecification1.setId(null);
        assertThat(productSpecification1).isNotEqualTo(productSpecification2);
    }
}

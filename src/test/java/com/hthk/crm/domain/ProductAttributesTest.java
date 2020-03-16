package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class ProductAttributesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductAttributes.class);
        ProductAttributes productAttributes1 = new ProductAttributes();
        productAttributes1.setId("id1");
        ProductAttributes productAttributes2 = new ProductAttributes();
        productAttributes2.setId(productAttributes1.getId());
        assertThat(productAttributes1).isEqualTo(productAttributes2);
        productAttributes2.setId("id2");
        assertThat(productAttributes1).isNotEqualTo(productAttributes2);
        productAttributes1.setId(null);
        assertThat(productAttributes1).isNotEqualTo(productAttributes2);
    }
}

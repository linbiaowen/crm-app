package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class ProductSimAttributesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductSimAttributes.class);
        ProductSimAttributes productSimAttributes1 = new ProductSimAttributes();
        productSimAttributes1.setId("id1");
        ProductSimAttributes productSimAttributes2 = new ProductSimAttributes();
        productSimAttributes2.setId(productSimAttributes1.getId());
        assertThat(productSimAttributes1).isEqualTo(productSimAttributes2);
        productSimAttributes2.setId("id2");
        assertThat(productSimAttributes1).isNotEqualTo(productSimAttributes2);
        productSimAttributes1.setId(null);
        assertThat(productSimAttributes1).isNotEqualTo(productSimAttributes2);
    }
}

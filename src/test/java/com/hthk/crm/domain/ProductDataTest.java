package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class ProductDataTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductData.class);
        ProductData productData1 = new ProductData();
        productData1.setId("id1");
        ProductData productData2 = new ProductData();
        productData2.setId(productData1.getId());
        assertThat(productData1).isEqualTo(productData2);
        productData2.setId("id2");
        assertThat(productData1).isNotEqualTo(productData2);
        productData1.setId(null);
        assertThat(productData1).isNotEqualTo(productData2);
    }
}

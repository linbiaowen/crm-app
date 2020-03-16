package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class ProductBoxTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductBoxType.class);
        ProductBoxType productBoxType1 = new ProductBoxType();
        productBoxType1.setId("id1");
        ProductBoxType productBoxType2 = new ProductBoxType();
        productBoxType2.setId(productBoxType1.getId());
        assertThat(productBoxType1).isEqualTo(productBoxType2);
        productBoxType2.setId("id2");
        assertThat(productBoxType1).isNotEqualTo(productBoxType2);
        productBoxType1.setId(null);
        assertThat(productBoxType1).isNotEqualTo(productBoxType2);
    }
}

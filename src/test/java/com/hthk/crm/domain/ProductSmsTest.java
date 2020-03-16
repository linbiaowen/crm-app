package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class ProductSmsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductSms.class);
        ProductSms productSms1 = new ProductSms();
        productSms1.setId("id1");
        ProductSms productSms2 = new ProductSms();
        productSms2.setId(productSms1.getId());
        assertThat(productSms1).isEqualTo(productSms2);
        productSms2.setId("id2");
        assertThat(productSms1).isNotEqualTo(productSms2);
        productSms1.setId(null);
        assertThat(productSms1).isNotEqualTo(productSms2);
    }
}

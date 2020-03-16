package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class ProductVoiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductVoice.class);
        ProductVoice productVoice1 = new ProductVoice();
        productVoice1.setId("id1");
        ProductVoice productVoice2 = new ProductVoice();
        productVoice2.setId(productVoice1.getId());
        assertThat(productVoice1).isEqualTo(productVoice2);
        productVoice2.setId("id2");
        assertThat(productVoice1).isNotEqualTo(productVoice2);
        productVoice1.setId(null);
        assertThat(productVoice1).isNotEqualTo(productVoice2);
    }
}

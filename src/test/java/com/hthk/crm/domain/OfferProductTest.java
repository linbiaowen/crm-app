package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class OfferProductTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfferProduct.class);
        OfferProduct offerProduct1 = new OfferProduct();
        offerProduct1.setId("id1");
        OfferProduct offerProduct2 = new OfferProduct();
        offerProduct2.setId(offerProduct1.getId());
        assertThat(offerProduct1).isEqualTo(offerProduct2);
        offerProduct2.setId("id2");
        assertThat(offerProduct1).isNotEqualTo(offerProduct2);
        offerProduct1.setId(null);
        assertThat(offerProduct1).isNotEqualTo(offerProduct2);
    }
}

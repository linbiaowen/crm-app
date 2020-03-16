package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class OfferDiscountTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfferDiscount.class);
        OfferDiscount offerDiscount1 = new OfferDiscount();
        offerDiscount1.setId("id1");
        OfferDiscount offerDiscount2 = new OfferDiscount();
        offerDiscount2.setId(offerDiscount1.getId());
        assertThat(offerDiscount1).isEqualTo(offerDiscount2);
        offerDiscount2.setId("id2");
        assertThat(offerDiscount1).isNotEqualTo(offerDiscount2);
        offerDiscount1.setId(null);
        assertThat(offerDiscount1).isNotEqualTo(offerDiscount2);
    }
}

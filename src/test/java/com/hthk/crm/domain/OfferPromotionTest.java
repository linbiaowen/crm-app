package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class OfferPromotionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfferPromotion.class);
        OfferPromotion offerPromotion1 = new OfferPromotion();
        offerPromotion1.setId("id1");
        OfferPromotion offerPromotion2 = new OfferPromotion();
        offerPromotion2.setId(offerPromotion1.getId());
        assertThat(offerPromotion1).isEqualTo(offerPromotion2);
        offerPromotion2.setId("id2");
        assertThat(offerPromotion1).isNotEqualTo(offerPromotion2);
        offerPromotion1.setId(null);
        assertThat(offerPromotion1).isNotEqualTo(offerPromotion2);
    }
}

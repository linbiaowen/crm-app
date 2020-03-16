package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class OfferAdvancePaymentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfferAdvancePayment.class);
        OfferAdvancePayment offerAdvancePayment1 = new OfferAdvancePayment();
        offerAdvancePayment1.setId("id1");
        OfferAdvancePayment offerAdvancePayment2 = new OfferAdvancePayment();
        offerAdvancePayment2.setId(offerAdvancePayment1.getId());
        assertThat(offerAdvancePayment1).isEqualTo(offerAdvancePayment2);
        offerAdvancePayment2.setId("id2");
        assertThat(offerAdvancePayment1).isNotEqualTo(offerAdvancePayment2);
        offerAdvancePayment1.setId(null);
        assertThat(offerAdvancePayment1).isNotEqualTo(offerAdvancePayment2);
    }
}

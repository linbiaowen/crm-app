package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class OfferSpecificationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfferSpecification.class);
        OfferSpecification offerSpecification1 = new OfferSpecification();
        offerSpecification1.setId("id1");
        OfferSpecification offerSpecification2 = new OfferSpecification();
        offerSpecification2.setId(offerSpecification1.getId());
        assertThat(offerSpecification1).isEqualTo(offerSpecification2);
        offerSpecification2.setId("id2");
        assertThat(offerSpecification1).isNotEqualTo(offerSpecification2);
        offerSpecification1.setId(null);
        assertThat(offerSpecification1).isNotEqualTo(offerSpecification2);
    }
}

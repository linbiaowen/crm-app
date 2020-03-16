package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class CommOptoutTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommOptoutType.class);
        CommOptoutType commOptoutType1 = new CommOptoutType();
        commOptoutType1.setId("id1");
        CommOptoutType commOptoutType2 = new CommOptoutType();
        commOptoutType2.setId(commOptoutType1.getId());
        assertThat(commOptoutType1).isEqualTo(commOptoutType2);
        commOptoutType2.setId("id2");
        assertThat(commOptoutType1).isNotEqualTo(commOptoutType2);
        commOptoutType1.setId(null);
        assertThat(commOptoutType1).isNotEqualTo(commOptoutType2);
    }
}

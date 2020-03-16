package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class CommMediaTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommMediaType.class);
        CommMediaType commMediaType1 = new CommMediaType();
        commMediaType1.setId("id1");
        CommMediaType commMediaType2 = new CommMediaType();
        commMediaType2.setId(commMediaType1.getId());
        assertThat(commMediaType1).isEqualTo(commMediaType2);
        commMediaType2.setId("id2");
        assertThat(commMediaType1).isNotEqualTo(commMediaType2);
        commMediaType1.setId(null);
        assertThat(commMediaType1).isNotEqualTo(commMediaType2);
    }
}

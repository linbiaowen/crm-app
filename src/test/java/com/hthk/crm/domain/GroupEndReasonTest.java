package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class GroupEndReasonTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupEndReason.class);
        GroupEndReason groupEndReason1 = new GroupEndReason();
        groupEndReason1.setId("id1");
        GroupEndReason groupEndReason2 = new GroupEndReason();
        groupEndReason2.setId(groupEndReason1.getId());
        assertThat(groupEndReason1).isEqualTo(groupEndReason2);
        groupEndReason2.setId("id2");
        assertThat(groupEndReason1).isNotEqualTo(groupEndReason2);
        groupEndReason1.setId(null);
        assertThat(groupEndReason1).isNotEqualTo(groupEndReason2);
    }
}

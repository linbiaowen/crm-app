package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class GroupMembersTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupMembers.class);
        GroupMembers groupMembers1 = new GroupMembers();
        groupMembers1.setId("id1");
        GroupMembers groupMembers2 = new GroupMembers();
        groupMembers2.setId(groupMembers1.getId());
        assertThat(groupMembers1).isEqualTo(groupMembers2);
        groupMembers2.setId("id2");
        assertThat(groupMembers1).isNotEqualTo(groupMembers2);
        groupMembers1.setId(null);
        assertThat(groupMembers1).isNotEqualTo(groupMembers2);
    }
}

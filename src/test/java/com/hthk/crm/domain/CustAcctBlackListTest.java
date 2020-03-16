package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class CustAcctBlackListTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustAcctBlackList.class);
        CustAcctBlackList custAcctBlackList1 = new CustAcctBlackList();
        custAcctBlackList1.setId("id1");
        CustAcctBlackList custAcctBlackList2 = new CustAcctBlackList();
        custAcctBlackList2.setId(custAcctBlackList1.getId());
        assertThat(custAcctBlackList1).isEqualTo(custAcctBlackList2);
        custAcctBlackList2.setId("id2");
        assertThat(custAcctBlackList1).isNotEqualTo(custAcctBlackList2);
        custAcctBlackList1.setId(null);
        assertThat(custAcctBlackList1).isNotEqualTo(custAcctBlackList2);
    }
}

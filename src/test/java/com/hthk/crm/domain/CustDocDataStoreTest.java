package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class CustDocDataStoreTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustDocDataStore.class);
        CustDocDataStore custDocDataStore1 = new CustDocDataStore();
        custDocDataStore1.setId("id1");
        CustDocDataStore custDocDataStore2 = new CustDocDataStore();
        custDocDataStore2.setId(custDocDataStore1.getId());
        assertThat(custDocDataStore1).isEqualTo(custDocDataStore2);
        custDocDataStore2.setId("id2");
        assertThat(custDocDataStore1).isNotEqualTo(custDocDataStore2);
        custDocDataStore1.setId(null);
        assertThat(custDocDataStore1).isNotEqualTo(custDocDataStore2);
    }
}

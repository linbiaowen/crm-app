package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class SubsPurchaseControlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubsPurchaseControl.class);
        SubsPurchaseControl subsPurchaseControl1 = new SubsPurchaseControl();
        subsPurchaseControl1.setId("id1");
        SubsPurchaseControl subsPurchaseControl2 = new SubsPurchaseControl();
        subsPurchaseControl2.setId(subsPurchaseControl1.getId());
        assertThat(subsPurchaseControl1).isEqualTo(subsPurchaseControl2);
        subsPurchaseControl2.setId("id2");
        assertThat(subsPurchaseControl1).isNotEqualTo(subsPurchaseControl2);
        subsPurchaseControl1.setId(null);
        assertThat(subsPurchaseControl1).isNotEqualTo(subsPurchaseControl2);
    }
}

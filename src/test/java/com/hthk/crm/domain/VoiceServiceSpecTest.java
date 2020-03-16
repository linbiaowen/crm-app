package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class VoiceServiceSpecTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VoiceServiceSpec.class);
        VoiceServiceSpec voiceServiceSpec1 = new VoiceServiceSpec();
        voiceServiceSpec1.setId("id1");
        VoiceServiceSpec voiceServiceSpec2 = new VoiceServiceSpec();
        voiceServiceSpec2.setId(voiceServiceSpec1.getId());
        assertThat(voiceServiceSpec1).isEqualTo(voiceServiceSpec2);
        voiceServiceSpec2.setId("id2");
        assertThat(voiceServiceSpec1).isNotEqualTo(voiceServiceSpec2);
        voiceServiceSpec1.setId(null);
        assertThat(voiceServiceSpec1).isNotEqualTo(voiceServiceSpec2);
    }
}

package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class CertificateEmergencyTextTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertificateEmergencyText.class);
        CertificateEmergencyText certificateEmergencyText1 = new CertificateEmergencyText();
        certificateEmergencyText1.setId(1L);
        CertificateEmergencyText certificateEmergencyText2 = new CertificateEmergencyText();
        certificateEmergencyText2.setId(certificateEmergencyText1.getId());
        assertThat(certificateEmergencyText1).isEqualTo(certificateEmergencyText2);
        certificateEmergencyText2.setId(2L);
        assertThat(certificateEmergencyText1).isNotEqualTo(certificateEmergencyText2);
        certificateEmergencyText1.setId(null);
        assertThat(certificateEmergencyText1).isNotEqualTo(certificateEmergencyText2);
    }
}

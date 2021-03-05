package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EmergencyProcedureTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmergencyProcedure.class);
        EmergencyProcedure emergencyProcedure1 = new EmergencyProcedure();
        emergencyProcedure1.setId(1L);
        EmergencyProcedure emergencyProcedure2 = new EmergencyProcedure();
        emergencyProcedure2.setId(emergencyProcedure1.getId());
        assertThat(emergencyProcedure1).isEqualTo(emergencyProcedure2);
        emergencyProcedure2.setId(2L);
        assertThat(emergencyProcedure1).isNotEqualTo(emergencyProcedure2);
        emergencyProcedure1.setId(null);
        assertThat(emergencyProcedure1).isNotEqualTo(emergencyProcedure2);
    }
}

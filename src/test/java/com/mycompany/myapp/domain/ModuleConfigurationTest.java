package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ModuleConfigurationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModuleConfiguration.class);
        ModuleConfiguration moduleConfiguration1 = new ModuleConfiguration();
        moduleConfiguration1.setId(1L);
        ModuleConfiguration moduleConfiguration2 = new ModuleConfiguration();
        moduleConfiguration2.setId(moduleConfiguration1.getId());
        assertThat(moduleConfiguration1).isEqualTo(moduleConfiguration2);
        moduleConfiguration2.setId(2L);
        assertThat(moduleConfiguration1).isNotEqualTo(moduleConfiguration2);
        moduleConfiguration1.setId(null);
        assertThat(moduleConfiguration1).isNotEqualTo(moduleConfiguration2);
    }
}

package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ObligationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Obligation.class);
        Obligation obligation1 = new Obligation();
        obligation1.setId(1L);
        Obligation obligation2 = new Obligation();
        obligation2.setId(obligation1.getId());
        assertThat(obligation1).isEqualTo(obligation2);
        obligation2.setId(2L);
        assertThat(obligation1).isNotEqualTo(obligation2);
        obligation1.setId(null);
        assertThat(obligation1).isNotEqualTo(obligation2);
    }
}

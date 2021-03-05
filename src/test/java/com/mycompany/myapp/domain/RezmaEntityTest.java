package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class RezmaEntityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RezmaEntity.class);
        RezmaEntity rezmaEntity1 = new RezmaEntity();
        rezmaEntity1.setId(1L);
        RezmaEntity rezmaEntity2 = new RezmaEntity();
        rezmaEntity2.setId(rezmaEntity1.getId());
        assertThat(rezmaEntity1).isEqualTo(rezmaEntity2);
        rezmaEntity2.setId(2L);
        assertThat(rezmaEntity1).isNotEqualTo(rezmaEntity2);
        rezmaEntity1.setId(null);
        assertThat(rezmaEntity1).isNotEqualTo(rezmaEntity2);
    }
}

package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ScheduleLockTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ScheduleLock.class);
        ScheduleLock scheduleLock1 = new ScheduleLock();
        scheduleLock1.setId(1L);
        ScheduleLock scheduleLock2 = new ScheduleLock();
        scheduleLock2.setId(scheduleLock1.getId());
        assertThat(scheduleLock1).isEqualTo(scheduleLock2);
        scheduleLock2.setId(2L);
        assertThat(scheduleLock1).isNotEqualTo(scheduleLock2);
        scheduleLock1.setId(null);
        assertThat(scheduleLock1).isNotEqualTo(scheduleLock2);
    }
}

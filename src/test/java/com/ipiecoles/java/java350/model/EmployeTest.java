package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeTest {
    Employe employe = new Employe();

    @Test
    void testAugmenterSalaire() {
        employe.setSalaire(1000d);
        employe.augmenterSalaire(50);

        Assertions.assertThat(employe.getSalaire()).isEqualTo(1500d);
    }

    @Test
    void testReduireSalaire() {
        employe.setSalaire(1000d);
        employe.augmenterSalaire(-50);

        Assertions.assertThat(employe.getSalaire()).isEqualTo(500d);
    }

    @Test
    void testAugmenterSalaireWhenPourcentageIsNull() {
        employe.setSalaire(1000d);
        employe.augmenterSalaire(0);

        Assertions.assertThat(employe.getSalaire()).isEqualTo(1000d);
    }

    @Test
    void testReduireSalaireWhenPourcentageIsNull() {
        employe.setSalaire(1000d);
        employe.augmenterSalaire(0);

        Assertions.assertThat(employe.getSalaire()).isEqualTo(1000d);
    }

    @Test
    void testAugmenterSalaireWhenSalaireIsNull() {
        employe.setSalaire(null);
        employe.augmenterSalaire(50);

        Assertions.assertThat(employe.getSalaire()).isEqualTo(null);
    }

    @Test
    void testReduireSalaireWhenSalaireIsNull() {
        employe.setSalaire(null);
        employe.augmenterSalaire(50);

        Assertions.assertThat(employe.getSalaire()).isEqualTo(null);
    }

    @Test
    void testAugmenterSalaireWhenSalaireIsNegative() {
        employe.setSalaire(-1000d);
        employe.augmenterSalaire(50);

        Assertions.assertThat(employe.getSalaire()).isEqualTo(null);
    }

    @Test
    void testReduireSalaireWhenSalaireIsNegative() {
        employe.setSalaire(-1000d);
        employe.augmenterSalaire(-50);

        Assertions.assertThat(employe.getSalaire()).isEqualTo(null);
    }
}

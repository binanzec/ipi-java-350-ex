package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EmployeTest {
    Employe employe = new Employe();

    /**
     * Utilisation de test paramétré pour le code smell
     * @param salaire Salaire
     * @param pourcentage Pourcentage
     * @param salaireAttendu Salaire attendu après le calcul de l'augmentation
     */
    @ParameterizedTest
    @CsvSource({
            "1000.0, 50, 1500.0",
            "1000.0, -50, 500.0",
            "1000.0, 0, 1000.0",
            "1000.0, 0, 1000.0"
    })
    void testAugmenterSalaire(Double salaire, Double pourcentage, Double salaireAttendu) {
        employe.setSalaire(salaire);
        employe.augmenterSalaire(pourcentage);

        Assertions.assertThat(employe.getSalaire()).isEqualTo(salaireAttendu);
    }

    @Test
    void testAugmenterSalaireWhenSalaireIsNull() {
        employe.setSalaire(null);
        employe.augmenterSalaire(50);

        Assertions.assertThat(employe.getSalaire()).isNull();
    }

    @Test
    void testReduireSalaireWhenSalaireIsNull() {
        employe.setSalaire(null);
        employe.augmenterSalaire(50);

        Assertions.assertThat(employe.getSalaire()).isNull();
    }

    @Test
    void testAugmenterSalaireWhenSalaireIsNegative() {
        employe.setSalaire(-1000d);
        employe.augmenterSalaire(50);

        Assertions.assertThat(employe.getSalaire()).isNull();
    }

    @Test
    void testReduireSalaireWhenSalaireIsNegative() {
        employe.setSalaire(-1000d);
        employe.augmenterSalaire(-50);

        Assertions.assertThat(employe.getSalaire()).isNull();
    }
}

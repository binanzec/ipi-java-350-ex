package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

class EmployeTest {
    Employe employe = new Employe();

    /**
     * Utilisation de test paramétré pour le code smell
     * @param salaire Salaire
     * @param pourcentage Pourcentage
     * @param salaireAttendu Salaire attendu après le calcul de l'augmentation
     */
    @ParameterizedTest(name = "Avec un salaire de {1} et une augmentation de {2}% le nouveau salaire devrait être de {3}")
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

    @ParameterizedTest(name = "Le {0} et pour un temps de travail de {1} jour(s), le nombre de RTT est de: {2}")
    @CsvSource({
            "2019-01-01, 0.5, 5",
            "2021-01-01, 1.0, 10",
            "2022-01-01, 1.5, 17",
            "2032-01-01, 2.0, 20",
            "2022-01-30, 2.0, 22",
            "2022-01-31, 2.0, 22",
    })
    void testNbRTT(LocalDate dateAnnee, Double tempsPartiel, Integer result) {
        employe.setTempsPartiel(tempsPartiel);
        Integer nbRtt = employe.getNbRtt(dateAnnee);

        Assertions.assertThat(nbRtt).isEqualTo(result);
    }

    @Test
    void testNbRTTWithoutDate() {
        employe.setTempsPartiel(1d);
        Integer nbRtt = employe.getNbRtt();

        Assertions.assertThat(nbRtt).isEqualTo(11);
    }

    @Test
    void testEmployeConstructor() {
        String nom = "Bob";
        String prenom = "John";
        String matricule = "C1234";
        LocalDate dateEmbauche = LocalDate.of(2022,1,1);
        Double salaire = 1500d;
        int performance = 5;
        Double tempsPartiel = 1d;

        Employe employe = new Employe(nom, prenom, matricule, dateEmbauche, salaire, performance, tempsPartiel);

        Assertions.assertThat(employe.getNom()).isEqualTo("Bob");
        Assertions.assertThat(employe.getPrenom()).isEqualTo("John");
        Assertions.assertThat(employe.getMatricule()).isEqualTo("C1234");
        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo("2022-01-01");
        Assertions.assertThat(employe.getSalaire()).isEqualTo(1500d);
        Assertions.assertThat(employe.getPerformance()).isEqualTo(5);
        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(1d);
    }
}

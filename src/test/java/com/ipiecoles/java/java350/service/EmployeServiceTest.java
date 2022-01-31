package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeServiceTest {

    @InjectMocks
    private EmployeService employeService;

    @Mock
    private EmployeRepository employeRepository;

    @ParameterizedTest(name = "Un commercial avec un caTraite de {1} et un caObjectif de {2}, la performance attendu est de {5}")
    @CsvSource({
            "'C', 9000, 10000, 4, 2, 2",
            "'C', 8000, 11000, 4, 5, 1",
            "'C', 14000, 8000, 12, 11, 17",
            "'C', 2000, 6000, -4, -2, 2",
    })

    void testCalculPerformanceCormmercial(String matricule, Long caTraite, Long objectifCa, Integer perf, Double perfMoyenne, Integer perfAttendu) throws EmployeException {
        Employe employe = new Employe();
        employe.setMatricule(matricule);
        employe.setPerformance(perf);

        when(employeRepository.findByMatricule(any(String.class))).thenReturn(employe);
        when(employeRepository.avgPerformanceWhereMatriculeStartsWith("C")).thenReturn(perfMoyenne);
        when(employeRepository.save(any(Employe.class))).thenReturn(employe);

        employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);

        Assertions.assertEquals(employe.getPerformance(), perfAttendu);
    }
}

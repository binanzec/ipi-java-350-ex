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
public class EmployeServiceTest {

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
            "'C',  9000, 10000, 4, 2, 2",
            "'C',  14000, 10000, 40, 38, 45",
            "'C',  1000, 1000, 30, 16, 31",
            "'C',  12000, 10000, 6, 7, 7",
            "'C',  9000, 10000, 4, 16, 2",
            "'C', 1000, 1000, 30, 80, 30",
            "'C', 9000, 10000, 22, 2, 21",
            "'C', 1000, 1000, 30, 30, 30",
            "'C', 12000, 10000, 80, 90, 81",
            "'C', 14000, 10000, 120, 8, 125",
            "'C', 12000, 10000, 80, 8, 82",
            "'C', 14000, 10000, 2, 38, 6",
            "'C', 9000, 10000, -22, 2, 1",
            "'C', 12000, 10000, -16, 9, -15",
            "'C', 9000, 10000, -22, -33, 2",
            "'C', 1000, 1000, -5, 6, 1",
            "'C', 9000, 10000, 22, -18, 21",
            "'C', 1000, 1000, -5, -7, 2",
            "'C', 12000, 10000, -16, -2, -15",
            "'C', 14000, 10000, -1, -6, 4",
            "'C', 14000, 10000, 2, -15, 7",
            "'C', 14000, 10000, -2, 1, 3",
            "'C', 1000, 1000, 5, -9, 6",
            "'C', 12000, 10000, 16, -28, 18",
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

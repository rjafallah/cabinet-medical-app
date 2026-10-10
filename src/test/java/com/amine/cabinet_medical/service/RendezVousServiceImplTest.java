package com.amine.cabinet_medical.service;

import com.amine.cabinet_medical.dao.RendezVousDAO;
import com.amine.cabinet_medical.entite.RendezVous;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RendezVousServiceImplTest {

    @Mock
    private RendezVousDAO rendezVousDAO;

    @InjectMocks
    private RendezVousServiceImpl service;

    @Test
    void findByIdRetourneLeRendezVousSiExistant() {
        RendezVous rdv = new RendezVous(LocalDateTime.now(), "Consultation", "PLANIFIE");
        when(rendezVousDAO.findById(1)).thenReturn(Optional.of(rdv));

        assertSame(rdv, service.findById(1));
    }

    @Test
    void findByIdRetourneNullSiAbsent() {
        when(rendezVousDAO.findById(99)).thenReturn(Optional.empty());

        assertNull(service.findById(99));
    }

    @Test
    void findByPatientIdDelegueAuDao() {
        List<RendezVous> liste = List.of(new RendezVous(LocalDateTime.now(), "Suivi", "PLANIFIE"));
        when(rendezVousDAO.findByPatientId(4)).thenReturn(liste);

        assertEquals(1, service.findByPatientId(4).size());
    }

    @Test
    void saveDelegueAuDao() {
        RendezVous rdv = new RendezVous(LocalDateTime.now(), "Consultation", "PLANIFIE");

        service.save(rdv);

        verify(rendezVousDAO).save(rdv);
    }
}

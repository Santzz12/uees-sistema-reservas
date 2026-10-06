package edu.uees.reservas.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.uees.reservas.domain.Reserva;
import edu.uees.reservas.repository.ReservaRepository;

class ReservaServiceTest {

    private final ReservaRepository repository = mock(ReservaRepository.class);
    private final ReservaService service = new ReservaService(repository);

    @Test
    void dosHorasPermitenCancelar() {
        assertTrue(service.puedeCancelar(2));
    }

    @Test
    void unaHoraNoPermiteCancelar() {
        assertFalse(service.puedeCancelar(1));
    }

    @Test
    void crearReservaExitosaConEstadoPendiente() {
        when(repository.guardar(any(Reserva.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Reserva reserva = service.crear("R-100", "NORMAL");

        assertNotNull(reserva);
        assertEquals("R-100", reserva.getId());
        assertEquals("NORMAL", reserva.getTipo());
        verify(repository).guardar(any(Reserva.class));
    }

    @Test
    void buscarReservaExistenteRetornaReserva() {
        Reserva reservaSimulada = new Reserva("R-200", "VIP");
        when(repository.buscarPorId("R-200")).thenReturn(Optional.of(reservaSimulada));

        Reserva encontrada = service.buscar("R-200");

        assertNotNull(encontrada);
        assertEquals("R-200", encontrada.getId());
        verify(repository).buscarPorId("R-200");
    }

    @Test
    void buscarReservaInexistenteLanzaExcepcion() {
        when(repository.buscarPorId("ID-NO-EXISTE")).thenReturn(Optional.empty());

        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> service.buscar("ID-NO-EXISTE")
        );

        assertEquals("Reserva no encontrada", excepcion.getMessage());
    }
}
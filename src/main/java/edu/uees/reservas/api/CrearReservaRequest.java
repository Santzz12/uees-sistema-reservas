package edu.uees.reservas.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearReservaRequest(
    @NotBlank(message = "El id no puede estar vacío")
    String id,

    @NotNull(message = "El tipo no puede ser nulo")
    String tipo
) {}
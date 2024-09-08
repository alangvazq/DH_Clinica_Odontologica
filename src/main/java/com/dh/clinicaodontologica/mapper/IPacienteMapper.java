package com.dh.clinicaodontologica.mapper;

import com.dh.clinicaodontologica.modelo.Paciente;
import com.dh.clinicaodontologica.modelo.dto.PacienteDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface IPacienteMapper {
    Paciente dtoAPaciente(PacienteDto dto);
    PacienteDto pacienteADto(Paciente paciente);

    // Si los valores son nulos en el DTO, mantiene los datos originales del paciente
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void actualizarPacienteDesdeDto(@MappingTarget Paciente paciente, PacienteDto dto);
}

package com.dh.clinicaodontologica.mapper;

import com.dh.clinicaodontologica.modelo.Turno;
import com.dh.clinicaodontologica.modelo.dto.TurnoDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {IOdontologoMapper.class, IPacienteMapper.class})
public interface ITurnoMapper {
    Turno dtoATurno(TurnoDto dto);
    TurnoDto turnoADto(Turno turno);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void actualizarTurnoDesdeDto(@MappingTarget Turno turno, TurnoDto dto);
}

package com.dh.clinicaodontologica.mapper;

import com.dh.clinicaodontologica.modelo.Odontologo;
import com.dh.clinicaodontologica.modelo.dto.OdontologoDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface IOdontologoMapper {
    Odontologo dtoAOdontologo(OdontologoDto dto);
    OdontologoDto odontologoADto(Odontologo odontologo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void actualizarOdontologoDesdeDto(@MappingTarget Odontologo odontologo, OdontologoDto dto);
}

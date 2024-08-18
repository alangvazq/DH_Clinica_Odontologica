package com.dh.clinicaodontologica.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T, N> {
    List<T> listar() throws Exception;
    Optional<T> buscar(N id) throws Exception;
    T agregar(T t) throws Exception;
    T modificar(T t) throws Exception;
    void eliminar(N id) throws Exception;
}

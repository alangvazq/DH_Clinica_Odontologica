package com.dh.clinicaodontologica.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T, N> {
    List<T> listar();
    Optional<T> buscar(N id);
    T agregar(T t);
    T modificar(T t);
    void eliminar(N id);
}

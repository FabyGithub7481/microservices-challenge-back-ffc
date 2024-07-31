package com.client_person_service.services;

import com.client_person_service.entities.Cliente;
import com.client_person_service.excepciones.ClienteNotFoundException;
import com.client_person_service.excepciones.ClienteYaExisteException;
import java.util.List;
import java.util.Optional;

public interface IClienteService {

  Cliente createCliente(Cliente cliente) throws ClienteYaExisteException;

  List<Cliente> getAllClientes();

  Optional<Cliente> getClienteById(Long id) throws ClienteNotFoundException;

  Cliente updateCliente(Long id, Cliente cliente) throws ClienteNotFoundException;

  void deleteCliente(Long id) throws ClienteNotFoundException;

  Long obtenerClienteIdPorIdentificacion(String identificacion) throws ClienteNotFoundException;
}

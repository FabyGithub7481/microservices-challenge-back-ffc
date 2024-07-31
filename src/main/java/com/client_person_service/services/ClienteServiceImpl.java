package com.client_person_service.services;


import com.client_person_service.entities.Cliente;
import com.client_person_service.excepciones.ClienteNotFoundException;
import com.client_person_service.excepciones.ClienteYaExisteException;
import com.client_person_service.repository.IClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements IClienteService {

  @Autowired
  private IClienteRepository clienteRepository;

  @Override
  @Transactional
  public Cliente createCliente(Cliente cliente) {
    // Verificar si el cliente ya existe por identificación
    if (clienteRepository.existsByIdentificacion(cliente.getIdentificacion())) {
      throw new ClienteYaExisteException("Cliente con la identificación " + cliente.getIdentificacion() + " ya existe.");
    }
    return clienteRepository.save(cliente);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Cliente> getAllClientes() {
    return clienteRepository.findAll();
  }
  @Override
  @Transactional(readOnly = true)
  public Optional<Cliente> getClienteById(Long id) {
    return clienteRepository.findById(id);
  }

  @Override
  @Transactional
  public Cliente updateCliente(Long id, Cliente cliente) {
    if (clienteRepository.existsById(id)) {
      cliente.setClienteId(id);
      return clienteRepository.save(cliente);
    }
    return null;
  }

  @Override
  @Transactional
  public void deleteCliente(Long id) {
    clienteRepository.softDelete(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Long obtenerClienteIdPorIdentificacion(String identificacion) {
    try {
      if (identificacion == null || identificacion.isEmpty()) {
        throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
      }

      Long clienteId = clienteRepository.findClienteIdByIdentificacion(identificacion);

      if (clienteId == null) {
        throw new ClienteNotFoundException(
            "Cliente no encontrado con la identificación: " + identificacion);
      }

      return clienteId;
    } catch (ClienteNotFoundException e) {
      throw e;
    } catch (IllegalArgumentException e) {
      throw e;
    } catch (Exception e) {
      throw new RuntimeException("Error interno del servidor: " + e.getMessage(), e);
    }
  }
}

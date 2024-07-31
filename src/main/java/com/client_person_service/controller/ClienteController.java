package com.client_person_service.controller;


import com.client_person_service.entities.Cliente;
import com.client_person_service.excepciones.ClienteNotFoundException;
import com.client_person_service.excepciones.ClienteYaExisteException;
import com.client_person_service.services.ClienteServiceImpl;
import com.client_person_service.services.IClienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

  @Autowired
  private IClienteService clienteService;

  @PostMapping
  public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
    try {
      Cliente nuevoCliente = clienteService.createCliente(cliente);
      return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    } catch (ClienteYaExisteException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el cliente.");
    }
  }

  @GetMapping
  public ResponseEntity<List<Cliente>> getAllClientes() {
    List<Cliente> clientes = clienteService.getAllClientes();
    return new ResponseEntity<>(clientes, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
    Optional<Cliente> cliente = clienteService.getClienteById(id);
    return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cliente> updateCliente(@PathVariable Long id,
      @RequestBody Cliente cliente) {
    Cliente updatedCliente = clienteService.updateCliente(id, cliente);
    return updatedCliente != null ? ResponseEntity.ok(updatedCliente)
        : ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
    clienteService.deleteCliente(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/cuenta/{identificacion}")
  public ResponseEntity<Object> getClientePorIdentificacion(@PathVariable String identificacion) {
    try {
      Long clienteId = clienteService.obtenerClienteIdPorIdentificacion(identificacion);
      return new ResponseEntity<>(clienteId, HttpStatus.OK);
    } catch (ClienteNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>("Error interno del servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}


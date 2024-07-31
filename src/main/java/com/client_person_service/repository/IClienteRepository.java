package com.client_person_service.repository;

import com.client_person_service.entities.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {

  @Modifying
  @Transactional
  @Query("UPDATE Cliente c SET c.estado = false WHERE c.clienteId = :clienteId")
  void softDelete(@Param("clienteId") Long clienteId);

  @Query("SELECT c.clienteId FROM Cliente c WHERE c.identificacion = :identificacion")
  Long findClienteIdByIdentificacion(@Param("identificacion") String identificacion);
  boolean existsByIdentificacion(String identificacion);
}

package com.client_person_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Personas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "nombre", nullable = false, length = 255)
  private String nombre;

  @Column(name = "genero", nullable = false, length = 20)
  private String genero;

  @Column(name = "edad", nullable = false)
  private Integer edad;

  @Column(name = "identificacion", unique = true, nullable = false, length = 50)
  private String identificacion;

  @Column(name = "direccion", length = 255)
  private String direccion;

  @Column(name = "telefono", length = 15)
  private String telefono;


}

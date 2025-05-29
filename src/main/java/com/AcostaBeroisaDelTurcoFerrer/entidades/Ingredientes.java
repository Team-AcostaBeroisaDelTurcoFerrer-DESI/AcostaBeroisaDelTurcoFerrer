package com.AcostaBeroisaDelTurcoFerrer.entidades;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

//Clase base
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_ingrediente", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("INGREDIENTE_BASE") // Opcional: valor para la clase base si se pueden instanciar
public class Ingredientes {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY suele ser una buena opción para SINGLE_TABLE
 private Long id;

 private String nombre;
 private int calorias;

//Relación OneToMany con la entidad de unión ItemsReceta
 // mappedBy indica que el lado propietario de la relación es 'ingrediente' en ItemsReceta
 @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL, orphanRemoval = true)
 private Set<ItemsReceta> itemsRecetas = new HashSet<>();
 
 
 // Constructores, getters y setters
 public Ingredientes() {}

 public Ingredientes(String nombre, int calorias) {
     this.nombre = nombre;
     this.calorias = calorias;
 }

 // Getters y Setters
 public Long getId() {
     return id;
 }

 public void setId(Long id) {
     this.id = id;
 }

 public String getNombre() {
     return nombre;
 }

 public void setNombre(String nombre) {
     this.nombre = nombre;
 }

 public int getCalorias() {
     return calorias;
 }

 public void setCalorias(int calorias) {
     this.calorias = calorias;
 }
 public Set<ItemsReceta> getItemsRecetas() {
     return itemsRecetas;
 }

 public void setItemsRecetas(Set<ItemsReceta> itemsRecetas) {
     this.itemsRecetas = itemsRecetas;
 }
}

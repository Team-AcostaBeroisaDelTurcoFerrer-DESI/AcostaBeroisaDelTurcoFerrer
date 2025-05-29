package com.AcostaBeroisaDelTurcoFerrer.entidades;
import jakarta.persistence.*; 
import java.time.LocalDate;

@Entity // Marca esta clase como una entidad JPA

public class Preparacion {
	
	@Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generación de IDs
	private Long Id;
	
	// Relación ManyToOne: Muchas Preparaciones pueden pertenecer a una Receta
    // 'optional = true' indica que la relación es opcional (0 o 1 receta)
    // '@JoinColumn' especifica la columna de clave foránea en la tabla Preparacion
    @ManyToOne(fetch = FetchType.LAZY) // Usar LAZY para evitar carga innecesaria
    @JoinColumn(name = "receta_id", nullable = true) // 'nullable = true' permite que no tenga Receta
	private Receta receta;
    
    
	// Relación OneToOne: Una Preparacion tiene una EntregaAsistencia (o ninguna)
    // mappedBy indica que el lado propietario de la relación es 'preparacion' en EntregaAsistencia
    // Optional. Esto significa que una Preparacion puede existir sin una EntregaAsistencia.
    // CascadeType.ALL para propagar operaciones (ej. si borro la preparacion, borro la entrega)
    @OneToOne(mappedBy = "preparacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private EntregaAsistencia entregaAsistencia;
	
    
	
	private LocalDate fechaCoccion;
	private int totalRacionesPreparadas;
	private int stockRacionesRestantes;
	
	public Preparacion() {	
	}
	
	public Preparacion(Long id, LocalDate fechaCoccion, int totalRacionesPreparadas, int stockRacionesRestantes) {
		super();
		Id = id;
		this.fechaCoccion = fechaCoccion;
		this.totalRacionesPreparadas = totalRacionesPreparadas;
		this.stockRacionesRestantes = stockRacionesRestantes;
	}
	
	
	// Método de conveniencia para establecer la relación bidireccional
    public void setEntregaAsistencia(EntregaAsistencia entregaAsistencia) {
        if (entregaAsistencia != null) {
            entregaAsistencia.setPreparacion(this);
        }
        this.entregaAsistencia = entregaAsistencia;
    }
    
    public EntregaAsistencia getEntregaAsistencia() {
        return entregaAsistencia;
    }
    
	 public Long getId() {
	        return Id;
	    }

	    public void setId(Long id) {
	        this.Id = id;
	    }
	
	public LocalDate getFechaCoccion() {
		return fechaCoccion;
	}
	public void setFechaCoccion(LocalDate fechaCoccion) {
		this.fechaCoccion = fechaCoccion;
	}
	public int getTotalRacionesPreparadas() {
		return totalRacionesPreparadas;
	}
	public void setTotalRacionesPreparadas(int totalRacionesPreparadas) {
		this.totalRacionesPreparadas = totalRacionesPreparadas;
	}
	public int getStockRacionesRestantes() {
		return stockRacionesRestantes;
	}
	public void setStockRacionesRestantes(int stockRacionesRestantes) {
		this.stockRacionesRestantes = stockRacionesRestantes;
	}
	public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }
	
}

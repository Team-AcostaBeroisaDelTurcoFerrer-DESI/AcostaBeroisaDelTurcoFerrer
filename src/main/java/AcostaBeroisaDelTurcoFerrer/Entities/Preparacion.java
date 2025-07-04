package AcostaBeroisaDelTurcoFerrer.Entities;
import jakarta.persistence.*; 
import java.time.LocalDate;

@Entity

public class Preparacion {
	
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long Id;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "receta_id", nullable = true) 
	private Receta receta;
    
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

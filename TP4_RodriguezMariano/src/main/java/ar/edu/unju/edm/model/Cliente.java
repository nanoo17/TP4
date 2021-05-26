package ar.edu.unju.edm.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Table (name="CLIENTES")
@Component
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idCliente;
	@Column
	@Min(1000)
	private int numeroDocumento;
	@Column
	private int codigoAreaTelefono;
	@Column
	private int numeroTelefono;
	@Column
	private String tipoDeDocumento;
	@Column
	@NotBlank(message = "Debe incluir nombre y apellido")
	private String nombreApellido;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaUltimaCompra;

	public Cliente() {
	}

	public Cliente(String tipoDeDocumento, String nombreApellido, String email, String password, int numeroDocumento,
			int codigoAreaTelefono, int numeroTelefono, LocalDate fechaNacimiento, LocalDate fechaUltimaCompra) {
		this.tipoDeDocumento = tipoDeDocumento;
		this.nombreApellido = nombreApellido;
		this.email = email;
		this.password = password;
		this.numeroDocumento = numeroDocumento;
		this.codigoAreaTelefono = codigoAreaTelefono;
		this.numeroTelefono = numeroTelefono;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaUltimaCompra = fechaUltimaCompra;
	}

	public String getTipoDeDocumento() {
		return tipoDeDocumento;
	}

	public void setTipoDeDocumento(String tipoDeDocumento) {
		this.tipoDeDocumento = tipoDeDocumento;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public int getEdad() {
		LocalDate fechaActual = LocalDate.now();
		Period periodo = Period.between(fechaNacimiento, fechaActual);
		return periodo.getYears();
	}

	public int getCodigoAreaTelefono() {
		return codigoAreaTelefono;
	}

	public void setCodigoAreaTelefono(int codigoAreaTelefono) {
		this.codigoAreaTelefono = codigoAreaTelefono;
	}

	public int getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(int numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDate getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getDatosAdicionales() {
		LocalDate fechaN = this.fechaNacimiento;
		LocalDate fechaU = this.fechaUltimaCompra;
		LocalDate fechaActual = LocalDate.now();

		String datos = "FUC: ";

		Period periodo = Period.between(fechaU, fechaActual);
		datos += (periodo.getYears() + "-" + periodo.getMonths() + "-" + periodo.getDays());

		datos += "\nFC: ";

		LocalDate cumple;
		int dia = fechaN.getDayOfMonth() - 1, mes = fechaN.getMonthValue(), an = fechaN.getYear() + this.getEdad() + 1;
		if (dia == 0) {
			mes -= 1;
			dia = fechaN.lengthOfMonth() - 1;
		}
		cumple = LocalDate.of(an, mes, dia);

		if (fechaActual.getMonthValue() >= cumple.getMonthValue()) {
			periodo = Period.between(fechaActual, cumple);
		} else {
			periodo = Period.between(cumple, fechaActual);
		}

		Calendar hoy = Calendar.getInstance();
		int horas = hoy.get(Calendar.HOUR_OF_DAY);
		int minutos = hoy.get(Calendar.MINUTE);
		int segundos = hoy.get(Calendar.SECOND);

		horas = 24 - horas - 1;
		minutos = 60 - minutos - 1;
		segundos = 60 - segundos;

		datos += (periodo.getYears() + "-" + periodo.getMonths() + "-" + periodo.getDays() + " " + horas + ":" + minutos
				+ ":" + segundos);

		return datos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoAreaTelefono;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((fechaUltimaCompra == null) ? 0 : fechaUltimaCompra.hashCode());
		result = prime * result + ((nombreApellido == null) ? 0 : nombreApellido.hashCode());
		result = prime * result + numeroDocumento;
		result = prime * result + numeroTelefono;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((tipoDeDocumento == null) ? 0 : tipoDeDocumento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigoAreaTelefono != other.codigoAreaTelefono)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (fechaUltimaCompra == null) {
			if (other.fechaUltimaCompra != null)
				return false;
		} else if (!fechaUltimaCompra.equals(other.fechaUltimaCompra))
			return false;
		if (nombreApellido == null) {
			if (other.nombreApellido != null)
				return false;
		} else if (!nombreApellido.equals(other.nombreApellido))
			return false;
		if (numeroDocumento != other.numeroDocumento)
			return false;
		if (numeroTelefono != other.numeroTelefono)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (tipoDeDocumento == null) {
			if (other.tipoDeDocumento != null)
				return false;
		} else if (!tipoDeDocumento.equals(other.tipoDeDocumento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [tipoDeDocumento=" + tipoDeDocumento + ", nombreApellido=" + nombreApellido + ", email=" + email
				+ ", password=" + password + ", numeroDocumento=" + numeroDocumento + ", codigoAreaTelefono="
				+ codigoAreaTelefono + ", numeroTelefono=" + numeroTelefono + ", fechaNacimiento=" + fechaNacimiento
				+ ", fechaUltimaCompra=" + fechaUltimaCompra + "]";
	}
}

package br.com.ajax.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@NotEmpty
	@Column(name = "nome", length = 80, nullable = false)
	private String nome;

	@NotEmpty
	@Column(name = "matricula", length = 15, nullable = false)
	private String matricula;

	@NotEmpty
	@Email
	@Column(name = "email", length = 80, nullable = false)
	private String email;

	@NotEmpty
	@Column(name = "telefone", length = 15, nullable = false)
	private String telefone;

	@NotEmpty
	@Column(name = "nascimento", length = 10, nullable = false)
	private String dataNascimento;

	@NotEmpty
	@Column(name = "responsavel", length = 80, nullable = false)
	private String respnsavel;

	@NotEmpty
	@Column(name = "rua", length = 80, nullable = false)
	private String rua;

	@NotEmpty
	@Column(name = "numero", length = 10, nullable = false)
	private String numero;

	@Column(name = "complemto", length = 80, nullable = false)
	private String complemento;

	@NotEmpty
	@Column(name = "cep", length = 15, nullable = false)
	private String cep;

	@NotEmpty
	@Column(name = "cidade", length = 40, nullable = false)
	private String cidade;

	@NotEmpty
	@Column(name = "uf", length = 2, nullable = false)
	private String uf;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "curso_id", nullable = false)
	private Curso curso;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sala_id", nullable = false)
	private Sala sala;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRespnsavel() {
		return respnsavel;
	}

	public void setRespnsavel(String respnsavel) {
		this.respnsavel = respnsavel;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

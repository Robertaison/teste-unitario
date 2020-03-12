package br.com.caelum.leilao.dominio;


import br.com.caelum.leilao.exception.ValorInvalidoParaLanceException;

public class Lance {

	private Usuario usuario;
	private Double valor;

	public Lance(Usuario usuario, Double valor) {
		this.usuario = usuario;
		setValor(valor);
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		if(valor<=0){
			throw new ValorInvalidoParaLanceException();
		}
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Lance{" +
				"usuario=" + usuario +
				", valor=" + valor +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Lance lance = (Lance) o;

		if (getUsuario() != null ? !getUsuario().equals(lance.getUsuario()) : lance.getUsuario() != null) return false;
		return getValor() != null ? getValor().equals(lance.getValor()) : lance.getValor() == null;
	}

	@Override
	public int hashCode() {
		int result = getUsuario() != null ? getUsuario().hashCode() : 0;
		result = 31 * result + (getValor() != null ? getValor().hashCode() : 0);
		return result;
	}
}

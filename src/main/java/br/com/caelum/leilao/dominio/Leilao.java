package br.com.caelum.leilao.dominio;

import java.util.*;
import java.util.stream.Collectors;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<>();
	}

	public void propoe(Lance lance) {
		if(lances.isEmpty() || podeDarLance(lance.getUsuario())) {
			lances.add(lance);
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	private Lance ultimoLanceDado() {
		return lances.get(lances.size()-1);
	}

	private boolean podeDarLance(Usuario usuario) {
		return !ultimoLanceDado().getUsuario().equals(usuario)
				&& qtdDeLancesDo(usuario) < 5;
	}

	private int qtdDeLancesDo(Usuario usuario) {
		int total = 0;
		for(Lance l : lances) {
			if(l.getUsuario().equals(usuario)) total++;
		}
		return total;
	}

	public Double dobraLance(Usuario usuario) {
		Double maiorLanceDoUsuario = this.getLances().stream()
				.filter(lanceFilter -> lanceFilter.getUsuario().equals(usuario))
				.max(Comparator.comparingDouble(Lance::getValor))
				.map(Lance::getValor)
				.orElse(0.0);

		return maiorLanceDoUsuario*2;
	}

	@Override
	public String toString() {
		return "Leilao{" +
				"descricao='" + descricao + '\'' +
				", lances=" + lances +
				'}';
	}
}

package br.edu.unoesc.funcionarios.service.impl;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unoesc.funcionarios.model.Funcionario;
import br.edu.unoesc.funcionarios.repository.FuncionarioRepository;
import br.edu.unoesc.funcionarios.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	
	@Autowired
	private FuncionarioRepository repositorio;
	
	@Override
	public void popularTabelaInicial() {
		repositorio.saveAll(List.of(
				new Funcionario(1L, "Alex", "Rua A.", 0, new BigDecimal("123.45"), LocalDate.of(2003, 9, 07)),
				new Funcionario(2L, "Fulano", "Rua F.", 1, new BigDecimal("678.90"), LocalDate.of(2001, 12, 28)),
				new Funcionario(3L, "Beltrano", "Rua B.", 2, new BigDecimal("1011.12"), LocalDate.of(1975, 6, 6))
			)
		);
	}

	@Override
	public Funcionario incluir(Funcionario funcionario) {
		funcionario.setId(null);
		
		return repositorio.save(funcionario);
	}

	@Override
	public Funcionario alterar(Long id, Funcionario funcionario) {
		var l = repositorio.findById(id)
						   .orElseThrow(
								   () -> new ObjectNotFoundException("Funcionário não encontrado! Id: "
										   + id + ", Tipo: " + Funcionario.class.getName(), null)
						   );
		
		// Atualiza os dados do banco com os dados vindos da requisição
		l.setNome(funcionario.getNome());
		l.setEndereco(funcionario.getEndereco());
		l.setNumDep(funcionario.getNumDep());
		l.setSalario(funcionario.getSalario());
		l.setNascimento(funcionario.getNascimento());

		
		return repositorio.save(l);
	}

	@Override
	public void excluir(Long id) {
		if (repositorio.existsById(id)) {
			repositorio.deleteById(id);
		} else {
			throw new ObjectNotFoundException("Funcionário não encontrado! Id: "
					   						  + id + ", Tipo: " + Funcionario.class.getName(), null);
		}
	}

	@Override
	public List<Funcionario> listar() {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		// Recupera todos os registros da tabela
		Iterable<Funcionario> itens = repositorio.findAll();
		
		// Cria uma cópia dos dados na lista 'funcionarios'
		itens.forEach(funcionarios::add);
		
		return funcionarios;
	}

	@Override
	public Funcionario buscar(Long id) {
		Optional<Funcionario> obj = repositorio.findById(id);
				
		return obj.orElseThrow(
						() -> new ObjectNotFoundException("Funcionário não encontrado! Id: "
					  		+ id + ", Tipo: " + Funcionario.class.getName(), null)
					);
	}

	@Override
	public List<Funcionario> buscarPorNome(String nome) {
		return repositorio.findByNomeContainingIgnoreCase(nome);
	}

	@Override
	public Optional<Funcionario> porId(Long id) {
		return repositorio.findById(id);
	}
}
package br.com.sistemaweb;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

import br.com.sistemaweb.persistence.CategoriaRepository;
import br.com.sistemaweb.persistence.FornecedorRepository;
import br.com.sistemaweb.persistence.MovimentacaoEstqueRepository;
import br.com.sistemaweb.persistence.MultiSessionFactoryFIlter;
import br.com.sistemaweb.persistence.PessoaRepository;
import br.com.sistemaweb.persistence.ProdutoRepository;
import br.com.sistemaweb.persistence.ProfissaoRepository;

public class MultiHibernateSessionFilter extends MultiSessionFactoryFIlter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sistemaweb.persistence.AbstractHibernateSessionFilter#buildContainer
	 * ()
	 */
	@Override
	public MutablePicoContainer buildContainer() {
		MutablePicoContainer container = new DefaultPicoContainer();
		container.addComponent(PessoaRepository.class);
		container.addComponent(ProfissaoRepository.class);
		container.addComponent(CategoriaRepository.class);
		container.addComponent(FornecedorRepository.class);
		container.addComponent(ProdutoRepository.class);
		container.addComponent(MovimentacaoEstqueRepository.class);
		return container;
	}
}

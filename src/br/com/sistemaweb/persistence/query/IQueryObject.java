/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 13/12/2008
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.persistence.query;

import java.util.List;

import org.hibernate.Session;

/**
 * @author Diego Lins de freitas
 *
 *	Interface responsavel por definir o comportamento de uma queryobject
 *	Uma query object eh um objeto que representa uma consulta ao banco 
 *	nela eh definida a query de consulta e seus parametros.
 */
public interface IQueryObject<T> {
	
	public List<T> list();
	
	public void setSession(Session session);

	public Session getSession();
	
	public Class<T> getClazz();
	
	public void setClazz(Class<T> clazz);
	
	public void setSpecification(Object s);
}

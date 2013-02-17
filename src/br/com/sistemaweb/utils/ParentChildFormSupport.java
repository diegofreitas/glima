/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 11/02/2009 23:19:04
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.utils;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Diego Lins de Freitas
 *
 */
public class ParentChildFormSupport<T> {
	
	private Class<T> childClass;
	
	private Collection<T> children ;
	
	/**
	 * @param class1
	 */
	public ParentChildFormSupport(Class<T> clazz) {
		childClass=clazz;
	}

	public void addChild(ActionEvent e){
		try {
			children.add(childClass.newInstance());
			//pegar repositorio.
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void removeChild(ActionEvent e){
		T bean= (T) Utils.getUIParameter("bean", e);
		children.remove(bean);
	}

	public void setChildClass(Class<T> childClass) {
		this.childClass = childClass;
	}

	public Class<T> getChildClass() {
		return childClass;
	}

	public void setChildren(Collection<T> children) {
		this.children = children;
	}

	public Collection<T> getChildren() {
		return children;
	}
}


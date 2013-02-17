/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 19/12/2008
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.utils;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectOne;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.sistemaweb.IDomainObject;
import br.com.sistemaweb.persistence.query.CriteriaQueryObject;

/**
 * @author Diego Lins de Freitas
 */
public class DomainObjectConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent ui, String str) {
		return this.getAttributesFrom(ui).get(str);
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent ui, Object obj) {
		if (obj != null ) {
			this.addAttribute(ui, obj);
			return obj.toString();
		}
		return null;
	}

	protected void addAttribute(UIComponent component, Object o) {
		if(!this.getAttributesFrom(component).containsKey(o.toString()))
			this.getAttributesFrom(component).put(o.toString(), o);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}

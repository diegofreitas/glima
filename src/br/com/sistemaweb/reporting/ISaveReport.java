/**
 * Este artefato de software foi desenvolvido por mim (root) em 21/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.reporting;

import java.io.IOException;

/**
 * @author root
 *
 */
public interface ISaveReport {

	public void save(Report report) throws IOException;
}

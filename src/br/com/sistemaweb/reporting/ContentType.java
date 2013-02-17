/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 18/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.reporting;

/**
 * @author Diego Lins de Freitas
 *
 */
public enum ContentType {

	XLS {
		@Override
		public String getContentType() {
			return "application/xls";
		}

		@Override
		public String getExtension() {
			return ".xls";
		}
	},
	PDF {
		@Override
		public String getContentType() {
			return "application/pdf";
		}

		@Override
		public String getExtension() {
			return ".pdf";
		}
	},
	RTF {
		@Override
		public String getContentType() {
			return "application/rtf";
		}

		@Override
		public String getExtension() {
			return ".rtf";
		}
	}, 
	SWF{
		@Override
		public String getContentType() {
			return "application/swf";
		}

		@Override
		public String getExtension() {
			return ".swf";
		}
	};
	
	public abstract String getContentType();
	
	public abstract String getExtension();
}

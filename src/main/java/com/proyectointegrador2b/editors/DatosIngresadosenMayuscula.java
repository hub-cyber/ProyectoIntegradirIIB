package com.proyectointegrador2b.editors;

import java.beans.PropertyEditorSupport;

public class DatosIngresadosenMayuscula extends PropertyEditorSupport{

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(text.toUpperCase().trim());
	}

}

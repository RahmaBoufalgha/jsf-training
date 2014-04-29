package edu.esprit.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("pc")
public class PriceConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Float equivalentPrice = null;
		if (!value.trim().equals("")) {
			Float.valueOf(value);
		}
		return equivalentPrice;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String equivalentString = null;
		if (value == null || value.equals("")) {
			equivalentString = "";
		} else {
			equivalentString = String.format("%1$.3f TND", (Float) value);
		}
		return equivalentString;
	}

}

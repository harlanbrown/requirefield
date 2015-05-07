/**
 * 
 */

package org.nuxeo.sample;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@Name("myBean")
@Scope(ScopeType.CONVERSATION)
public class RequireFieldBean implements Serializable {

	private static final long serialVersionUID = -5804021424968567090L;
	
    private static final Log log = LogFactory.getLog(RequireFieldBean.class);
	
	public Object retrieveInputComponentValue(UIComponent anchor, String componentId) {
	    Map attributes = anchor.getAttributes();
	    String inputId = (String) attributes.get(componentId);
	    UIInput component = (UIInput) anchor.findComponent(inputId);
	    return component.getLocalValue();
	  	}
	
	public void requireField(FacesContext context, UIComponent component, Object value) {
	    Object requireFieldFlag = retrieveInputComponentValue(component, "requireField");
	    Object requiredField = retrieveInputComponentValue(component, "requiredField");
	    Boolean b = (Boolean) requireFieldFlag;
	    String s = (String) requiredField;
	    if ( (b) && (s.length()<1) ) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field is required", null);
	        throw new ValidatorException(message);
	    }
	}

	public void requireField2(FacesContext context, UIComponent component, Object value) {
	    Object requireFieldFlag = retrieveInputComponentValue(component, "requireField2");
	    Object requiredField = retrieveInputComponentValue(component, "requiredField2");
	    String s1 = (String) requireFieldFlag;
	    String s2 = (String) requiredField;
	    if ( (s1).equals("yes") && (s2.length()<1) ) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field is required", null);
	        throw new ValidatorException(message);
	    }
	}
	
	public boolean accept() {
        return true;
    }
	
}

package helpers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Faces {
	public static void addMessageErro(String mensagem){
		FacesMessage msg = new FacesMessage(mensagem);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage("", msg);
	}
}

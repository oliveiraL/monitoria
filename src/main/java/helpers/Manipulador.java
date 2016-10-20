package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.faces.context.FacesContext;

public class Manipulador {
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		String aplicationPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
		FileInputStream file = new FileInputStream(
				 aplicationPath + File.separator +"properties" + File.separator + "sistema.properties");
		props.load(file);
		return props;
	}
	

	public String getRelativePath() throws IOException {
		return getProp().getProperty("prop.relativePath");
	}
}

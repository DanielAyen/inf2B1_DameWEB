package Logik;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class DatenzugriffXML implements iDatenzugriff, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1632008787864445195L;
	FileWriter fw = null;

	@Override
	public void speichern(Object o, String pfad) throws IOException {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(SpielBean.class);
			Marshaller m = jaxbContext.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(o, System.out);
			fw = new FileWriter(pfad);
			m.marshal(o, fw);

		} catch (JAXBException e) {
			e.printStackTrace();
			System.err.println(e);
		}
		finally{
			try{
				fw.close();
			}catch(Exception e){
				System.err.println(e);
			}
		}
	}

	@Override
	public Object laden(File selectedFile) throws IOException {
		 try {
System.out.println("dübdüb");
				
				JAXBContext jaxbContext = JAXBContext.newInstance(SpielBean.class);
				Unmarshaller um = jaxbContext.createUnmarshaller();
				Object o = (Object) um.unmarshal(selectedFile);
				SpielBean p = (SpielBean) o;
				System.out.println(p);
				return p;
				

			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
		return null;
	}

}

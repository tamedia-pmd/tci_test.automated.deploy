package ch.txg.core.pdf;

import java.io.Serializable;

import org.apache.pdfbox.pdmodel.*;
import org.slf4j.*;

import ch.txg.core.bean.pdfProperties.PDFProperties;
import ch.txg.core.exceptions.UnableToParsePDFPropertiesException;


@SuppressWarnings("serial")
public class PDFExtractor implements Serializable{

	/**
	 * @author Marco Mele
	 * @contact marco.mele@catenate.com
	 * @date 13.11.2020
	 */
	private Logger logger=null;
	private String lineseparator = System.getProperty("line.separator");;
	
	public PDFExtractor() {
		logger = LoggerFactory.getLogger(this.getClass().getName());
		logger.debug("PDFExtractor class instantiated...");
		System.out.println("If you cannot see the java logs, please add the configuration of the logger into the logback.xml. Logger name: "+this.getClass().getName());
	}
	
	/**
	 * Extract properties from a pdf
	 * 
	 * @param filename
	 * @param pdfContentBase64
	 * @return PDFProperties
	 * @throws Throwable
	 * 
	 */
	public PDFProperties getProperties(String filename,byte[] pdfContentBase64) throws Throwable{
		
		PDFProperties result = new PDFProperties();
		ClassLoader cl = null;
		PDDocument doc = null;
		String author=null;
		String title=null;
		String object=null;
		String keywords=null;
		
		try{
			logger.info("getPDFProperties started for the file: "+filename);
			
			cl = Thread.currentThread().getContextClassLoader();
			
			// Load the pdf content and populate the PDDocumentInformation

			doc = PDDocument.load(pdfContentBase64);
			PDDocumentInformation info = doc.getDocumentInformation();
			author=info.getAuthor();
			title=info.getTitle();
			object=info.getSubject();
			keywords=info.getKeywords();
			
			if(author==null && title==null && object==null && keywords==null){
				throw new UnableToParsePDFPropertiesException("Unable to Parse PDF Properties for the filename "+filename);
			}
			
			// Set the PDFProperty Bean
			result.setAuthor(author);
			result.setTitle(title);
			result.setObject(object);
			result.setKeywords(keywords);
			
		}finally {
			Thread.currentThread().setContextClassLoader(cl);
			doc.close();
			logger.info("getPDFProperties ended for the file: "+filename);
			logger.debug("getPDFProperties ended with the following properties found: "+lineseparator
					+ "author: "+author+lineseparator
					+ "title: "+title+lineseparator
					+ "object: "+object+lineseparator
					+ "keywords: "+keywords+lineseparator
					);
			}
		return result;
	}
	
}

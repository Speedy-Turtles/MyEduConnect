package app.project.controller.etudiant;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import app.project.service.etudiant.PdfService;


@CrossOrigin
@RestController
public class GererPdf {

	@Autowired
	PdfService pdfServie;
	
	@GetMapping(value = "/getPdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> generatePdf(HttpServletRequest request) {
	    try {
	        // Generate the PDF byte array
	        byte[] pdfBytes = pdfServie.generatePdfBytes(request);

	        // Set the response headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("Document.pdf").build());
	        return ResponseEntity.ok().headers(headers).body(pdfBytes);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}



   
}

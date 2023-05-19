package app.project.service.etudiant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.project.entities.User;
import app.project.service.UserService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

import javax.servlet.http.HttpServletRequest;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

@Service
public class PdfService {
	
	@Autowired
	UserService userService;
	public byte[] generatePdfBytes(HttpServletRequest request) throws IOException {
		User user =userService.UserAuth(request);
	    PDDocument document = new PDDocument();
	    PDPage firstPage = new PDPage();
	    document.addPage(firstPage);
	    
	    try (PDPageContentStream contentStream = new PDPageContentStream(document, firstPage)) {
	    	PDFont font = PDType1Font.TIMES_ROMAN;
	    	
	    	float pageHeight = firstPage.getTrimBox().getHeight();
			float pageWidth = firstPage.getTrimBox().getWidth();
			float titleWidth = font.getStringWidth("Institut Supérieur des Etudes Technologiques de Bizerte") / 1000 * 17;
            float startX = (pageWidth - titleWidth) / 2;
            
            float titleWidth1 = font.getStringWidth("Attestation de présence") / 1000 * 17;
            float startX1 = (pageWidth - titleWidth1) / 2;
            
            float titleWidth2 = font.getStringWidth("Attestation de présence") / 1000 * 17;
            float startX2 = (pageWidth - titleWidth2) / 2;
            
            float titleWidth3 = font.getStringWidth("Complexe universitaire, Manzil Abd al-Rahman Road, P.O. num 65") / 1000 * 17;
            float startX3 = (pageWidth - titleWidth2) / 2;
           
            
            
	        contentStream.beginText();
	        contentStream.setFont(PDType1Font.TIMES_ROMAN, 15);
            contentStream.newLineAtOffset(20, pageHeight - 30);
            contentStream.setLeading(30.0f);
        	contentStream.showText("République Tunisienne");
        	contentStream.newLine();
        	contentStream.showText("Ministère de l'Enseignement Supérieur et de la Recherche Scientifique");
        	contentStream.newLine();
        	contentStream.showText("Direction Générale des Etudes Technologiques");
        	contentStream.newLine();
        	contentStream.endText();
        	
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 17);
        	contentStream.newLineAtOffset(startX,pageHeight - 140);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Institut Supérieur des Etudes Technologiques de Bizerte");
        	contentStream.endText();
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 17);
        	contentStream.newLineAtOffset(startX2,pageHeight - 170);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("2023 - 2022");
        	contentStream.endText();
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 15);
        	contentStream.newLineAtOffset(20,pageHeight - 220);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Le secrétaire général de l'Institut d'études technologiques de Bizerte témoigne que :");
        	contentStream.endText();
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 15);
        	contentStream.newLineAtOffset(20,pageHeight - 260);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Prenom : " + user.getFirstName());
        	contentStream.endText();
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 15);
        	contentStream.newLineAtOffset(20,pageHeight - 300);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Nom : " + user.getLastName());
        	contentStream.endText();
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 15);
        	contentStream.newLineAtOffset(20,pageHeight - 340);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Né en : " + user.getBirth_day());
        	contentStream.endText();
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 15);
        	contentStream.newLineAtOffset(20,pageHeight - 380);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Titulaire d'une carte d'identité nationale Num : " + user.getCin());
        	contentStream.endText();
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 15);
        	contentStream.newLineAtOffset(20,pageHeight - 420);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Licence : Baccalauréat en technologie de l'information");
        	contentStream.endText();
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 15);
        	contentStream.newLineAtOffset(20,pageHeight - 460);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Niveau : " +user.getClasse().getSpec().getNiveau());
        	contentStream.endText();
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 15);
        	contentStream.newLineAtOffset(20,pageHeight - 500);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Classe : " +user.getClasse().getNom());
        	contentStream.endText();
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 17);
        	contentStream.newLineAtOffset(20,pageHeight - 560);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Continue l'étudier Régulièrement pour l'année universaire en cours");
        	contentStream.endText();
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 17);
        	contentStream.newLineAtOffset(20,pageHeight - 580);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Ce certificat a été remis à concerné L'ordre de le jeter à celui qui a de la considéré");
        	contentStream.endText();
        	
        	Date aujourdhui = new Date();
        	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        	contentStream.beginText();
        	contentStream.setFont(font, 17);
        	contentStream.newLineAtOffset(20,pageHeight - 620);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Bizerte à "+formatter.format(aujourdhui));
        	contentStream.endText();
        	
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 18);
        	contentStream.newLineAtOffset(20,pageHeight - 660);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Le secrétaire général");
        	contentStream.endText();
        	
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 18);
        	contentStream.newLineAtOffset(20,pageHeight - 700);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Kamel El Karoui");
        	contentStream.endText();
        	
        	float titleWidth4 = font.getStringWidth("Complexe universitaire, Manzil Abd al-Rahman Road, P.O. num 65") / 1000 * 14;
            float startX4 = (pageWidth - titleWidth4) / 2;
            
            float titleWidth5 = font.getStringWidth("Téléphone : 72570601 Fax : 72572455 Adresse e-mail") / 1000 * 14;
            float startX5 = (pageWidth - titleWidth5) / 2;
            
            contentStream.beginText();
        	contentStream.setFont(font, 14);
        	contentStream.newLineAtOffset(startX4,pageHeight - 740);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Complexe universitaire, Manzil Abd al-Rahman Road, P.O. num 65");
        	contentStream.endText();
        	
        	contentStream.beginText();
        	contentStream.setFont(font, 14);
        	contentStream.newLineAtOffset(startX4,pageHeight - 760);
        	contentStream.setLeading(30.0f);
        	contentStream.showText("Téléphone : 72570601 Fax : 72572455 Adresse e-mail");
        	contentStream.endText();
        	
        	
	    }

	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    document.save(byteArrayOutputStream);
	    document.close();

	    return byteArrayOutputStream.toByteArray();
	}
}

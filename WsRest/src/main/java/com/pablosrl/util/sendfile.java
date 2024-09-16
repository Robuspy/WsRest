package com.pablosrl.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class sendfile  {

	private String to;
	private String from;
	private String host;
	private String filename;
	private boolean debug;
	private String msgText1 = "\n Nuevas sincronizaciones \n";
	private String subject = "Pedidos App Inventiva";

    public sendfile(String... args) {
		to 			= args[0];
		from	 	= args[1];
		host 		= args[2];
		filename 	= args[3];
		msgText1	+= args[4];
		debug 		= Boolean.valueOf(args[5]).booleanValue();
    }

    public void sendMail() {
    	new Thread(new Runnable() {
		    @Override public void run() {
		// create some properties and get the default Session
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.user", from);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from,"inventiva000");
			}
		});
		session.setDebug(debug);

		try {
		    // create a message
		    MimeMessage msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress(from));
		    InternetAddress[] address = {new InternetAddress(to)};
		    msg.setRecipients(Message.RecipientType.TO, address);
		    msg.setSubject(subject);

		    // create and fill the first message part
		    MimeBodyPart mbp1 = new MimeBodyPart();
		    mbp1.setText(msgText1);

		    // create the second message part
		    //MimeBodyPart mbp2 = new MimeBodyPart();

		    // attach the file to the message
		    //mbp2.attachFile(filename);

		    /*
		     * Use the following approach instead of the above line if
		     * you want to control the MIME type of the attached file.
		     * Normally you should never need to do this.
		     *
		    FileDataSource fds = new FileDataSource(filename) {
			public String getContentType() {
			    return "application/octet-stream";
			}
		    };
		    mbp2.setDataHandler(new DataHandler(fds));
		    mbp2.setFileName(fds.getName());
		     */

		    // create the Multipart and add its parts to it
		    Multipart mp = new MimeMultipart();
		    mp.addBodyPart(mbp1);
		    //mp.addBodyPart(mbp2);

		    // add the Multipart to the message
		    msg.setContent(mp);

		    // set the Date: header
		    msg.setSentDate(new Date());

		    /*
		     * If you want to control the Content-Transfer-Encoding
		     * of the attached file, do the following.  Normally you
		     * should never need to do this.
		     *
		    msg.saveChanges();
		    mbp2.setHeader("Content-Transfer-Encoding", "base64");
		     */

		    // send the message
		    Transport.send(msg);

		} catch (MessagingException mex) {
		    mex.printStackTrace();
		    Exception ex = null;
		    if ((ex = mex.getNextException()) != null) {
		    	ex.printStackTrace();
		    }
		}
		    }
		}).start();
    }

	public static byte[] readFile(String filePath) throws IOException {
		return Files.readAllBytes(Paths.get(filePath));
	}
}
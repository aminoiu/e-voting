package com.electronicvoting.helper;

import com.electronicvoting.domain.dto.Mail;
import com.electronicvoting.service.mail.MailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.resource.beans.container.internal.CdiBeanContainerExtendedAccessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import javax.inject.Named;
import java.util.Date;

@Named
@RequiredArgsConstructor
public class SendMailSMTP {
    @Autowired
    private Environment env;
    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    public void sendEmailToVoterStart(String s, String votingTitle, String voteCode, Date startDate, Date endDate, String startedBy, String tempPass) {
        Mail mail = new Mail();
        mail.setMailFrom(env.getProperty("spring.mail.username"));
        mail.setMailTo(s);
        mail.setMailSubject("Sesiune de vot noua -> " + votingTitle);
        mail.setMailContent(
                "<html>" +
                        "<body>" +
                        "<div>" +
                        "<h4>" + " Buna ziua. \n Ai fost adaugat in noua sesiune de vot in calitate de <i><b> alegator </b></i>. " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Titlu: " + votingTitle +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Creat de: " + startedBy +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Data de inceput: " + startDate +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Data de sfarsit: " + endDate +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Codul pentru alegeri: <b>" + voteCode + "</b>" +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Votul tau este foarte important! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Pentru tine a fost creat un nou cont de utilizator, cu rolul de <b>ALEGATOR</b>" +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Parola temporara: " + tempPass +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p style='color:red;'>" + " *<i> La prima autentificare, va trebui sa-ti setezi o noua parola, pentru a asigura contul tau! </i>" +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Iti multumim! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Sa ai o zi buna in continuare! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Echipa EVoting! " +
                        "</h4>" +
                        "</div>" +
                        "</body>" +
                        "</html>"


        );

        MailService mailService = (MailService) applicationContextProvider.getApplicationContext().getBean("mailService");
        mailService.sendEmail(mail);
    }

    public void sendEmailToCandidateStart(String s, String votingTitle, Date startDate, Date endDate, String startedBy, String tempPass) {
        Mail mail = new Mail();
        mail.setMailFrom(env.getProperty("spring.mail.username"));
        mail.setMailTo(s);
        mail.setMailSubject("Sesiune de vot noua  -> " + votingTitle);
        mail.setMailContent(
                "<html>" +
                        "<body>" +
                        "<div>" +
                        "<h4>" + " Buna ziua. \n Ai fost adaugat in noua sesiune de vot in calitate de <i><b> candidat </b></i>. " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Titlu: " + votingTitle +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Creat de: " + startedBy +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Data de inceput: " + startDate +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Data de sfarsit: " + endDate +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " La sfirsitul acestei sesiuni de vot, vei primi un e-mail cu informatii despre cistigator! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Pentru tine a fost creat un nou cont de utilizator, cu rolul de <b>CANDIDAT</b>" +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Parola temporara: " + tempPass +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p style='color:red;'>" + " *<i> La prima autentificare, va trebui sa-ti setezi o noua parola, pentru a asigura contul tau! </i>" +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Iti multumim! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Sa ai o zi buna in continuare!! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Echipa EVoting!! " +
                        "</h4>" +
                        "</div>" +
                        "</body>" +
                        "</html>"


        );

        MailService mailService = (MailService) applicationContextProvider.getApplicationContext().getBean("mailService");
        mailService.sendEmail(mail);
    }

    public void sendEmailToTheWinner(String s, String votingTitle, Date startDate, Date endDate, String startedBy, Long score, Integer numberOfVoters) {
        Mail mail = new Mail();
        mail.setMailFrom(env.getProperty("spring.mail.username"));
        mail.setMailTo(s);
        mail.setMailSubject("Rezultatele sesiunii de vot -> " + votingTitle);
        mail.setMailContent(
                "<html>" +
                        "<body>" +
                        "<div>" +
                        "<h1 style='color:red;'>" + " FELICITARI!" +
                        "</h1>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Ai fost ales cistigatorul sesiunii de vot: " + votingTitle + "!" +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Scorul acumulat: " + "<b>"+score +"</b>"+ " din " +"</b>"+ numberOfVoters +"</b>"+ " posibile voturi"+
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Sesiunea a fost creata de catre: " + startedBy +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " A inceput: " + startDate +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " A sfarsit: " + endDate +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Iti multumim! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Sa ai o zi buna in continuare! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Cu drag, Echipa Evoting! " +
                        "</h4>" +
                        "</div>" +
                        "</body>" +
                        "</html>"


        );

        MailService mailService = (MailService) applicationContextProvider.getApplicationContext().getBean("mailService");
        mailService.sendEmail(mail);
    }

    public void sendEmailToAllVoters(String s, String votingTitle, Date startDate, Date endDate, String startedBy, Long score, Integer numberOfVoters, String votingWinner) {
        Mail mail = new Mail();
        mail.setMailFrom(env.getProperty("spring.mail.username"));
        mail.setMailTo(s);
        mail.setMailSubject("Rezultatele sesiunii de vot -> " + votingTitle);
        mail.setMailContent(
                "<html>" +
                        "<body>" +
                        "<div>" +
                        "<h3 style='color:red;'>" + "Iti multumim pentru participarea la vot!" +
                        "</h3>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + "Candidatul "+votingWinner+" a fost ales cistigatorul sesiunii de vot: " + votingTitle + "!" +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Scorul acumulat: " + "<b>"+score +"</b>"+ " din " +"</b>"+ numberOfVoters +"</b>"+ " posibile voturi"+
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Sesiunea a fost creata de catre: " + startedBy +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " A inceput: " + startDate +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " A sfarsit: " + endDate +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Iti multumim! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Sa ai o zi buna in continuare! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Cu drag, Echipa Evoting! " +
                        "</h4>" +
                        "</div>" +
                        "</body>" +
                        "</html>"


        );

        MailService mailService = (MailService) applicationContextProvider.getApplicationContext().getBean("mailService");
        mailService.sendEmail(mail);
    }

    public void sendEmailToAllCandidates(String s, String votingTitle, Date startDate, Date endDate, String startedBy, Long score, Integer numberOfVoters, String votingWinner) {
        Mail mail = new Mail();
        mail.setMailFrom(env.getProperty("spring.mail.username"));
        mail.setMailTo(s);
        mail.setMailSubject("Rezultatele sesiunii de vot -> " + votingTitle);
        mail.setMailContent(
                "<html>" +
                        "<body>" +
                        "<div>" +
                        "<h3 style='color:red;'>" + "Ne pare rau, a fost ales alt cistigator!" +
                        "</h3>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + "Candidatul "+votingWinner+" a fost ales cistigatorul sesiunii de vot: " + votingTitle + "!" +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Scorul acumulat: " + "<b>"+score +"</b>"+ " din " +"</b>"+ numberOfVoters +"</b>"+ " posibile voturi"+
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Sesiunea a fost creata de catre: " + startedBy +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " A inceput: " + startDate +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " A sfarsit: " + endDate +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Iti multumim! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Sa ai o zi buna in continuare! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Cu drag, Echipa Evoting! " +
                        "</h4>" +
                        "</div>" +
                        "</body>" +
                        "</html>"


        );

        MailService mailService = (MailService) applicationContextProvider.getApplicationContext().getBean("mailService");
        mailService.sendEmail(mail);
    }
}

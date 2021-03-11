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
        mail.setMailSubject("New voting session -> " + votingTitle);
        mail.setMailContent(
                "<html>" +
                    "<body>" +
                        "<div>" +
                        "<h4>" + " Hello. \n Was created a new voting session as a <i><b> voter </b></i>. " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Title: " + votingTitle +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Created by: " + startedBy +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Start date: " + startDate +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " End date: " + endDate +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Code for voting: <b>" + voteCode + "</b>" +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Your vote is very important! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " For you was created a new account into the Evoting application, with role <b>VOTER</b>" +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Temporary password: " + tempPass +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p style='color:red;'>" + " *<i> On first login, you will have to change this password, to secure your account! </i>" +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Thank you! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Have a nice day! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Evoting team! " +
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
        mail.setMailSubject("New voting session -> " + votingTitle);
        mail.setMailContent(
                "<html>" +
                        "<body>" +
                        "<div>" +
                        "<h4>" + " Hello. \n Was created a new voting session where you are involved as a <i><b> candidate </b></i>. " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Title: " + votingTitle +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Created by: " + startedBy +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Start date: " + startDate +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " End date: " + endDate +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " At the end of this voting session, you will receive an email with winner information! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " For you was created a new account into the Evoting application, with role <b>CANDIDATE</b>" +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<p>" + " Temporary password: " + tempPass +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<p style='color:red;'>" + " *<i> On first login, you will have to change this password, to secure your account! </i>" +
                        "</p>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Thank you! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Have a nice day! " +
                        "</h4>" +
                        "</div>" +

                        "<div>" +
                        "<h4>" + " Evoting team! " +
                        "</h4>" +
                        "</div>" +
                        "</body>" +
                        "</html>"


        );

        MailService mailService = (MailService) applicationContextProvider.getApplicationContext().getBean("mailService");
        mailService.sendEmail(mail);
    }
}

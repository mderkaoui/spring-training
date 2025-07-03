package fr.dawan.project1.services.impl;

import fr.dawan.project1.entities.Product;
import fr.dawan.project1.repositories.ProductRepository;
import fr.dawan.project1.services.EmailService;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;
    private ProductRepository productRepository;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, ProductRepository productRepository) {
        this.mailSender = mailSender;
        this.productRepository=productRepository;
    }

    @Override
    public void sendStockNotification(int min) throws Exception {

        StringBuilder mailContent = new StringBuilder("Bonjour, \n les produits suivants vont être prochainement en rupture de stock : \n");
        List<Product> products = productRepository.findOutOfStock(min);
        for(Product p : products){
            mailContent.append(p.getName() + " : " + p.getQtyInStock()).append("\n");
        }

        MimeMessage msg = mailSender.createMimeMessage();
        msg.setSubject("Rappel des produits en rupture");
        msg.setFrom("contact@dawan.fr");
        msg.setRecipients(MimeMessage.RecipientType.TO,"mderkaoui@dawan.fr");
        msg.setText(mailContent.toString());
        mailSender.send(msg);

    }
}

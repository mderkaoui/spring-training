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

        List<Product> products = productRepository.findOutOfStock(min);

        //--------------------------------
        StringBuilder mailContent = new StringBuilder("Bonjour, \n les produits suivants vont être prochainement en rupture de stock : \n");
        for(Product p : products){
            mailContent.append(p.getName() + " : " + p.getQtyInStock()).append("\n");
        }
        mailContent.append("\n Messagerie automatique");
        //---------------------------------------

        //----------------------------------------
        //charger le template products-out-stock.ftl et je lui passe products en paramètre
        //ce qui retourne une chaine
        //il faut injecter : 	@Autowired private Configuration freemarkerConfig;
        //freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
        //Template template = freemarkerConfig.getTemplate("products-out-stock.ftl");
        //Map<String, Object> model = new HashMap<String, Object>();
        //model.put("products", products);
        //String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        //-------------------------------------------



        MimeMessage msg = mailSender.createMimeMessage();
        msg.setSubject("Rappel des produits en rupture");
        msg.setFrom("alfredo.schmeler26@ethereal.email");
        msg.setRecipients(MimeMessage.RecipientType.TO,"mderkaoui@dawan.fr");
        msg.setText(mailContent.toString());
        mailSender.send(msg);

    }
}

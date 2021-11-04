package com.dbc.pessoaapi.service;
import com.dbc.pessoaapi.DTO.PessoaCreateDTO;
import com.dbc.pessoaapi.DTO.PessoaDTO;
import com.dbc.pessoaapi.entity.PessoaEntity;

import com.dbc.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private  final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;

    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String remetente;
    private final Configuration configuration;



    public PessoaDTO create(PessoaCreateDTO pessoaCreateDTO) throws Exception {
        PessoaEntity pessoaEntity = objectMapper.convertValue(pessoaCreateDTO, PessoaEntity.class);
        PessoaEntity pessoaCriada = pessoaRepository.create(pessoaEntity);
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaCriada, PessoaDTO.class);
        //enviarEmailSimples(pessoaDTO);
        return pessoaDTO;

    }
    public List<PessoaDTO> list() {
        return pessoaRepository.list().stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .collect(Collectors.toList());
    }

    public <PessoaCreateDTO> PessoaDTO update(Integer id,
                                              PessoaCreateDTO pessoaCreateDTO) throws Exception {
        PessoaEntity entity = objectMapper.convertValue(pessoaCreateDTO, PessoaEntity.class);
        PessoaEntity update = pessoaRepository.update(id, entity);
        PessoaDTO dto = objectMapper.convertValue(update, PessoaDTO.class);
       // enviarEmailComTemplate(dto);
        return dto;
    }

    public void delete(Integer id) throws Exception {

        PessoaEntity pessoarecuperada = pessoaRepository.getIdById(id);
        pessoaRepository.delete(pessoarecuperada.getIdPessoa());
        //enviarEmailComTemplateNoDelete(pessoarecuperada);

    }

    public List<PessoaDTO> listByName(String nome) {
        return pessoaRepository.listByName(nome).stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .collect(Collectors.toList());
    }
    public void enviarEmailSimples( PessoaDTO pessoaDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(remetente);
        message.setTo(pessoaDTO.getEmail());
        message.setSubject("Assunto do e-mail");
        message.setText("Olá" + pessoaDTO.getNome() +"Estamos felizes em ter voce em nosso sistema!" + remetente+  "Seu cadastro foi efeituado com sucesso\n seu identificador é  Qualquer coisa so contatar o suporte pelo email ${email{propriedadespring.mail.username} \n\n\nAtt,\n Adriele." );
        emailSender.send(message);
    }
    public void enviarEmailComTemplate( PessoaDTO pessoaDTO) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(remetente);
        helper.setTo( pessoaDTO.getEmail());
        helper.setSubject("Email com template Exemplo");

        Template template = configuration.getTemplate("email-template.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nomeUsuario", pessoaDTO.getNome());
        dados.put("email", remetente );
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        helper.setText(html, true);

        emailSender.send(mimeMessage);
    }
    public void enviarEmailComTemplateNoDelete(PessoaEntity pessoaEntity) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(remetente);
        helper.setTo(pessoaEntity.getEmail());
        helper.setSubject("Exclusão cadastro");

        Template template = configuration.getTemplate("email-template2.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nomeUsuario", pessoaEntity.getNome());
        dados.put("email", remetente);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        helper.setText(html, true);

        emailSender.send(mimeMessage);
    }

}
package com.moudjames23.sms.services;


import com.moudjames23.sms.entities.Customer;
import com.moudjames23.sms.entities.Template;
import com.moudjames23.sms.exceptions.ResourceAlreadyExistException;
import com.moudjames23.sms.exceptions.ResourceNotFoundException;
import com.moudjames23.sms.repositories.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {

    private final TemplateRepository repository;

    public TemplateService(TemplateRepository repository) {
        this.repository = repository;
    }

    public List<Template> index() {
        return this.repository.findAll();
    }

    public Template create(Template template) {

        if (this.byTitle(template.getTitle()) != null)
            throw new ResourceAlreadyExistException("Template has already this title");

        return this.repository.save(template);
    }

    public Template show(int id) {

        return byId(id);
    }

    public Template update(Template data, int id) {

        this.byId(id);

        Template template = byTitle(data.getTitle());

        if (template != null && template.getTitle().equals(data.getTitle()))
        {
            data.setId(template.getId());
            return this.repository.save(data);
        }

        throw new ResourceAlreadyExistException("Template has already this title");
    }


    public void delete(int id) {
        Template template = byId(id);

        this.repository.delete(template);


    }

    public Template byId(int id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Template doesn't exist"));
    }

    public Template byTitle(String title)
    {
        return  this.repository.findTemplateByTitle(title);
    }
}



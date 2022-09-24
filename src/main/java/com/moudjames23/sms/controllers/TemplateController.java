package com.moudjames23.sms.controllers;

import com.moudjames23.sms.dto.HttpResponse;
import com.moudjames23.sms.entities.Template;
import com.moudjames23.sms.services.TemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.moudjames23.sms.dto.HttpResponse.generateResponse;

@RestController
@RequestMapping("api/v1/templates")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping
    public ResponseEntity<Object> index()
    {
        List<Template> templates = this.templateService.index();

        return generateResponse("Successs", HttpStatus.OK, templates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable int id)
    {
        Template template = this.templateService.show(id);

        return  generateResponse("Success", HttpStatus.OK, template);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Template template)
    {
        return generateResponse("Success", HttpStatus.OK, this.templateService.create(template));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @Valid @RequestBody Template template)
    {
        return  generateResponse("Success", HttpStatus.OK, this.templateService.update(template, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id)
    {
        templateService.delete(id);
        return generateResponse("Success", HttpStatus.OK, null);
    }
}

package com.moudjames23.sms.controllers;

import com.moudjames23.sms.dto.HttpResponse;
import com.moudjames23.sms.entities.Tag;
import com.moudjames23.sms.services.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.moudjames23.sms.dto.HttpResponse.*;

@RestController
@RequestMapping("api/v1/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<Object> index()
    {
        List<Tag> tags = this.tagService.index();
        return generateResponse("Success", HttpStatus.OK, tags);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Tag data)
    {
        return  generateResponse("SUCCESS", HttpStatus.CREATED, this.tagService.create(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable  int id)
    {
        return generateResponse("SUCCESS", HttpStatus.OK, this.tagService.show(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @RequestBody Tag tag, @PathVariable int id)
    {
        return generateResponse("SUCCESS", HttpStatus.OK, this.tagService.update(tag, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id)
    {
        this.tagService.delete(id);
        return generateResponse("SUCCESS", HttpStatus.OK, null);
    }
}

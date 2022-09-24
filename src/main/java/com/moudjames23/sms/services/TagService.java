package com.moudjames23.sms.services;

import com.moudjames23.sms.entities.Tag;
import com.moudjames23.sms.exceptions.ResourceAlreadyExistException;
import com.moudjames23.sms.exceptions.ResourceNotFoundException;
import com.moudjames23.sms.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository repository;

    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public List<Tag> index()
    {
        return this.repository.findAll();
    }

    public Tag create(Tag data)
    {
        Tag tag = this.byName(data.getName());

        if (tag != null)
            throw new ResourceAlreadyExistException("This tag " +data.getName()+ " already exist");

        return this.repository.save(data);
    }

    public Tag show(int id)
    {
        return this.byId(id);
    }

    public Tag update(Tag data, int id)
    {
        this.byId(id);

        Tag tag = this.byName(data.getName());

        if (tag != null && tag.getId() == id)
        {
            data.setId(tag.getId());
            return this.repository.save(data);
        }

        throw new ResourceAlreadyExistException("This tag " +data.getName()+ " already exist");
    }

    public void delete(int id)
    {
        this.byId(id);

        this.repository.deleteById(id);
    }


    public Tag byId(int id)
    {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This tag doesn't exist"));
    }

    public Tag byName(String name)
    {
        return this.repository.findTagsByName(name);
    }
}

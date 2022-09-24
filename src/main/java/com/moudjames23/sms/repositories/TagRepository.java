package com.moudjames23.sms.repositories;

import com.moudjames23.sms.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Tag findTagsByName(String name);
}

package com.moudjames23.sms.repositories;

import com.moudjames23.sms.entities.Customer;
import com.moudjames23.sms.entities.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Integer> {

    Template findTemplateByTitle(String title);
}

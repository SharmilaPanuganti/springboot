package cgg.smartcontact.smartcontactmanager.repositories;

import cgg.smartcontact.smartcontactmanager.entities.Contact;
import cgg.smartcontact.smartcontactmanager.entities.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
  public Page<Contact> findByUser(User user, Pageable pePageable);
}

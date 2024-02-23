package de.ng.master.architecture.services;

import de.ng.master.architecture.data.SimpleEventEntity;
import de.ng.master.architecture.data.SimpleEventRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SimpleEventService {

  private final SimpleEventRepository repository;

  public SimpleEventService(SimpleEventRepository repository) {
    this.repository = repository;
  }

  public Optional<SimpleEventEntity> get(Long id) {
    return repository.findById(id);
  }

  public SimpleEventEntity update(SimpleEventEntity entity) {
    return repository.save(entity);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public Page<SimpleEventEntity> list(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public Page<SimpleEventEntity> list(Pageable pageable, Specification<SimpleEventEntity> filter) {
    return repository.findAll(filter, pageable);
  }

  public int count() {
    return (int) repository.count();
  }

}

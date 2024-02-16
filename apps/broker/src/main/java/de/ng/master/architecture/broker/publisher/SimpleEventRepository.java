package de.ng.master.architecture.broker.publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleEventRepository extends JpaRepository<SimpleEventEntity, Long> {

}

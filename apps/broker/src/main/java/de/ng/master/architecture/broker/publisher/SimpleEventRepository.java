package de.ng.master.architecture.broker.publisher;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleEventRepository extends JpaRepository<SimpleEventEntity, Long> {

  List<SimpleEventEntity> findSimpleEventEntitiesByNameAndPublishedIsFalse(String name);
}

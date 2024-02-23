package de.ng.master.architecture.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleEventRepository extends JpaRepository<SimpleEventEntity, Long>, JpaSpecificationExecutor<SimpleEventEntity> {

}

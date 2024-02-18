package de.ng.master.architecture.broker.subscriber;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriberEntity, Long> {

  List<SubscriberEntity> findSubscriberEntitiesByTopic(String topic);

  Optional<SubscriberEntity> findSubscriberEntityByCallbackUrlAndTopic(String clientUrl, String topic);
}

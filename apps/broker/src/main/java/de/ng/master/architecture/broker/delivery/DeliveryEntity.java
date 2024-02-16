package de.ng.master.architecture.broker.delivery;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryEntity {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;
  private Long eventId;
  private Long subscriberId;
  private boolean delivered;


}

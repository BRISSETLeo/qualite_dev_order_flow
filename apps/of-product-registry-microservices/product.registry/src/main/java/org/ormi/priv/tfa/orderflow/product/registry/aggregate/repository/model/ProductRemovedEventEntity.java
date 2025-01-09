package org.ormi.priv.tfa.orderflow.product.registry.aggregate.repository.model;

public class ProductRemovedEventEntity extends ProductRegistryEventEntity {
  static final String EVENT_TYPE = "ProductRemoved";

  /**
   * Payload for the event.
   */
  public static record Payload (
    /**
     * The id of the product.
     */
    String productId
  ) {}

  /**
   * The payload for the event.
   */
  private Payload payload;

  /**
   * Get the payload for the event.
   * @return the payload.
   */
  public Payload getPayload() {
    return payload;
  }

  @Override
  public String getEventType() {
    return EVENT_TYPE;
  }
  
}

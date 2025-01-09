package org.ormi.priv.tfa.orderflow.product.registry.aggregate.repository.model;

public class ProductUpdatedEventEntity extends ProductRegistryEventEntity {
  static final String EVENT_TYPE = "ProductUpdated";

  /**
   * Payload for the event.
   */
  public static record Payload (
    /**
     * The id of the product.
     */
    String productId,
    /**
     * The name of the product.
     */
    String name,
    /**
     * The description of the product.
     */
    String productDescription
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

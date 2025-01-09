package org.ormi.priv.tfa.orderflow.product.registry.read.service;

import java.util.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.ormi.priv.tfa.orderflow.lib.publishedlanguage.event.ProductRegistryEvent;
import org.ormi.priv.tfa.orderflow.product.registry.read.projection.ProductRegistryProjector;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProductRegistryEventConsumer {

  @Inject
  private ProductRegistryProjector projector;

  @Incoming("product-registry-event")
  @Transactional(Transactional.TxType.REQUIRED)
  public void handleEvent(ProductRegistryEvent event) {
    // Project the event
    projector.handleEvent(event);
    // Sink the event here once or while projection is processed
    sinkEvent(event);
  }

  private void sinkEvent(ProductRegistryEvent event) {
    // Implementation for sinking the event
    // This could involve logging, sending to another service, etc.
    Logger logger = Logger.getLogger(ProductRegistryEventConsumer.class.getName());
    if (logger.isLoggable(java.util.logging.Level.INFO)) {
        logger.info(String.format("Event processed: %s", event.toString()));
    }
  }
}

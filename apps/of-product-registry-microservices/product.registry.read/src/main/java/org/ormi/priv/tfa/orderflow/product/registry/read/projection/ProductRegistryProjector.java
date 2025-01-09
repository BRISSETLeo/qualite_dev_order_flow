package org.ormi.priv.tfa.orderflow.product.registry.read.projection;

import java.util.Optional;

import org.ormi.priv.tfa.orderflow.lib.publishedlanguage.event.ProductRegistered;
import org.ormi.priv.tfa.orderflow.lib.publishedlanguage.event.ProductRegistryEvent;
import org.ormi.priv.tfa.orderflow.lib.publishedlanguage.event.ProductRemoved;
import org.ormi.priv.tfa.orderflow.lib.publishedlanguage.event.ProductUpdated;
import org.ormi.priv.tfa.orderflow.product.registry.read.projection.repository.model.ProductEntity;
import org.ormi.priv.tfa.orderflow.product.registry.read.service.ProductService;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Product registry projector to project events to the read model.
 * 
 * @implNote This class is responsible for projecting the events to the read
 *           model.
 *           It reacts to the events and updates the read model accordingly.
 *           As the projector is in downstream of the event emitter, it is not
 *           his responsibility to verify integrity of the states
 *           as the aggregate and event emitter should have already done that.
 */
@ApplicationScoped
public class ProductRegistryProjector {

  /**
   * The product service.
   */
  @Inject
  private ProductService productService;

  /**
  * Handle the event.
  * 
  * @param event - the event to handle
  */
  public void handleEvent(ProductRegistryEvent event) {
    if (event instanceof ProductRegistered registered) {
      projectRegisteredProduct(registered);
    } else if (event instanceof ProductUpdated updated) {
      projectUpdatedProduct(updated);
    } else if (event instanceof ProductRemoved removed) {
      projectRemovedProduct(removed);
    }
  }

  /**
   * Project a registered product.
   * 
   * @param registered - the event to project
   */
  public void projectRegisteredProduct(ProductRegistered registered) {
    // Create a new product entity
    final ProductEntity product = new ProductEntity();
    product.productId = registered.getPayload().productId.getId();
    product.name = registered.getPayload().name;
    product.description = registered.getPayload().productDescription;
    
    // Persist the product
    productService.createProduct(product);
  }

  /**
   * Project an updated product.
   * 
   * @param updated - the event to project
   */
  public void projectUpdatedProduct(ProductUpdated updated) {
    // Get the product entity
    final Optional<ProductEntity> result = productService.getProductById(updated.getPayload().productId);
    if (result.isEmpty()) {
      // The product does not exist
      // Log an error
      Log.error("Product not found: " + updated.getPayload().productId);
      return;
    }
    // Update the product
    final ProductEntity product = result.get();
    product.name = updated.getPayload().name;
    product.description = updated.getPayload().productDescription;
    // Persist the product
    productService.updateProduct(product);
  }

  /**
   * Project a removed product.
   * 
   * @param removed - the event to project
   */
  public void projectRemovedProduct(ProductRemoved removed) {
    // Remove the product
    productService.removeProductById(removed.getPayload().productId);
  }
}

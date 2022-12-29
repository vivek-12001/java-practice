package sampleapp.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import sampleapp.dto.ProductRequestDTO;
import sampleapp.dto.ProductResponseDTO;
import sampleapp.entity.Product;
import sampleapp.exception.ProductNotFoundException;
import sampleapp.exception.ProductServiceException;
import sampleapp.repository.ProductRepository;
import sampleapp.utils.ValueMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private ProductRepository productRepository;

    public ProductResponseDTO createNewProduct(ProductRequestDTO productRequestDTO) throws ProductServiceException {

        ProductResponseDTO productResponseDTO;

        try {
            log.info("ProductService:createNewProduct execution started.");
            Product product = ValueMapper.convertToEntity(productRequestDTO);

            Product productResults = productRepository.save(product);
            productResponseDTO = ValueMapper.convertToDTO(productResults);

            log.debug("ProductService:createNewProduct received response from Database {}", ValueMapper.writeValueAsString(productRequestDTO));
        } catch (Exception e) {
            log.error("Exception occurred while persisting product to database , Exception message {}", e.getMessage());
            throw new ProductServiceException("Exception occured while creating new product");
        }
        log.info("ProductService:createNewProduct execution ended.");
        return productResponseDTO;
    }

//    @Cacheable(value = "products")
    public List<ProductResponseDTO> getAllProducts() {
        List<ProductResponseDTO> productResponseDTO;

        try {
            log.info("ProductService:getProducts execution started.");

            List<Product> productList = productRepository.findAll();

            if (!productList.isEmpty()) {
                productResponseDTO = productList.stream().map(ValueMapper::convertToDTO).collect(Collectors.toList());
            } else {
                productResponseDTO = Collections.emptyList();
            }
            log.debug("ProductService:getProducts retrieving products from database  {}", ValueMapper.writeValueAsString(productResponseDTO));
        } catch (Exception ex) {
            log.error("Exception occurred while retrieving products from database , Exception message {}", ex.getMessage());
            throw new ProductServiceException("Exception occurred while fetch all products from Database");
        }
        log.info("ProductService:getProducts execution ended.");
        return productResponseDTO;
    }

//    @Cacheable(value = "product")
    public ProductResponseDTO getProductById(long productId) {
        ProductResponseDTO productResponseDTO;

        try {
            log.info("ProductService:getProductById execution started.");

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + productId));
            productResponseDTO = ValueMapper.convertToDTO(product);

            log.debug("ProductService:getProductById retrieving product from database for id {} {}", productId,
                    ValueMapper.writeValueAsString(productResponseDTO));
        } catch (Exception ex) {
            log.error("Exception occurred while retrieving product {} from database , Exception message {}", productId, ex.getMessage());
            throw new ProductServiceException("Exception occurred while fetch product from Database " + productId);
        }
        log.info("ProductService:getProductById execution ended.");
        return productResponseDTO;
    }

//    @Cacheable(value = "product")
    public Map<String, List<ProductResponseDTO>> getProductsByTypes() {
        try {
            log.info("ProductService:getProductsByTypes execution started.");

            Map<String, List<ProductResponseDTO>> productsMap = productRepository.findAll().stream().map(ValueMapper::convertToDTO)
                            .filter(productResponseDTO -> productResponseDTO.getProductType() != null)
                                    .collect(Collectors.groupingBy(ProductResponseDTO::getProductType));

            log.info("ProductService:getProductsByTypes execution ended.");
            return productsMap;

        } catch (Exception ex) {
            log.error("Exception occurred while retrieving product grouping by type from database , Exception message {}", ex.getMessage());
            throw new ProductServiceException("Exception occurred while fetch product from Database ");
        }
    }
}

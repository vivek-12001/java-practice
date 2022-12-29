package sampleapp.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sampleapp.dto.ApiResponseDTO;
import sampleapp.dto.ProductRequestDTO;
import sampleapp.dto.ProductResponseDTO;
import sampleapp.service.ProductService;
import sampleapp.utils.ValueMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private static final String SUCCESS = "Success";
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO> createNewProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) {

        log.info("ProductController::createNewProduct request body {}", ValueMapper.writeValueAsString(productRequestDTO));

        ProductResponseDTO productResponseDTO = productService.createNewProduct(productRequestDTO);

        ApiResponseDTO<ProductResponseDTO> productResponseDTOApiResponseDTO = ApiResponseDTO.
                <ProductResponseDTO>builder()
                .status(SUCCESS)
                .results(productResponseDTO)
                .build();

        log.info("ProductController::createNewProduct response {}", ValueMapper.writeValueAsString(productResponseDTOApiResponseDTO));
        return new ResponseEntity<>(productResponseDTOApiResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO> getProducts() {

        List<ProductResponseDTO> products = productService.getAllProducts();

        ApiResponseDTO<List<ProductResponseDTO>> responseDTO = ApiResponseDTO
                .<List<ProductResponseDTO>>builder()
                .status(SUCCESS)
                .results(products)
                .build();

        log.info("ProductController::getProducts response {}", ValueMapper.writeValueAsString(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable long productId) {

        log.info("ProductController::getProduct by id  {}", productId);

        ProductResponseDTO productResponseDTO = productService.getProductById(productId);
        ApiResponseDTO<ProductResponseDTO> responseDTO = ApiResponseDTO
                .<ProductResponseDTO>builder()
                .status(SUCCESS)
                .results(productResponseDTO)
                .build();

        log.info("ProductController::getProduct by id  {} response {}", productId, ValueMapper
                .writeValueAsString(productResponseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/types")
    public ResponseEntity<ApiResponseDTO> getProductsGroupByType() {

        Map<String, List<ProductResponseDTO>> products = productService.getProductsByTypes();
        ApiResponseDTO<Map<String, List<ProductResponseDTO>>> responseDTO = ApiResponseDTO
                .<Map<String, List<ProductResponseDTO>>>builder()
                .status(SUCCESS)
                .results(products)
                .build();

        log.info("ProductController::getProductsGroupByType by types  {}", ValueMapper.writeValueAsString(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}

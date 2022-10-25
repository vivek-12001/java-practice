package inventory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Inventory {
    private Map<Integer, Product> aisleToProductMap;
    private Map<Integer, Integer> productIdToCountMap;
    Queue<Integer> availableAisle;

    public Inventory (int aisleCount) {
        availableAisle = new LinkedList<>();

        for (int i=1;i<=aisleCount;i++)
            availableAisle.add(i);

        aisleToProductMap = new HashMap<>();
        productIdToCountMap = new HashMap<>();
    }

    public void addProduct(Product product) throws Exception {
        int productId = product.getProductId();
        int productCount = productIdToCountMap.getOrDefault(productId, 0);
        if (productCount == 0) {
            if (availableAisle.isEmpty()) {
                throw new Exception("out of space to add product");
            }
            aisleToProductMap.put(availableAisle.poll(), product);
        }
        productIdToCountMap.put(productId, productCount + 1);
        System.out.println("product added = " + product);
    }

    public Product getProductAt(int aisleNumber) {
        return aisleToProductMap.get(aisleNumber);
    }

    public boolean checkIfProductAvailable(int productId) {
        int productCount = productIdToCountMap.getOrDefault(productId, 0);
        return productCount>0;
    }

    public void deductProductCount(int aisleNumber) {
        int productId = aisleToProductMap.get(aisleNumber).getProductId();
        int updatedProductCount = productIdToCountMap.get(productId) - 1;

        if (updatedProductCount == 0) {
            productIdToCountMap.remove(aisleNumber);
            aisleToProductMap.remove(aisleNumber);
            availableAisle.add(aisleNumber);
        } else {
            productIdToCountMap.put(productId, updatedProductCount);
        }

    }
}

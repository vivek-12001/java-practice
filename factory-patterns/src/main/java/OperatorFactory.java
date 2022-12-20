import java.util.HashMap;
import java.util.Map;

public class OperatorFactory {

    static Map<String, Operation> operationMap = new HashMap<>();

    static {
        operationMap.put("add", new Addition());
        operationMap.put("subtract", new Subtract());
        operationMap.put("multiply", new Multiply());
    }

    public static Operation getOperation(String operator) {
        return operationMap.get(operator);
    }

}

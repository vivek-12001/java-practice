public class Calculator {

    public static void main(String[] args) {
        int a = 10;
        int b = 5;
        String operation = "multiply";

        Operation targetOperator = OperatorFactory.getOperation(operation);
        int ans = targetOperator.apply(a, b);

        System.out.println("ans = " + ans);
    }
}

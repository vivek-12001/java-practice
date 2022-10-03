public class DeliveryContext {

    private PackageState currentState;
    private Integer packageId;

    public DeliveryContext(PackageState currentState, Integer packageId) {
        this.currentState = currentState;
        this.packageId = packageId;
    }

    public PackageState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(PackageState currentState) {
        this.currentState = currentState;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public void update() {
        currentState.updateState(this);
    }
}

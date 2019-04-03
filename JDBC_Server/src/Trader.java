public class Trader extends Employee
{
    protected int Commission;
    protected int Commission_limit;

    public int getCommission_limit() {
        return Commission_limit;
    }

    public void setCommission_limit(int commission_limit) {
        Commission_limit = commission_limit;
    }

    public int getCommission() {
        return Commission;
    }

    public void setCommission(int commission) {
        Commission = commission;
    }
}

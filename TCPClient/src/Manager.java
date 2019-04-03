public class Manager extends Employee
{
    protected int Supplement;
    protected String CardID;
    protected int Cost_limit;

    public int getCost_limit() {
        return Cost_limit;
    }

    public void setCost_limit(int cost_limit) {
        Cost_limit = cost_limit;
    }

    public String getCardID() {
        return CardID;
    }

    public void setCardID(String cardID) {
        CardID = cardID;
    }

    public int getSupplement() {
        return Supplement;
    }

    public void setSupplement(int supplement) {
        Supplement = supplement;
    }





}

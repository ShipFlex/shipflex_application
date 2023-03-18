public class EOpties {
    private String romp;
    private String stuurinrichting;
    private String motor;
    public EOpties(String romp, String stuurinrichting, String motor){
        this.romp = romp;
        this.stuurinrichting = stuurinrichting;
        this.motor = motor;

    }
    public String getRomp(){
        return romp;

    }
    public void setRomp(String romp){
        this.romp = romp;
    }

    public String getStuurinrichting() {
        return stuurinrichting;
    }
    public void setStuurinrichting(String stuurinrichting){
        this.stuurinrichting = stuurinrichting;
    }
    public String getMotor(){
        return motor;

    }
    public void setMotor(String motor){
        this.motor = motor;
    }
}

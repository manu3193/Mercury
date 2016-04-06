package mercury.arm.hardware;

/**
 * Created by Kevin on 05/04/2016.
 */
public class Register {

    private String name;
    private String alternativeName;
    private String value;

    public Register() {
        name = "";
        alternativeName = "";
        value = "";
    }

    public Register(String name, String alternativeName, String value) {
        this.name = name;
        this.alternativeName = alternativeName;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

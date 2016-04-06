package mercury.arm.hardware;

/**
 * Created by Kevin on 05/04/2016.
 */
public class CPSR {

    private boolean negative;
    private boolean zero;
    private boolean carry;
    private boolean overflow;

    public CPSR(){
        this.negative = false;
        this.zero = false;
        this.carry = false;
        this.overflow = false;
    }

    public boolean getNegative() {
        return negative;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    public boolean getZero() {
        return zero;
    }

    public void setZero(boolean zero) {
        this.zero = zero;
    }

    public boolean getCarry() {
        return carry;
    }

    public void setCarry(boolean carry) {
        this.carry = carry;
    }

    public boolean getOverflow() {
        return overflow;
    }

    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
    }
}

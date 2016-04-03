package mercury.gui;

/**
 * Created by Kevin on 02/04/2016.
 */
public class Memory {

    private String wordAddress;
    private String byte3;
    private String byte2;
    private String byte1;
    private String byte0;
    private int row;

    public Memory(){
        this.wordAddress ="0x0";
        this.byte3 = " ";
        this.byte2 = " ";
        this.byte1 = " ";
        this.byte0 = " ";
        this.setRow(0);
    }

    public String getWordAddress() {
        return wordAddress;
    }

    public void setWordAddress(String wordAddress) {
        this.wordAddress = wordAddress;
    }

    public String getByte3() {
        return byte3;
    }

    public void setByte3(String byte3) {
        this.byte3 = byte3;
    }

    public String getByte2() {
        return byte2;
    }

    public void setByte2(String byte2) {
        this.byte2 = byte2;
    }

    public String getByte1() {
        return byte1;
    }

    public void setByte1(String byte1) {
        this.byte1 = byte1;
    }

    public String getByte0() {
        return byte0;
    }

    public void setByte0(String byte0) {
        this.byte0 = byte0;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}

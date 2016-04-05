package mercury.arm.hardware;

/**
 * Created by Kevin on 05/04/2016.
 */
public class Memory {
    /***
     * Entire memory has a size of 8 KB since it is allocated per byte.
     * From position index: 0 to 4095 => Program Memory.
     *                      4096 to 8192 => Data Memory.
     */
    public String[] memoryArray;
    private static final int MEMORY_SIZE = 8192;

    public Memory(){
        initializeMemory();
    }

    public void initializeMemory(){
        memoryArray = new String[MEMORY_SIZE];
        String initialValue = "00000000000000000000000000000000";
        for(int i = 0; i< MEMORY_SIZE; i++){
            memoryArray[i] = initialValue;
        }
    }
}

package mercury.simulator;

import mercury.ARMProgram;
import mercury.ProgramStatement;
import mercury.arm.Constants;
import mercury.arm.Constants.*;

import java.util.List;




/**
 * Created by Kevin on 03/04/2016.
 */
public class Simulation_Manager {


    public Simulation_Manager() {
    }

    public void Simulate(ARMProgram mainProgram) {
        List<ProgramStatement> instructionSet;
        instructionSet = mainProgram.getStatementList();
        ProgramStatement currentStatement;
        String regularMnemonic;

        for (int i = 0; i < instructionSet.size(); i++) {
            currentStatement = instructionSet.get(i);
            regularMnemonic = GetRegularMnemonic(currentStatement);
            switch (regularMnemonic) {
                case Constants.AND_MNEMONIC:
                    AndInstruction(currentStatement);
                    break;
                case Constants.EOR_MNEMONIC:
                    break;
                case Constants.SUB_MNEMONIC:
                    break;
                case Constants.RSB_MNEMONIC:
                    break;
                case Constants.ADD_MNEMONIC:
                    break;
                case Constants.ADC_MNEMONIC:
                    break;
                case Constants.SBC_MNEMONIC:
                    break;
                case Constants.RSC_MNEMONIC:
                    break;
                case Constants.CMP_MNEMONIC:
                    break;
                case Constants.CMN_MNEMONIC:
                    break;
                case Constants.ORR_MNEMONIC:
                    break;
                case Constants.MOV_MNEMONIC:
                    break;
                case Constants.LSL_MNEMONIC:
                    break;
                case Constants.ASR_MNEMONIC:
                    break;
                case Constants.RRX_MNEMONIC:
                    break;
                case Constants.ROR_MNEMONIC:
                    break;
                case Constants.BIC_MNEMONIC:
                    break;
                case Constants.MVN_MNEMONIC:
                    break;
                case Constants.MUL_MNEMONIC:
                    break;
                case Constants.MLA_MNEMONIC:
                    break;
                case Constants.STR_MNEMONIC:
                    break;
                case Constants.LDR_MNEMONIC:
                    break;
                case Constants.STRB_MNEMONIC:
                    break;
                case Constants.LDRB_MNEMONIC:
                    break;
                case Constants.B_MNEMONIC:
                    break;
            }
        }
    }

    private void AndInstruction(ProgramStatement instruction){


    }
    private String GetRegularMnemonic(ProgramStatement instruction) {
        String instrMnemonic = instruction.getMnemonic();
        int instrMnemonicLength = instrMnemonic.length();
        if (instruction.HasSetFlagsSufix() & !instruction.HasConditionalSufix()) {
            instrMnemonic = instrMnemonic.substring(0, instrMnemonicLength - 1);
        } else if (!instruction.HasSetFlagsSufix() & instruction.HasConditionalSufix()) {
            instrMnemonic = instrMnemonic.substring(0, instrMnemonicLength - 2);
        } else if (instruction.HasConditionalSufix() & instruction.HasSetFlagsSufix()) {
            instrMnemonic = instrMnemonic.substring(0, instrMnemonicLength - 3);
        }
    return instrMnemonic;
    }
}

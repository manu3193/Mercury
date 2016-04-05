package mercury.simulator;

import mercury.ARMProgram;
import mercury.ProgramStatement;
import mercury.arm.instruction.Constants;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Kevin on 03/04/2016.
 */
public class Simulation_Manager {


    public Simulation_Manager() {
    }
/*
    public void Simulate(ARMProgram mainProgram) {
        List<ProgramStatement> instructionSet;
        instructionSet = mainProgram.getStatementList();
        ProgramStatement currentStatement = new ProgramStatement();
        String regularMnemonic;
        /*
        for (int i = 0; i < instructionSet.size(); i++) {
            currentStatement = instructionSet.get(i);
            regularMnemonic = GetRegularMnemonic(currentStatement);
            switch (regularMnemonic) {
                case Constants.AND_MNEMONIC:
                    break;
                case "MEMORY":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;
                case "BRANCH":
                    break;

            }

        }
    }*/

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

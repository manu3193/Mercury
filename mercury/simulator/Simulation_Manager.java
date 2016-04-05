package mercury.simulator;

import jdk.nashorn.internal.runtime.Context;
import mercury.ARMProgram;
import mercury.ProgramStatement;

import java.util.ArrayList;


/**
 * Created by Kevin on 03/04/2016.
 */
public class Simulation_Manager {
    private static final int DATAPROCESSING = 0;
    private static final int MEMORY = 1;
    private static final int BRANCH = 0;


    public Simulation_Manager() {
    }
/*
    public void Simulate(ARMProgram mainProgram) {
        ArrayList<ProgramStatement> instructionSet = new ArrayList<>();
        //instructionSet = mainProgram.getStatementList();
        ProgramStatement currentStatement = new ProgramStatement();
        String instructionCategory = currentStatement.getInstructionType();
        for (currentStatement:
             instructionSet) {
            switch (instructionCategory) {
                case "DATAPROCESSING":
                    DataProcessingInstructionManager();
                    break;
                case "MEMORY":
                    MemoryInstructionManager();
                    break;
                case "BRANCH":
                    BranchInstructionManager();
                    break;
                default:
                    System.out.println("Invalid instruction");
                    break;

            }
        }*/
/*
    public int CategorizeProgramStatement(ProgramStatement instruction) {
        String instructionType = instruction.getInstructionType;
        if (instructionType.equals("")) {

        }


    }*/
}

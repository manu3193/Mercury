package mercury;

import java.util.ArrayList;

/**
 * Created by manzumbado on 22/03/16.
 */
public class ErrorList {

    private ArrayList<ErrorMessage> errorList;
    private int errorCount;
    public static final String ERROR_MESSAGE_PREFIX = "Error";
    public static final String FILENAME_PREFIX = " in ";
    public static final String LINE_PREFIX = " line ";
    public static final String POSITION_PREFIX = " column ";
    public static final String MESSAGE_SEPARATOR = ": ";

    public ErrorList() {
        this.errorList = new ArrayList<ErrorMessage>();
        this.errorCount = 0;
    }

    public ArrayList<ErrorMessage> getErrorMessages() {
        return errorList;
    }

    public int getNumberErrors() {
        return errorCount;
    }

    public boolean errorsOccurred() {
        return (errorCount != 0);
    }

    public void add(ErrorMessage message) {
        add(message);
    }


    public String generateReport() {
        StringBuffer report = new StringBuffer("");
        String reportLine;
        for (int i = 0; i < errorList.size(); i++) {
            ErrorMessage mess = errorList.get(i);
            reportLine = ERROR_MESSAGE_PREFIX + FILENAME_PREFIX;
            if (mess.getLine() > 0)
                reportLine = reportLine + LINE_PREFIX + mess.getLine();
            if (mess.getPos() > 0)
                reportLine = reportLine + POSITION_PREFIX + mess.getPos();
            reportLine = reportLine + MESSAGE_SEPARATOR + mess.getMessage() + "\n";
            report.append(reportLine);
        }
        return report.toString();
    }

}

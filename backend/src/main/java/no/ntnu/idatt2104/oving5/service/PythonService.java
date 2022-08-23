package no.ntnu.idatt2104.oving5.service;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class PythonService {
    public String printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder result= new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            result.append(line + "\n");
        }
        return result.toString();
    }

    public static void writeToFile(String toBeWritten){
        try {
            File file = new File("script.py");
            OutputStream out = new FileOutputStream(file);
            out.write(toBeWritten.getBytes(StandardCharsets.UTF_8));
            out.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String execute(String script) throws IOException {


        writeToFile(script);

        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/C", "start")
                .directory(new File("D:/scriptfolder"));


        String dir = String.format("%s/:/app", System.getProperty("user.dir"));
        builder.redirectErrorStream(true);

        builder.command("docker","run","--rm","-v",dir,"-w","/app","python:3","python", "script.py");

        Process p = builder.start();

        return printResults(p);

    }

}

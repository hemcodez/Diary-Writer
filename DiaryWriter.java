import java.io.*;
import java.util.Date;

public class DiaryWriter {
    public static void main(String[] args) throws IOException {
        //Create a new diary writer
        DiaryWriter diaryWriter = new DiaryWriter();

        //Use the diary writer to make a new diary
        diaryWriter.createNewDiary("Ann");

        //Create a new date for an entry from a prevois year
        Date date = new Date(2021,11,26);

        //Add an entry to the diary from a previous year
        diaryWriter.addEntry(date, "\nDear diary, " + "I am tired and my leg hurts.\n");

        //Add a an entry to the diary file for today's date
        diaryWriter.addEntry(new Date(), "\nDear diary, " + "I almost slept in.\n");

        //Remove the last entry
        diaryWriter.removeEntry(5,2);

    }//end main method

    //Method to make a new diary file
    private void createNewDiary(String ownerName){
        try{
            //Declare a string to hold the opening Line
            //in the diary
            String openingLine =
                    "Welcome to " + ownerName + "'s diary!\n";
            //Create a new file object for the diary
            File diaryFile = new File("diary.txt");

            //Check to see if the file exists on the hard drive
            //If not, use the File object to create a new txt file
            //for the diary
            if(!diaryFile.exists()){
                diaryFile.createNewFile();
            } //end if

            //Create a new FileWrite object for the BufferedWriter
            //to Use
            FileWriter fileWriter = new FileWriter(diaryFile.getName(), true);

            //Create a new BufferedWriter using the FileWriter
            BufferedWriter bw = new BufferedWriter(fileWriter);

            //Write the opening line of text to the diary file
            bw.write(openingLine);

            //close the file
            bw.close();

        } catch(IOException e){
            System.out.println("Error creating new diary.\n");
            e.printStackTrace();
        } //end try-catch
    } //end method createNewDiary

    //Method to ass a new dated diary entry
    private void addEntry(Date entryDate, String entryText) throws IOException{
        //Create a new file object for the diary
        File diaryFile = new File("diary.txt");

        try{
            //If the diary file exists already, then add the new entry
            if(diaryFile.exists()){
                if(diaryFile.canWrite()){ //Test to see if I can write to file

                    //Create a new FileWriter object for the BufferedWriter
                    FileWriter fileWriter = new FileWriter(diaryFile.getName(), true);
                    BufferedWriter bw = new BufferedWriter(fileWriter);

                    //Add the date and the entry to the end of the diary file
                    bw.append(entryDate.toString());
                    bw.append(entryText);

                    //Close the file
                    bw.close();
                }//end nested if
            }//end if
        } catch (IOException e) {
            //Cacth IO error
            //Print the stack trace for error
            System.out.println("Error adding diary entry.\n");
            e.printStackTrace();
        }//end try-catch
    }//end addEntry

    //Method to remove an entry from the diary file
    void removeEntry(int startline, int numlines) {
        try {
            //Make a new buffered reader to read in the file
            BufferedReader br = new BufferedReader(new FileReader("diary.txt"));

            //String buffer to store content of the file
            StringBuffer ab = new StringBuffer("");

            //Keep track of the line number
            int linenumber = 1;

            //Variable to hold the current line of the file
            String line;

            //Keep reading lines until the end of the file is reached
            while ((line = br.readLine()) !=null) {

                //Store each valid line in the string buffer
                if (linenumber < startline || linenumber >= startline + numlines)
                    ab.append(line+"\n");

                //  Increment the line number
                linenumber++;
            } //end while

            //Close the file
            br.close();

            //Create a new diary file without the lines that were deleted
            FileWriter fw = new FileWriter(new File("diary.txt"));

            //Write entire string buffer into the file
            fw.write(ab.toString());

            //Close the new file
            fw.close();

        } catch (Exception e) {
            System.out.println("Error removing the linfe from the diary file: " +
                    e.getMessage());
        } //end try-catch
    } //end remove entry



} //end class

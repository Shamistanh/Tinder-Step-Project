package beans;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Message {
    String who="";
    String whom = "";
    String message="";
    String date="";

}

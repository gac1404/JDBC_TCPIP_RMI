import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;


public class Security {

    private static List<String> credentialsList = asList(
            "adam|1234",
            "test|test",
            "password|admin"
    );

    private static List<String> tokenList = new ArrayList<>();

    public Security() { }

    public static String generateToken(){

        String token = UUID.randomUUID().toString();

        tokenList.add(token);

        for(String str : tokenList){
            System.out.println(str);
        }

        return token;
    }

    public static  boolean validateToken(String token){

        if(tokenList.contains(token)){
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean validateCredentials(String credentials){

        if(credentialsList.contains(credentials)){
            return true;
        }
        else {
            return false;
        }
    }

}
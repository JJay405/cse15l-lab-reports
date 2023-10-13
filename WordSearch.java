import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler 
{
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;        

    String[] storage = new String[20];

    public String handleRequest(URI url) 
    {
        if (url.getPath().equals("/")) 
        
            return String.format("Junhong's Search Engine, current words in storage is "+num);
       
           else if (url.getPath().contains("/add")) 
           {

                String[] parameters = url.getQuery().split("=");
                
               storage[num] = parameters[1];
                    
                    num += 1;
                   return String.format('"'+parameters[1]+'"'+ " is added. current words in storage is: "+num);
            }

            else if (url.getPath().contains("/search"))
            {
                String[] temp = url.getQuery().split("=");
                String keyword = temp[1];
                
                for(int i =0; i<num+1; i++)
                {
                    if(storage[i].contains(keyword)){
                           
                       return String.format("Word found: "+storage[i]);}
                    else   
                        return String.format("No word found in storage." );
                
                }
            }
            else if (url.getPath().contains("/display"))
            {
                for(int i = 0;i<10;i++)
                
                {
                return String.format("Storage is "+ storage[i]);}
            }
                    return "404 Not Found!";
            
    }
}

class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}

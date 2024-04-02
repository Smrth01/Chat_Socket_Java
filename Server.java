import java.net.*;
import java.io.*;

public class Server {
		ServerSocket server;
		Socket socket;

		BufferedReader br;
		PrintWriter out;
    
		//constructor 
		public Server()
		{
			try{
					server=new ServerSocket(7777);
					System.out.println("Server is Ready to accept connection");
					System.out .println("waiting");
					socket=server.accept();

					br= new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out= new PrintWriter(socket.getOutputStream());

					startReading();
					startWriting();

					
			}catch(Exception e) {
					e.printStackTrace();
		}
	}

		public void startReading(){
				//this thread will read data
				Runnable r1=()->{
					System.out.println("Reader Started");
					while(true){
						try{
						String msg=br.readLine();
							if(msg.equalsIgnoreCase("Bye")){
								System.out.println("Client terminated the chat");
								break;
								}

							System.out.println("CLIENT : "+msg);
						} catch(Exception e){
								e.printStackTrace();
							}


					}
				};
			new Thread(r1).start();
	    }
 
		public void startWriting(){
				// this thread will take data from user and sernd it to client constantly

				Runnable r2=()->{
					System.out.println("Writer Started..");
					while(true)
					{ 
						try{ 
						
							BufferedReader br1= new BufferedReader(new InputStreamReader(System.in));
							String content = br1.readLine();
							out.println(content);
							out.flush();
						
						} catch(Exception e){
							e.printStackTrace();
							}
					}


				};
			new Thread(r2).start();
		}
	
		

    public static void main(String[] args) {
        System.out.println("this is server: starting Server ");
        new Server();
    }

}


import java.net._
import java.io._
import scala.io._
import java.util.concurrent.Executors


object TCPClient {

    val pool = java.util.concurrent.Executors.newFixedThreadPool(2)

    1 to 10 foreach { x =>
      pool.execute(
        new Runnable {
          def run {
            Thread.sleep(2000)
            println("n: %s, thread: %s".format(x, Thread.currentThread.getId))
          }
        }
      )
    }
  def main(args: Array[String]){
     
    //set the client and servers port, input etc
    val input = StdIn.readLine();
    val clientServer =  new Socket(InetAddress.getByName("localhost"), 8000);
    val in = new BufferedSource(clientServer.getInputStream()).getLines()
    val out = new PrintStream(clientServer.getOutputStream())


    out.println("GET /echo.php?message="+ input +" HTTP/1.0\r\n");
    //continue to load until all input is taken
    while(in.hasNext){
    	println(in.next());
    }

    clientServer.close()
    
  }
}
package app.code;

import java.util.LinkedList;

public class TestClient {

    public static void  main(String[] arg){
        testOne t1= new testOne();

        t1.start();
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }

    }
}


class testOne extends Thread{
    @Override
    public void run() {

        try {
            ClientManager mngr= new ClientManager();
            System.out.println(mngr.initialize_connection());
            LinkedList s= new LinkedList();
            s.add("asshole");
            s.add("jerk");
            s.add("idiot");
            s.add("moran");
            Word word=new Word("mps","2019","here",s);
            System.out.println(mngr.replaceWord(word));

            word=mngr.getWord("MPS");
            System.out.println(word.getfirst_used());
            System.out.println(word.getMeanings().get(0));
            System.out.println(word.getOrigin());
            System.out.println("------------------------------------");

            System.out.println(word);
//            System.out.println(word.getfirst_used());
//            System.out.println(word.getMeanings().get(0));
//            System.out.println(word.getOrigin());
            System.out.println("------------------------------------");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
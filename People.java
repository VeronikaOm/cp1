package cp1;

public class People implements Runnable {

    @Override
    public void run() {
        try {
            if(!Main.isAvailableHours){
                System.err.printf("Гості з потоку %s прийшли до кав'ярні але вже зачинено \n", Thread.currentThread().getName());
                return;
            }
            Main.PeopleHere++;
            System.err.printf("Гості з потоку %s прийшли до кав'ярні і хочуть зробити замовлення \n", Thread.currentThread().getName());
            Thread.sleep(200);
           
            Main.barista.acquire();
            if(!Main.isAvailableHours){
                System.err.printf("Гості з потоку %s не встигли зробити замовлення \n", Thread.currentThread().getName());
                Main.barista.release();
                Main.PeopleHere--;
                return;
            }
            System.out.printf("Бариста прийняв замовлення від гостей з потоку %s \n", Thread.currentThread().getName());
            Thread.sleep(200);


            Thread.sleep(4000); 
                
            System.out.printf("Бариста приготував замовлення гостям з потоку %s \n", Thread.currentThread().getName());
            Thread.sleep(20);
            Main.barista.release();
           
            Thread.sleep(4000); 
         
            System.err.printf("Гості з потоку %s випили каву і пішли \n", Thread.currentThread().getName());
            Thread.sleep(200); 
            Main.PeopleHere--;
            if(Main.PeopleHere == 0){
                System.err.println("_=_=_=_=_=_=_=_=_=Персонал пішов додому=_=_=_=_=_=_=_=_=_=_=");
            }
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

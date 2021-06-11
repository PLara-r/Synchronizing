import java.util.concurrent.*;
public class Main {
    private int sheepCount = 0;
    private void incrementAndReport() {
        synchronized(this) {
            System.out.print((++sheepCount)+" ");
        }
    }
    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(20);
            Main manager = new Main();
            for(int i=0; i<10; i++)
                service.submit(() -> manager.incrementAndReport());
        } finally {
            if(service != null) service.shutdown();
        }
    }}
    //1 2 3 4 5 6 7 8 9 10
//Хотя все потоки по-прежнему создаются и выполняются одновременно, каждый из них ждет в synchronizedблоке,
// чтобы рабочий увеличил его и сообщил результат перед вводом. Таким образом, каждый работник зоопарка ждет
// возвращения предыдущего работника зоопарка, прежде чем выбежать на поле. Несмотря на то, что случайным образом,
// какой сотрудник зоопарка выбежит дальше,
// гарантировано, что на поле будет максимум один.

//смотри в описании пункт 4 - Улучшение доступа с помощью синхронизированных блоков


